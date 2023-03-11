/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.TypeEvent;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.services.EvenementType_Service;
import java.util.ArrayList;

/**
 *
 * @author Dali
 */
public class ListTypeEventForm extends Form{

    public ListTypeEventForm(Form previous) {
        setTitle("List Type Event");
        setLayout(BoxLayout.y());

  
        ArrayList<EvenementType> EvenementTypes = new EvenementType_Service().getAllEvenementTypes();
       for (EvenementType t : EvenementTypes) {
          addElement(t);
       }
       

       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
    
      public void addElement(EvenementType eventType) {
    Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label idLabel = new Label("ID: " + eventType.getId());
    Label typeLabel = new Label("type: " + eventType.getType());


    
         container.addAll(idLabel, typeLabel);
        Button editButton = new Button("Edit");
   editButton.addActionListener(e -> {
        // Action lorsque le bouton "Edit" est cliqué
      new ModifyTypeEventForm(this,eventType).show();
              
    });
 
   Button deleteButton = new Button("Supprimer");
deleteButton.addActionListener(e -> {
    // Action lorsque le bouton "Supprimer" est cliqué
    if(Dialog.show("Confirmation", "Voulez-vous supprimer ce type?", "Oui", "Non")) {
        // Appeler la fonction de service pour supprimer l'offre
        //new EvenementType_Service().
        new EvenementType_Service().deleteEvenementType(eventType.getId());

        // Retirer le conteneur de l'offre supprimée de la vue
        this.removeComponent(container);
        this.revalidate();
    }});
  container.add(editButton);
      container.add(deleteButton);
   
    add(container);
   


} 
    
    
}
