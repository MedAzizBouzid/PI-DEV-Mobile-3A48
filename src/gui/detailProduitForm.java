/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Callback;
import entities.Produit;
import java.util.ArrayList;
import services.serviceProduit;

/**
 *
 * @author Admin
 */
public class detailProduitForm extends Form{
    
     public detailProduitForm(Form previous,int id) {
          setTitle("Product detail");
             setLayout(BoxLayout.y());
      
String json = null;
                  
         Produit Produit = serviceProduit.getInstance().getProduitById(id,json);
         
            addElement(Produit);
        
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }

     
   public void addElement(Produit task) {
Label lbPrix = new Label("Prix : " + task.getPrix());
Label lbStock = new Label("Stock : " + task.getStock());
Label lbNom = new Label("Nom du produit: "+ task.getNom());
Label lbImage = new Label("Image: "+task.getImage());


        
        addAll(lbNom,lbImage,lbPrix,lbStock);

    }
   
}
