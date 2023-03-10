/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.categorie;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mobile.abonn.addabForm;
import com.mobile.entites.Categorieoff;
import com.mobile.services.serviceacteg;
import com.mobile.categorie.editcategForm;

import java.util.ArrayList;



/**
 *
 * @author Cyrine daly
 */
public class showcategForm  extends Form {
    
    public  showcategForm (Form previous) {
  
        
            setTitle("List offre");
           setLayout(BoxLayout.y());
     

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
        ArrayList<Categorieoff> categorieoff = serviceacteg.getInstance().getAllcategList();
       for (Categorieoff t : categorieoff) {
          addElement(t,previous);
       }
    

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        Button btnAddsub = new Button("Add categ");
    //    FontImage.setMaterialIcon(btnAddsub, FontImage.MATERIAL_ADD);
btnAddsub.setText("Add categorie");
btnAddsub.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
btnAddsub.getAllStyles().setAlignment(Component.CENTER);
btnAddsub.getAllStyles().setFgColor(0x000000); // Couleur noire pour le texte

btnAddsub.addActionListener(e-> new addcateForm(this).show());
addAll(btnAddsub);;


    }

  

  
   //  public void addElement(offres task) {
 
//    Label label = new Label(task.getDescription() + " - Durée : " + task.getDuree() + " - Prix : " + task.getPrix() + " €");

  //  add(label);
//}
   
    
    public void addElement(Categorieoff cat,Form previous) {
        
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label idLabel = new Label("ID: " + cat.getId());
      idLabel.setUIID("OffreLabel");
    Label prixLabel = new Label("desc: " + cat.getDescription());
      prixLabel.setUIID("OffreLabel");
    Label dureeLabel = new Label("name: " + cat.getName());
    dureeLabel.setUIID("OffreLabel");
  
         container.addAll(idLabel, prixLabel, dureeLabel);
        
         Button editButton = new Button("Edit");
             FontImage.setMaterialIcon(editButton, FontImage.MATERIAL_MODE_EDIT);
   editButton.addActionListener(e -> {
        // Action lorsque le bouton "Edit" est cliqué
      new editcategForm(cat,previous).show();
    });
         Button deleteButton = new Button("Supprimer");
            FontImage.setMaterialIcon(deleteButton, FontImage.MATERIAL_DELETE);
deleteButton.addActionListener(e -> {
    // Action lorsque le bouton "Supprimer" est cliqué
    if(Dialog.show("Confirmation", "Voulez-vous supprimer cette offre?", "Oui", "Non")) {
        // Appeler la fonction de service pour supprimer l'offre
        serviceacteg service =new serviceacteg();
        service.deletecateg (cat);
        // Retirer le conteneur de l'offre supprimée de la vue
        this.removeComponent(container);
        this.revalidate();
    }});
 
      container.add(deleteButton);
    
   container.add(editButton);
     add(container);


}
    

    public showcategForm() {
    }

    
   


    
    
    
}
