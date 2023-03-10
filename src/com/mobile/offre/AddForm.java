/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.offre;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mobile.entites.offres;
import com.mobile.entites.Categorieoff;
import com.mobile.services.serviceacteg;
import com.mobile.services.serviceoffre;
import java.util.ArrayList;

/**
 *
 * @author Cyrine daly
 */
public class AddForm extends Form{
    public AddForm(Form previous) {
            Style bgStyle = getStyle();
        bgStyle.setBgColor(0xCCCCCC); // Choisir la couleur de fond
        bgStyle.setBgTransparency(255);
    setTitle("Add a new offer");
    setLayout(BoxLayout.y());
    
    TextField tfDescription = new TextField("", "Description");
    TextField tfPrice = new TextField("", "Price");
    TextField tfDuration = new TextField("", "Duration");
      ComboBox<String> comboCateg = new ComboBox<>();
     ArrayList<Categorieoff> categories = serviceacteg.getInstance().getAllcategList();
       for (Categorieoff c : categories)
       { String n=c.getName();
          comboCateg.addItem(n);
       }
        
    Button btnValider = new Button("Add offer");
    
    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if ((tfDescription.getText().length() == 0) || (tfPrice.getText().length() == 0) || (tfDuration.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    float price = Float.parseFloat(tfPrice.getText().toString());
                    int durree = Integer.parseInt(tfDuration.getText().toString());
                  String selectedCategName = comboCateg.getSelectedItem();

Categorieoff selectedCateg = null;

for (Categorieoff categ : categories) {
    if (categ.getName().equals(selectedCategName)) {
        selectedCateg = categ;
        break;
    }
}
                    
    offres o = new offres(price, durree, tfDescription.getText(), selectedCateg);
                   
                    if (serviceoffre.getInstance().addoffre(o)) {
                        Dialog.show("Success", "Offer added successfully", new Command("OK"));
                          new ShowForm(previous).showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Price and duree must be numbers", new Command("OK"));
                }
            }
        }
    });
   
   
    
    addAll(tfDescription, tfPrice, tfDuration,comboCateg, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
}

}