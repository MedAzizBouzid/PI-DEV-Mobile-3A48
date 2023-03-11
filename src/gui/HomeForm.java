/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author USER
 */
public class HomeForm extends Form{
    public HomeForm() {
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("choose an option"));
        Button btnAddposte = new Button("Add Poste");
        Button btnListposte = new Button("List Postes");
        
        btnAddposte.addActionListener(e-> new AddPosteForm(this).show());
        btnListposte.addActionListener(e-> new ListPosteForm(this).show());
        addAll(btnAddposte,btnListposte);
        
    }
    
}
