/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.gui.Event.AddEventForm;

/**
 *
 * @author Dali
 */
public class EventForm extends Form{

    public EventForm(Form previous) {
          setTitle("Gestion  Events");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddType = new Button("Add Event");
        Button btnListType = new Button("List Event");
        
        btnAddType.addActionListener(e-> new AddEventForm(this).show());
       // btnListType.addActionListener(e-> new ListEventForm(this).show());
        addAll(btnAddType,btnListType);
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
    
}
