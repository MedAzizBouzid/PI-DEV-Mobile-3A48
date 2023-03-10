/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;



/**
 *
 * @author Dali
 */
public class HomeForm extends Form{

            Resources theme = UIManager.initFirstTheme("/theme_1");

    
    public HomeForm() {
        
       // setTitle("Home");
       // setLayout(BoxLayout.y());
        
      //  add(new Label("Choose an option"));
      //  Button btnGestionEvenements = new Button("Gestion Evenements");
       // Button btnGestionsponsors = new Button("Gestion sponsors");
        
      //  btnGestionEvenements.addActionListener(e-> new GestionEventForm(this).show());
      //  btnGestionsponsors.addActionListener(e-> new ListTasksForm(this).show());
       // addAll(btnGestionEvenements,btnGestionsponsors);
        

   
     super("Admin",BoxLayout.y());
     this.getToolbar().addCommandToSideMenu("Gestion Type Event", null, e -> {
         TypeEventForm w = new TypeEventForm(this);
        w.show();    
        });
     this.getToolbar().addCommandToSideMenu("Gestion Events", null, e -> {
         EventForm w = new EventForm(this);
        w.show();    
        });
    
    }
        
    
    
    
}
