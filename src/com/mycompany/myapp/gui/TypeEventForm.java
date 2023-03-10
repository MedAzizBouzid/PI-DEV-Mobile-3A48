/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.gui.TypeEvent.AddTypeEventForm;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.TypeEvent.ListTypeEventForm;

/**
 *
 * @author Dali
 */
public class TypeEventForm extends Form{

    public TypeEventForm(Form previous) {
         setTitle("Gestion Type Events");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddType = new Button("Add Type");
        Button btnListType = new Button("List Type");
        
        btnAddType.addActionListener(e-> new AddTypeEventForm(this).show());
        btnListType.addActionListener(e-> new ListTypeEventForm(this).show());
        addAll(btnAddType,btnListType);
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
