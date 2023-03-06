/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;

/**
 *
 * @author USER
 */
public class HomeForm extends Form{
Resources res;
  Resources theme = Resources.getGlobalResources();
    public HomeForm() {
      // définit une image de fond

        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddposte = new Button("Add Product");
        Button btnListposte = new Button("List Products");
     
           
        btnAddposte.addActionListener(e-> new AddProduitForm(this,res).show());
        btnListposte.addActionListener(e-> new ListProduitForm(this,res).show());
           
        addAll(btnAddposte,btnListposte);
        
    }
    
}
