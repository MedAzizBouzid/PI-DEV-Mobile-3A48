/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.categorie;

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
import com.mobile.entites.Categorieoff;
import com.mobile.entites.offres;
import com.mobile.offre.ShowForm;
import com.mobile.services.serviceacteg;
import com.mobile.services.serviceoffre;
import java.util.ArrayList;


/**
 *
 * @author Cyrine daly
 */
public class addcateForm extends Form{
     public addcateForm(Form previous) {
    setTitle("Add a new categorie");
    setLayout(BoxLayout.y());
    
    TextField tfDescription = new TextField("", "Description");
    TextField tfname = new TextField("", "Name");
  
 
        
    Button btnValider = new Button("Add categorie");
    
    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if ((tfDescription.getText().length() == 0) || (tfname.getText().length() == 0) ) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                 String name = tfname.getText().toString();
                                 String des = tfDescription.getText().toString();
 
                    
    Categorieoff o = new Categorieoff(name,des);
                   
                    if (serviceacteg.getInstance().addcateg(o)) {
                        Dialog.show("Success", "categorie added successfully", new Command("OK"));
                          new showcategForm(previous).showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "name and duration must be numbers", new Command("OK"));
                }
            }
        }
    });
   
   
    
    addAll(tfDescription,tfname , btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}

    
}
