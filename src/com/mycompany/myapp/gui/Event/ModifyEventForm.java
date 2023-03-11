/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.Event;

import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.services.EvenementType_Service;
import com.mycompany.myapp.services.Evenement_Service;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Dali
 */
public class ModifyEventForm extends Form {

    private ComboBox eventTypeComboBox;

    public ModifyEventForm(Form previous, Evenement event)  {
        /**
         * ************begin At**************
         */
        // Créer un conteneur pour le Picker
        Container pickerContainerBegin = new Container(new BorderLayout());
        // Créer une étiquette pour indiquer que c'est la date de début
        Label beginAtLabel = new Label("Begin At:");

// Créer un Picker pour la date et l'heure
        Picker beginAtPicker = new Picker();
        beginAtPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        String dateBeginAtString = event.getBeginAt(); // votre chaîne de date
        System.out.println(dateBeginAtString);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date dateBeginAt = format.parse(dateBeginAtString); // conversion de la chaîne en objet Date
       // beginAtPicker.setDate(dateBeginAt);//affecter une valeur par default 

//beginAtPicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd HH:mm"));
// Ajouter un texte indicatif au champ de sélection du Picker
//beginAtPicker.setHint("Select Begin Date and Time");
// Ajouter l'étiquette et le Picker au conteneur
        pickerContainerBegin.add(BorderLayout.WEST, beginAtLabel);
        pickerContainerBegin.add(BorderLayout.CENTER, beginAtPicker);
        /**
         * ************begin At**************
         */

        /**
         * ************finish At**************
         */
        // Créer un conteneur pour le Picker
        Container pickerContainerfinish = new Container(new BorderLayout());
        // Créer une étiquette pour indiquer que c'est la date de début
        Label finishAtLabel = new Label("finish At:");

// Créer un Picker pour la date et l'heure
        Picker finishAtPicker = new Picker();
        finishAtPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
        String dateFinishAtString = event.getBeginAt(); // votre chaîne de date
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // Date dateFinishAt = format2.parse(dateFinishAtString); // conversion de la chaîne en objet Date
       // finishAtPicker.setDate(dateFinishAt);
//finishAtPicker.setFormatter(new SimpleDateFormat("yyyy-MM-dd HH:mm"));

// Ajouter un texte indicatif au champ de sélection du Picker
//beginAtPicker.setHint("Select Begin Date and Time");
// Ajouter l'étiquette et le Picker au conteneur
        pickerContainerfinish.add(BorderLayout.WEST, finishAtLabel);
        pickerContainerfinish.add(BorderLayout.CENTER, finishAtPicker);
        /**
         * ************finish At**************
         */

        /*    // Créer un Picker pour la date et l'heure
Picker dateTimePicker = new Picker();
dateTimePicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);
dateTimePicker.setName("begin At");
// Ajouter le Picker au conteneur
pickerContainer.add(BorderLayout.CENTER, dateTimePicker);*/
        eventTypeComboBox = new ComboBox();
        ArrayList<EvenementType> EvenementTypes = new EvenementType_Service().getAllEvenementTypes();

        setTitle("Add a new Type");
        setLayout(BoxLayout.y());

        TextField nomField = new TextField(event.getNom(), "nom");
        TextField lieuField = new TextField(event.getLieu(), "lieu");

        TextField descritionField = new TextField(event.getDescription(), "description");
        TextField capaciteField = new TextField(String.valueOf(event.getCapacite()), "capacite");
        TextField prixField = new TextField(String.valueOf(event.getPrix()), "prix");
        /**
         * ****donner une valeur par defaut pour le combo box***
         */
// Sélectionner l'élément avec la valeur "hello"
//String[] values = eventTypeComboBox.getModel().getSize();
        int selectedIndex = 0;
        for (int i = 0; i < eventTypeComboBox.getModel().getSize(); i++) {
            if (eventTypeComboBox.getModel().getItemAt(i).equals(event.getType().getType())) {
                selectedIndex = i;
                break;
            }
        }
        System.out.println(eventTypeComboBox.getModel().getSize());
        System.out.println(selectedIndex);
        eventTypeComboBox.setSelectedIndex(selectedIndex);
        /**
         * ****donner une valeur par defaut pour le combo box***
         */

        for (EvenementType eventType : EvenementTypes) {
            eventTypeComboBox.addItem(eventType.getType());
        }

        Button btnValider = new Button("Add Type");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nomField.getText().length() == 0) && (lieuField.getText().length() == 0)
                        && (descritionField.getText().length() == 0) && (capaciteField.getText().length() == 0) && (prixField.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        String nom = nomField.getText().toString();
                        String lieu = lieuField.getText().toString();
                        String description = descritionField.getText().toString();
                        int capacite = Integer.parseInt(capaciteField.getText().toString());
                        float prix = Float.parseFloat(prixField.getText().toString());
                        String selectedTypeEvent = (String) eventTypeComboBox.getSelectedItem();
                        long pickedbeginAt = beginAtPicker.getDate().getTime();
                        long pickedfinishAt = finishAtPicker.getDate().getTime();
                        Date selectedBeginAt = new Date(pickedbeginAt);
                        Date selectedFinishAt = new Date(pickedfinishAt);
                        /**
                         * **********
                         */
                        Date finishAt = finishAtPicker.getDate();
                        Date beginAt = beginAtPicker.getDate();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String formattedDatefinish = format.format(finishAt);
                        String formattedDatebegin = format.format(beginAt);

                        /**
                         * *************
                         */
                        System.err.println("nom: " + nom + " lieu: " + lieu + " desc: " + description + " capa: " + capacite + " prix: " + prix + " type: " + selectedTypeEvent + " debut: " + selectedBeginAt.toString() + " fin: " + selectedFinishAt.toString() + " formated " + formattedDatefinish);
                        EvenementType type = new EvenementType(selectedTypeEvent);
                        Evenement t = new Evenement(nom, lieu, description, formattedDatebegin, formattedDatefinish, capacite, event.getId(), prix, type);
                        //Evenement t = new Evenement();

                        if (new Evenement_Service().updateEvenement(t)) {
                            Dialog.show("Success", "Event accepted", new Command("OK"));
                            previous.show(); //retourner vers la page precedente
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                        System.err.println("eoooo");
                    }

                }

            }
        });

        addAll(nomField, lieuField, pickerContainerBegin, pickerContainerfinish, descritionField, capaciteField, prixField, eventTypeComboBox, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
