/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Event;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.gui.TypeEvent.ModifyTypeEventForm;
import com.mycompany.myapp.services.EvenementType_Service;
import com.mycompany.myapp.services.Evenement_Service;
import java.util.ArrayList;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;
import java.lang.System;


import java.net.URI;


/**
 *
 * @author Dali
 */
public class ListEventForm extends Form {
                        public static final String ACCOUNT_SID = "ACe5e12645de4fa19a9d817da822c48552";
    public static final String AUTH_TOKEN = "4890e95a4b67ff9d7f2d2127ed72ddb5";

    public ListEventForm(Form previous) {
        setTitle("List Events");
        setLayout(BoxLayout.y());

        ArrayList<Evenement> Evenements = new Evenement_Service().getAllEvenements();
        for (Evenement t : Evenements) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

    public void addElement(Evenement event) {
        Container container = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label idLabel = new Label("ID: " + event.getId());
        Label typeLabel = new Label("type: " + event.getType());
        Label nomLabel = new Label("nom: " + event.getNom());

        Label lieuLabel = new Label("lieu: " + event.getLieu());

        Label beginAtLabel = new Label("begin At: " + event.getBeginAt());

        Label finishAtLabel = new Label("finish At: " + event.getFinishAt());

        Label descriptionLabel = new Label("description: " + event.getDescription());

        Label capaciteLabel = new Label("capacite: " + event.getCapacite());
        Label prixLabel = new Label("prix: " + event.getPrix());

        container.addAll(idLabel, typeLabel,nomLabel,lieuLabel,beginAtLabel,finishAtLabel,descriptionLabel,capaciteLabel,prixLabel);
        Button editButton = new Button("Edit");
        editButton.addActionListener(e -> {
           
                // Action lorsque le bouton "Edit" est cliqué
                new ModifyEventForm(this,event).show();
           

        });

        Button deleteButton = new Button("Supprimer");
        deleteButton.addActionListener(e -> {
            // Action lorsque le bouton "Supprimer" est cliqué
            if (Dialog.show("Confirmation", "Voulez-vous supprimer cet Evenement?", "Oui", "Non")) {
                // Appeler la fonction de service pour supprimer l'offre
                //new EvenementType_Service().
                new Evenement_Service().deleteEvenement(event.getId());

                // Retirer le conteneur de l'offre supprimée de la vue
                this.removeComponent(container);
                this.revalidate();
            }
        });
                Button reserverButton = new Button("reserver");
        reserverButton.addActionListener(e -> {
            // Action lorsque le bouton "Supprimer" est cliqué
            if (Dialog.show("Confirmation", "Voulez-vous reserver cet Evenement?", "Oui", "Non")) {
                // Appeler la fonction de service pour supprimer l'offre
                //new EvenementType_Service().


   
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Call call = Call.creator(
                new com.twilio.type.PhoneNumber("+21626181201"),
                new com.twilio.type.PhoneNumber("+12697956309"),
                URI.create("http://demo.twilio.com/docs/voice.xml"))
            .create();

        System.out.println(call.getSid());
    

                
            }
        });
        container.add(editButton);
        container.add(deleteButton);
        container.add(reserverButton);
        

        add(container);

    }

}
