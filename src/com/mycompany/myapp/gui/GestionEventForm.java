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

/**
 *
 * @author Dali
 */
public class GestionEventForm extends Form{

    public GestionEventForm(Form previous) {
        
                setTitle("Gestion Events");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnEvent = new Button("Event");
        Button btnTypeEvent = new Button("Type Event");
        
      //  btnEvent.addActionListener(e-> new EventsForm(this).show());
      //  btnTypeEvent.addActionListener(e-> new TypeEventsForm(this).show());
        addAll(btnEvent,btnTypeEvent);
        
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

    }
    
}
