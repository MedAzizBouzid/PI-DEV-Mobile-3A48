/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Produit;
import services.serviceProduit;

/**
 *
 * @author Admin
 */
public class UpdateProduit extends BaseForm {
    Form current;
    
    public UpdateProduit(Resources res,Produit p){
        super("Products",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        current =this;
            setToolbar(tb);
            getTitleArea().setUIID("Container");
           
            getContentPane().setScrollVisible(false);
            
         //  super.addSideMenu(res);
            TextField nom=new TextField(p.getNom(),"Nom:",5,TextField.ANY);
            TextField Image= new TextField(p.getImage(), "Image:",5, TextField.ANY);
             TextField prix = new TextField(String.valueOf(p.getPrix()), "Prix:", 5, TextField.ANY);
           TextField stock = new TextField(String.valueOf(p.getStock()), "Stock:",5, TextField.ANY);
           nom.setUIID("NewsTopLine");
               Image.setUIID("NewsTopLine");
                   prix.setUIID("NewsTopLine");
                       stock.setUIID("NewsTopLine");
                       
                       nom.setSingleLineTextArea(true);
                                              Image.setSingleLineTextArea(true);
                                              prix.setSingleLineTextArea(true);
                                              stock.setSingleLineTextArea(true);
                                  
                          Button btnModifier=new Button ("Modifier");
                          btnModifier.setUIID("Button");
                          btnModifier.addPointerPressedListener(l->{
                              p.setNom(nom.getText());
                               p.setImage(Image.getText());
                              p.setPrix(Float.parseFloat(prix.getText()));
                              p.setStock(Integer.parseInt(stock.getText()) );
                             
                        
                          
                          if(serviceProduit.getInstance().modifierProduit(p)){
                            Form previous = null;
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Modification en cours...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
                              new ListProduitForm(previous,res).show();
                          }
                            });
                          Button BtnAnnuler=new Button("Annuler");
                          BtnAnnuler.addActionListener(e->{
                              Form previous = null;
                              new ListProduitForm(previous,res).show();
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
                                     new FloatingHint(Image),
                                     createLineSeparator(),
                                     prix,
                                       l4,l5,
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
