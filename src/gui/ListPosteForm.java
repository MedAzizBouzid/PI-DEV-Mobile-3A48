/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import entities.Poste;
import java.util.ArrayList;
import services.servicePoste;

/**
 *
 * @author USER
 */
public class ListPosteForm extends Form{

    public ListPosteForm(Form previous) {
          setTitle("list postes");
                  setLayout(BoxLayout.y());

                  
         ArrayList<Poste> postes = servicePoste.getInstance().getAllPostes();
        for (Poste t : postes) {
            addElement(t);
        }
        getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    public void addElement(Poste task) {

        CheckBox cb = new CheckBox(task.getTitre());
        Label label = new Label(task.getDecription());

        cb.setEnabled(false);
        
        addAll(cb,label);

    }
}
