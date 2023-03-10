/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.categorie;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mobile.entites.Categorieoff;
import com.mobile.offre.ShowForm;
import com.mobile.services.serviceacteg;
import com.mobile.services.serviceoffre;

/**
 *
 * @author Cyrine daly
 */
public class editcategForm  extends Form{
      private Categorieoff categorie;

 

    public editcategForm(Categorieoff categorie,Form previous) {
      
setTitle("Modifier la catégorie");
        setLayout(BoxLayout.y());

        this.categorie = categorie;

        // Afficher les informations actuelles de la catégorie
        TextField nameField = new TextField(categorie.getName());
        TextField descriptionField = new TextField(categorie.getDescription());

        add(new Label("Nom:"));
        add(nameField);

        add(new Label("Description:"));
        add(descriptionField);

        Button saveButton = new Button("Enregistrer");
        saveButton.addActionListener(e -> {
            // Récupérer les nouvelles informations entrées par l'utilisateur
            String newName = nameField.getText();
            String newDescription = descriptionField.getText();

            // Mettre à jour l'objet catégorie avec les nouvelles informations
            categorie.setName(newName);
            categorie.setDescription(newDescription);

            // Appeler la fonction de service pour mettre à jour la catégorie dans la base de données
          boolean success = serviceacteg.getInstance().updateCateg(categorie);

      if (success) {
            
           new showcategForm(previous).showBack();
        }
     else {
            // Afficher un message d'erreur si la mise à jour a échoué
           Dialog.show("Erreur", "La mise à jour de la catégorie a échoué", "OK", null);
       }
   
        });
        add(saveButton);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

        // Ajouter un bouton "Annuler" pour retourner à l'écran précédent sans sauvegarder les modifications
     
    }

    

   

    
}
