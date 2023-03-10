/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.gui;


import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mobile.abonn.showabForm;
import com.mobile.offre.AddForm;
import com.mobile.offre.ShowForm;
import com.mobile.abonn.showabForm;
import com.mobile.categorie.showcategForm;
/**
 *
 * @author Cyrine daly
 */
public class HomeForm extends Form {


public HomeForm() {


    setTitle("Home");
    setLayout(new BorderLayout());
    
    // Ajouter un fond d'écran personnalisé
    Style bgStyle = getStyle();
    bgStyle.setBgColor(0xCCCCCC); // Choisir la couleur de fond
    bgStyle.setBgTransparency(255);
//      addAll(new Label("choose an option"));
    
    // Créer un conteneur pour les boutons
    Container buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    buttonsContainer.getAllStyles().setMarginTop(50);
    buttonsContainer.getAllStyles().setMarginBottom(50);
    buttonsContainer.getAllStyles().setAlignment(Component.CENTER);
    
    // Ajouter le titre

  
    
    // Créer les boutons
    Button btnListoffre = new Button("Liste des offres");
    btnListoffre.getAllStyles().setFgColor(0x000000); // Noir
    btnListoffre.addActionListener(e -> new ShowForm(this).show());
    
    Button btnListcateg = new Button("Liste des catégories");
    btnListcateg.getAllStyles().setFgColor(0x000000); // Noir
    btnListcateg.addActionListener(e -> new showcategForm(this).show());
    
    Button btnListab = new Button("Liste des abonnements");
    btnListab.getAllStyles().setFgColor(0x000000); // Noir
    btnListab.addActionListener(e -> new showabForm(this).show());
    
    // Ajouter les éléments au conteneur
    buttonsContainer.addAll(btnListoffre, btnListcateg, btnListab);
    
    // Ajouter le titre et le conteneur de boutons au centre de la forme
    add(BorderLayout.CENTER, BoxLayout.encloseY( buttonsContainer));
} }