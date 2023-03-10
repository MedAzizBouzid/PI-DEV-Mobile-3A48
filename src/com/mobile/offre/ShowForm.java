/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.offre;


import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mobile.entites.offres;
import com.mobile.services.serviceoffre;
import java.util.ArrayList;
import javafx.scene.control.Separator;

/**
 *
 * @author Cyrine daly
 */
public class ShowForm extends Form{
    
     public  ShowForm (Form previous) {
          Style bgStyle = getStyle();
        bgStyle.setBgColor(0xCCCCCC); // Choisir la couleur de fond
        bgStyle.setBgTransparency(255);
        setTitle("List offre");
        setLayout(BoxLayout.y());
        

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<offres> offres = serviceoffre.getInstance().getAlloffreList();
       for (offres t : offres) {
          addElement(t,previous);
            
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
            ArrayList<offres> offresTries = serviceoffre.getInstance().trioffreparprix();
            // Effacer la vue actuelle
            this.removeAll();
            // Afficher la liste triée
            for (offres t : offresTries) {
                addElement(t, previous);
            }
            // Revalider la vue
            this.revalidate();
        });
        addAll(btnTri);
       

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
Button btnAddTask = new Button();
FontImage.setMaterialIcon(btnAddTask, FontImage.MATERIAL_ADD);
btnAddTask.setText("Add offre");
btnAddTask.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
btnAddTask.getAllStyles().setAlignment(Component.CENTER);
btnAddTask.getAllStyles().setFgColor(0x000000); // Couleur noire pour le texte


btnAddTask.addActionListener(e-> new AddForm(this).show());

//Container btnContainer = BoxLayout.encloseY(new ComponentGroup(btnAddTask));
//btnAddTask.getAllStyles().setAlignment(Component.CENTER);
addAll(btnAddTask);
     }
    ShowForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public static Separator createSeparator() {
    Separator sep = new Separator();
 
    return sep;
}


  
   //  public void addElement(offres task) {
 
//    Label label = new Label(task.getDescription() + " - Durée : " + task.getDuree() + " - Prix : " + task.getPrix() + " €");

  //  add(label);
//}
   
    
    public void addElement(offres offre,Form previous) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label idLabel = new Label("ID:   " + offre.getID());
       idLabel.setUIID("OffreLabel");
    Label prixLabel = new Label("Prix:   " + offre.getPrix());
       prixLabel.setUIID("OffreLabel");
    Label dureeLabel = new Label("Duree:  " + offre.getDuree());
       dureeLabel.setUIID("OffreLabel");
     Label DescriptionLabel = new Label("Description: " + offre.getDescription());
        DescriptionLabel.setUIID("OffreLabel");
     
     
       Label categorieLabel = new Label("categorie:  " + offre.getCateg().getName());
       categorieLabel.setUIID("OffreLabel");
         container.addAll(idLabel, prixLabel, dureeLabel,DescriptionLabel,categorieLabel);
        Button editButton = new Button("Edit");
        FontImage.setMaterialIcon(editButton, FontImage.MATERIAL_MODE_EDIT);
   editButton.addActionListener(e -> {
        // Action lorsque le bouton "Edit" est cliqué
      new EditForm(offre,previous).show();
    });
 
   Button deleteButton = new Button("Supprimer");
   FontImage.setMaterialIcon(deleteButton, FontImage.MATERIAL_DELETE);
deleteButton.addActionListener(e -> {
    // Action lorsque le bouton "Supprimer" est cliqué
    if(Dialog.show("Confirmation", "Voulez-vous supprimer cette offre?", "Oui", "Non")) {
        // Appeler la fonction de service pour supprimer l'offre
        serviceoffre service = new serviceoffre();
        service.deleteOffre( offre);
        // Retirer le conteneur de l'offre supprimée de la vue
        this.removeComponent(container);
        this.revalidate();
    }});
  container.add(editButton);
      container.add(deleteButton);
     //   add(createLineSeparator(0xeeeeee));
     container.setUIID("Separator");
    add(container);
      
   


}
    public static Separator createLineSeparator(int color) {
    Separator separator = new Separator();
   
    return separator;
}
        }
    
    
     

