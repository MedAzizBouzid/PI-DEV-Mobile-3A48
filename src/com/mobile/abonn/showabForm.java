/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.abonn;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mobile.entites.abonnement;
import com.mobile.entites.offres;
import com.mobile.offre.AddForm;
import com.mobile.offre.EditForm;
import com.mobile.services.serviceabo;
import com.mobile.services.serviceoffre;
import com.mobile.abonn.addabForm;
import java.util.ArrayList;

/**
 *
 * @author Cyrine daly
 */
public class showabForm extends Form{
     
     public  showabForm (Form previous) {
        setTitle("List offre");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<abonnement> ab = serviceabo.getInstance().getAllcategList();
       for (abonnement t : ab) {
          addElement(t);
       }
       Button btnTri = new Button();
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_SORT, "CustomButtonIcon", 6);
        btnTri.setIcon(icon);
        btnTri.setText("Tri");
        btnTri.setUIID("CustomButton");
        btnTri.getAllStyles().setFgColor(0x000000); // Couleur noire pour le texte
        btnTri.addActionListener(e -> {
            // Action lorsque le bouton "Tri" est cliqué
            // Récupérer la liste triée depuis le service
            ArrayList<abonnement> offresTries = serviceabo.getInstance().triabpardate();
            // Effacer la vue actuelle
            this.removeAll();
            // Afficher la liste triée
            for (abonnement t : offresTries) {
                addElement(t);
            }
            // Revalider la vue
            this.revalidate();
        });
        addAll(btnTri);

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
     Button btnAddsub = new Button("Add Subscription");
btnAddsub.setUIID("CustomButton");
btnAddsub.addActionListener(e-> new addabForm(this).show());
addAll(btnAddsub);;

    }

    showabForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
   //  public void addElement(offres task) {
 
//    Label label = new Label(task.getDescription() + " - Durée : " + task.getDuree() + " - Prix : " + task.getPrix() + " €");

  //  add(label);
//}
   
    
    public void addElement(abonnement offre) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label idLabel = new Label("ID: " + offre.getId());
    Label salleLabel = new Label("Salle: " + offre.getSalle());
    Label mpayementLabel = new Label("methode de payement: " + offre.getMpayement());
   Label NameLabel = new Label("name:" + offre.getName());
     Label emailLabel = new Label("Email:" + offre.getEmail());
     Label dateLabel = new Label("date debut:" + offre.getDateD());
     
     
      Label offreLabel = new Label("id de l offre:" + offre.getOffre().getID());
         container.addAll(idLabel,salleLabel,mpayementLabel,NameLabel,emailLabel,dateLabel,offreLabel);
   
 Button deleteButton = new Button("Supprimer");
    FontImage.setMaterialIcon(deleteButton, FontImage.MATERIAL_DELETE);
deleteButton.addActionListener(e -> {
    // Action lorsque le bouton "Supprimer" est cliqué
    if(Dialog.show("Confirmation", "Voulez-vous supprimer cette offre?", "Oui", "Non")) {
        // Appeler la fonction de service pour supprimer l'offre
        serviceabo service = new serviceabo();
        service.deleteab(offre);
        // Retirer le conteneur de l'offre supprimée de la vue
       this.removeComponent(container);
        this.revalidate();
    }});
  
      container.add(deleteButton);
    
    
  add(container);
   


}
        }
    

