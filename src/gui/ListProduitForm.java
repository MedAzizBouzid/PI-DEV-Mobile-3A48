/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import entities.Produit;
import java.util.ArrayList;
import services.serviceProduit;

/**
 *
 * @author USER
 */
public class ListProduitForm extends Form{

    public ListProduitForm(Form previous) {
          setTitle("list des produits");
                  setLayout(BoxLayout.y());

                  
         ArrayList<Produit> Produits = serviceProduit.getInstance().getAllProduits();
        for (Produit t : Produits) {
            addElement(t);
        }
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    public void addElement(Produit task) {
Label lbPrix = new Label("Prix : " + task.getPrix());
Label lbStock = new Label("Stock : " + task.getStock());
Label lbNom = new Label("Nom du produit: "+ task.getNom());
Label lbImage = new Label("Image: "+task.getImage());
  Label lSupprimer = new Label(" ");
  lSupprimer.setUIID("NewsTopLine");
  Style SuppStyle=new Style(lSupprimer.getUnselectedStyle());
  SuppStyle.setFgColor(0xf21f1f);
  FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, SuppStyle);
  lSupprimer.setIcon(supprimerImage);
  lSupprimer.setTextPosition(RIGHT);
  
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
            new ListProduitForm(this);
        }
    }
    
    
});
       
Label bergila = new Label("-----------------------------------------------------");   


        
        addAll(lbNom,lbImage,lbPrix,lbStock,lSupprimer,bergila);

    }
}
