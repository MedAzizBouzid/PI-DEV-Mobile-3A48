/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Produit;
import java.io.IOException;
import java.util.ArrayList;
import services.serviceProduit;

/**
 *
 * @author USER
 */
public class ListProduitForm extends Form{
Image img = null;
    public ListProduitForm(Form previous,Resources res) {
          setTitle("list des produits");
                  setLayout(BoxLayout.y());

                  int screenWidth = Display.getInstance().getDisplayWidth();
         ArrayList<Produit> Produits = serviceProduit.getInstance().getAllProduits();
      
         for (Produit t : Produits) {
            addElement(t,previous,res);
        }
    
//icon = icon.modifyWith(ColorUtil.rgb(255, 0, 0));
     //   getToolbar().addMaterialCommandToLeftBar("black",icon.scaled(32, 32),null,);
   getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,  e->new HomeForm().show());
    }


 
   
    public void addElement(Produit task,Form previous,Resources res) {
         Label lbPrix0 = new Label("Prix : ");
Label lbPrix = new Label(" " + task.getPrix());
lbPrix0.setUIID("product-price");

  
Label lbStock0 = new Label("Stock : ");
lbStock0.setUIID("product-stock");
Label lbStock = new Label(""+ task.getStock());

Label lbNom0 = new Label("Nom du produit: ");

Label lbNom = new Label(""+ task.getNom());
lbNom0.setUIID("product-name");
        System.out.println(task.getImage());
     
try {
    img = Image.createImage("file://C:/Users/Admin/AppData/Local/Temp/"+task.getImage());
   
    System.out.println(img);
} catch (IOException ex) {
    ex.printStackTrace();
}
EncodedImage encImg = EncodedImage.createFromImage(img, false);
Label lbImage = new Label("Image: ");
lbImage.setIcon(encImg);
Label lbCategorie0 = new Label("Categorie : ");
lbCategorie0.setUIID("product-stock");
Label lbCategorie = new Label(""+task.getNomCat());


            
       




//supp button
  Label lSupprimer = new Label(" ");

  Style SuppStyle=new Style(lSupprimer.getUnselectedStyle());
  SuppStyle.setFgColor(0xf21f1f);
  FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, SuppStyle);

  lSupprimer.setIcon(supprimerImage);
  lSupprimer.setTextPosition(RIGHT);
      lSupprimer.setUIID("product-delete-button");
//click delete icon
lSupprimer.addPointerPressedListener (l->{
    Dialog dig=new Dialog("Suppression");
    if(dig.show("Suppression","Vous Voulez Supprimer ?","Annuler","Oui")) {
        dig.dispose();
    }
        else
    {
        dig.dispose();
        if(serviceProduit.getInstance().deleteProduit(task.getId())){
            ToastBar.Status status = ToastBar.getInstance().createStatus();
                      status.setMessage("Suppression en cours...");
                      status.setShowProgressIndicator(true);
                      status.show();
                         status.clear();
           new ListProduitForm(previous,res).show();
        }
    }
    
    
});
       //modifier button
        Label lModifier = new Label(" ");
 lModifier.setUIID("lModifier");
  Style ModStyle=new Style(lModifier.getUnselectedStyle());
  ModStyle.setFgColor(0xf7ad02);
   FontImage modifierImage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, SuppStyle);
  lModifier.setIcon(modifierImage);
  lModifier.setTextPosition(LEFT );
       
  
  lModifier.addPointerPressedListener(l->{
      new UpdateProduit(res,task).show();
  });
  
Label bergila = new Label("-----------------------------------------------------");   

 Container container = BoxLayout.encloseY(
        lbImage,
         lbNom0,
         lbNom, 
       lbPrix0,
        lbPrix, 
        lbStock0,
        lbStock, 
        lbCategorie0,
        lbCategorie,
     
      
        BoxLayout.encloseX(lModifier, lSupprimer),  bergila
    );
 container.setUIID("container");
     // add container to the form
    add(container);

           

     

    }

    }
