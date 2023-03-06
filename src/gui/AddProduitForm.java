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
import entities.Produit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import services.serviceProduit;

/**
 *
 * @author USER
 */
public class AddProduitForm extends Form{

    protected String saveFileToDevice(String hi,String ext)throws IOException{
        URI uri;
        try{
            uri=new URI(hi);
            String path=uri.getPath();
            int index =hi.lastIndexOf("/");
            hi=hi.substring(index+1);
            return hi;
        }catch(URISyntaxException ex)
        {
            
        }
        return "hh";
        
        
    }
    
    
    public AddProduitForm(Form previous) {
        setTitle("add a new Produit");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField(""," name");
         TextField tfstock = new TextField("","Stock");
                  TextField tfimage = new TextField("","image");

         TextField tfprix = new TextField("","Prix");
      


        
        Button btnValider= new Button("add Produit");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length()==0))
                Dialog.show("Alert","please fill all the fields", new Command("ok"));
                else
                {
                     try{
                            Produit p = new Produit(Integer.parseInt(tfstock.getText()),Integer.parseInt(tfprix.getText()),tfimage.getText(),tfName.getText());
                            if(new serviceProduit().addProduit(p))
                                Dialog.show("Success", "connection accepted", new Command("OK"));
                            else
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                        }   catch(NumberFormatException e){
                            Dialog.show("ERROR","stock or prix must be a number", new Command("OK"));
                        }
                            
                        }
            }
        });
        addAll(tfName,tfimage,tfstock,tfprix,btnValider);
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
