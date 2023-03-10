/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.abonn;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mobile.entites.Categorieoff;
import com.mobile.entites.abonnement;
import com.mobile.entites.offres;
import com.mobile.offre.ShowForm;
import com.mobile.services.serviceabo;
import com.mobile.services.serviceacteg;
import com.mobile.services.serviceoffre;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Cyrine daly
 */
public class addabForm extends Form{
     public addabForm(Form previous) {
    setTitle("Add a new subscription");
        setLayout(BoxLayout.y());
        
        TextField tfSalle = new TextField("", "Gym");
        TextField tfMoyenPayement = new TextField("", "Payment method");
        TextField tfEmail = new TextField("", "Email");
        TextField tfName = new TextField("", "Name");
        Picker dateDebutPicker = new Picker();
        ComboBox<Integer> comboOffres = new ComboBox<>();
        ArrayList<offres> offresList = serviceoffre.getInstance().getAlloffreList();
        for (offres o : offresList) {
           int n=o.getID();
            comboOffres.addItem(n);
        }
        
        Button btnValider = new Button("Add subscription");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfSalle.getText().length() == 0) || (tfMoyenPayement.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                             String salle = tfSalle.getText().toString();
                                 String mpayement = tfMoyenPayement.getText().toString();
                                 String email= tfEmail.getText().toString();
                                   String name=tfName.getText().toString();
                     // Date dateDebut = dateDebutPicker.getDate();
                       int selectedOffre = comboOffres.getSelectedItem();
                       
offres selectedCateg = null;

for (offres categ : offresList) {
    if (categ.getID()==(selectedOffre)) {
     selectedCateg = categ;
        break;                  }
}
                        
                        abonnement a = new abonnement(salle, mpayement, email,name, selectedCateg);
                        
                        if (serviceabo.getInstance().addAbonnement(a)) {
                            Dialog.show("Success", "Subscription added successfully", new Command("OK"));
                          //  new showabForm(previous).showBack();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Price and duration must be numbers", new Command("OK"));
                    }
                }
            }
        });
        
        addAll(tfSalle, tfMoyenPayement, tfEmail, tfName, comboOffres, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}