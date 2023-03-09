/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.UIBuilder;
import java.io.IOException;

/**
 *
 * @author USER
 */
public class HomeForm extends Form{
Resources res;
  Resources theme = Resources.getGlobalResources();
    public HomeForm()   {
        Form f1=null;
 try{
            Resources res = Resources.open("/theme.res");
             Image backgroundImage = res.getImage("bckg.jpg");
                Container contentPane = getContentPane();
    contentPane.getStyle().setBgImage(backgroundImage);
    contentPane.getStyle().setBgTransparency(255);
        }
        catch(IOException e){
        
    }

                            getToolbar() .addCommandToSideMenu("Partie Admin", null, new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent evt) {
                       
                            addAll(new Label("choose an option"));
                            Button btnAddposte = new Button("Ajouter un Produit");
                            Button btnListposte = new Button("Consulter les Produits");


                            btnAddposte.addActionListener(e->{
                                 ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Loading...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
                                new AddProduitForm(f1,res).show();
                                    });
                            btnListposte.addActionListener(e-> {
                                 ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Loading...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
                                new ListProduitForm(f1,res).show();
                                    });

                            addAll(btnAddposte,btnListposte);
                                           }
                                       });
                              getToolbar().addCommandToSideMenu("Partie Client", null, new ActionListener(){ 
            @Override
            public void actionPerformed(ActionEvent evt) {
                 
                                 ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Loading...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
                                new ListProduitForm(f1,res).show();
                     

            }
                              });
            
                            
                            
 
        setTitle("Body Rock");
        setLayout(BoxLayout.y());
     
 
                   
        
    }
    
}
