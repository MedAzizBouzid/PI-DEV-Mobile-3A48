/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui.TypeEvent;

import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.services.EvenementType_Service;

/**
 *
 * @author Dali
 */
public class ModifyTypeEventForm extends Form{

    public ModifyTypeEventForm(Form previous,EvenementType c) {
         setTitle("Edit Type Event");
        setLayout(BoxLayout.y());

        TextField tfType = new TextField(c.getType(), "Type Event");
        Button btnValider = new Button("Edit Type");
                    


        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfType.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        
                        EvenementType t = new EvenementType(c.getId(), tfType.getText().toString());
                        if (new EvenementType_Service().updateEvenementType(t)) {
                            Dialog.show("Success", "Edit accepted", new Command("OK"));
                            previous.show(); //retourner vers la page precedente
                        }
                        else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfType, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
    
    
}
