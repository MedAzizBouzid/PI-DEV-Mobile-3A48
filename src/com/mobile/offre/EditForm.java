/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.offre;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mobile.entites.Categorieoff;
import com.mobile.entites.offres;
import com.mobile.services.serviceacteg;
import com.mobile.services.serviceoffre;
import java.util.ArrayList;

/**
 *
 * @author Cyrine daly
 */
public class EditForm extends Form {
    
    private offres offre;

    public EditForm(offres offre, Form previous) {
        this.offre = offre;

        setTitle("Modifier Offre");
        setLayout(BoxLayout.y());

        // Afficher les informations actuelles de l'offre
        TextField descriptionField = new TextField(offre.getDescription());
        TextField prixField = new TextField(String.valueOf(offre.getPrix()));
        TextField dureeField = new TextField(String.valueOf(offre.getDuree()));
        ComboBox<Categorieoff> categField = new ComboBox<>();
        ArrayList<Categorieoff> categories = serviceacteg.getInstance().getAllcategList();
        for (Categorieoff c : categories) {
            categField.addItem(c);
            if (c.getId() == offre.getCateg().getId()) {
                categField.setSelectedItem(c);
            }
        }

        add(new Label("Description:"));
        add(descriptionField);

        add(new Label("Prix:"));
        add(prixField);

        add(new Label("Durée:"));
        add(dureeField);

        add(new Label("Catégorie:"));
        add(categField);

        Button saveButton = new Button("Enregistrer");
        saveButton.addActionListener(e -> {
            // Récupérer les nouvelles informations entrées par l'utilisateur
            String newDescription = descriptionField.getText();
            float newPrix = Float.parseFloat(prixField.getText());
            int newDuree = Integer.parseInt(dureeField.getText());
            Categorieoff newCateg = (Categorieoff) categField.getSelectedItem();

            // Mettre à jour l'objet offre avec les nouvelles informations
            offre.setDescription(newDescription);
            offre.setPrix(newPrix);
            offre.setDuree(newDuree);
            offre.setCateg(newCateg);

            // Appeler la fonction de service pour mettre à jour l'offre dans la base de données
            boolean success = serviceoffre.getInstance().modifierOffre(offre);

            if (success) {
                // Rediriger l'utilisateur vers l'écran d'affichage des offres
                new ShowForm(previous).showBack();
            } else {
                // Afficher un message d'erreur si la mise à jour a échoué
                Dialog.show("Erreur", "La mise à jour de l'offre a échoué", "OK", null);
            }
        });
        add(saveButton);

        // Ajouter un bouton "Annuler" pour retourner à l'écran précédent sans sauvegarder les modifications
        Button cancelButton = new Button("Annuler");
        cancelButton.addActionListener(e -> {
            new ShowForm(previous).showBack();
        });
        add(cancelButton);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            new ShowForm(previous).showBack();
        });
    }
}