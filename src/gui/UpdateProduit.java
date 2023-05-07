 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Categorie;
import entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
 
import services.serviceProduit;

/**
 *
 * @author Admin
 */
public class UpdateProduit extends BaseForm {
    Form current;
      String fileName = "";
    public UpdateProduit(Resources res,Produit p){
        super("Products",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        current =this;
            setToolbar(tb);
            getTitleArea().setUIID("Container");
           
            getContentPane().setScrollVisible(false);
            
         //  super.addSideMenu(res);
            TextField nom=new TextField(p.getNom(),"Nom:",5,TextField.ANY);
          Button btImage = new Button("Ajouter image");
        Label tfimage = new Label();
        btImage.addActionListener((e) -> {
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            if (path != null) {

                Image img = null;
                try {
                    img = Image.createImage(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                tfimage.setIcon(img);
                File file = new File(path);
                fileName = file.getName();
//                System.out.println(path);
//                System.out.println(fileName);
//                System.out.println(img);
   p.setImage(fileName);
                System.out.println(p.getImage());
            }
        });
             TextField prix = new TextField(String.valueOf(p.getPrix()), "Prix:", 5, TextField.ANY);
           TextField stock = new TextField(String.valueOf(p.getStock()), "Stock:",5, TextField.ANY);
              ArrayList<Categorie> categories = new ArrayList<>();
        categories = serviceProduit.getInstance().getAllCategories();
        ArrayList<String> lesTypes = new ArrayList<String>();
        for (int i = 0; i < categories.size(); i++) {
            lesTypes.add(categories.get(i).getType());
        }

        ComboBox<String> cat = new ComboBox<String>();
        for (String type : lesTypes) {
            cat.addItem(type);
        }
                                     p.setNom(nom.getText());
                            
                              p.setPrix(Float.parseFloat(prix.getText()));
                              p.setStock(Integer.parseInt(stock.getText()) );
                              p.setNomCat(cat.getSelectedItem());
                             System.out.println(p.getImage());
                          Button btnModifier=new Button ("Modifier");
                          btnModifier.setUIID("Button");
                          btnModifier.addPointerPressedListener(l->{
                           
                        
                          
                          if(serviceProduit.getInstance().modifierProduit(p)){
                            Form previous = null;
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Modification en cours...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
                                  try {
                                      new ListProduitForm(previous,res).show();
                                  } catch (IOException ex) {
                                        ex.getMessage();
                                  }
                          }
                            });
                          Button BtnAnnuler=new Button("Annuler");
                          BtnAnnuler.addActionListener(e->{
                              Form previous = null;
            try {
                new ListProduitForm(previous,res).show();
            } catch (IOException ex) {
                ex.getMessage();
            }
                          });
                            Label l1 =new Label("");

                            Label l2 =new Label("");
                            Label l3 =new Label("");
                            Label l4 =new Label("");
                            Label l5 =new Label("");
                            
                            
                            Container content = BoxLayout.encloseY(
                            l1,l2,
                                    new FloatingHint(nom),
                                    createLineSeparator(    ),
                                    tfimage,
                                     btImage,
                                     createLineSeparator(),
                                     prix,
                                       l4,l5,
                                          cat,
                                    createLineSeparator(),
                                    stock,
                                                                         createLineSeparator(),
                                    btnModifier,
                                                    BtnAnnuler


                                    
                            );
                            add(content);
                            show();
                          
                          
                          
                          

                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
            
                        

            
        
        
        
    }

    
}