/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import entities.Poste;
import services.servicePoste;

/**
 *
 * @author USER
 */
public class AddPosteForm extends Form{

    public AddPosteForm(Form previous) {
        setTitle("add a new poste");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("","Poste titre");
        TextField tfdescription = new TextField("","Poste description");
        Button btnValider= new Button("add poste");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0)||(tfdescription.getText().length()==0))
                Dialog.show("Alert","please fill all the fields", new Command("ok"));
                else
                {
                     try{
                            Poste p = new Poste(tfName.getText(),tfdescription.getText());
                            if(new servicePoste().addPoste(p))
                                Dialog.show("Success", "connection accepted", new Command("OK"));
                            else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                        }   catch(NumberFormatException e){
                            Dialog.show("ERROR","Status must be a number", new Command("OK"));
                        }
                            
                        }
            }
        });
        addAll(tfName,tfdescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
