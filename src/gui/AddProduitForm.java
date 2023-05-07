package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Categorie;
import entities.Produit;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import services.serviceProduit;

/**
 *
 * @author USER
 */
public class AddProduitForm extends Form {

    String fileName = "";
    String path = "";

    protected String saveFileToDevice(String hi, String ext) throws IOException {
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath();
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {

        }
        return "hh";

    }

    public AddProduitForm(Form previous, Resources res) {
        setTitle("add a new Produit");
        setLayout(BoxLayout.y());
        TextField tfName = new TextField("", " name");
        TextField tfstock = new TextField("", "Stock");
        // TextField tfimage = new TextField("","image");
        TextField tfprix = new TextField("", "Prix");
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
                System.out.println(path);
                System.out.println(fileName);
                System.out.println(img);

            }
        });

        //System.out.println(fileName);
        Button btnValider = new Button("add Produit");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "please fill all the fields", new Command("ok"));
                } else {
                    try {
                        Produit p = new Produit(Integer.parseInt(tfstock.getText()), Float.parseFloat(tfprix.getText()), fileName, tfName.getText(), cat.getSelectedItem());
                        if (new serviceProduit().addProduit(p)) {
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setMessage("Ajout en cours...");
                            status.setShowProgressIndicator(true);
                            status.show();
                            status.clear();
                            new ListProduitForm(previous, res).show();

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "stock or prix must be a number", new Command("OK"));
                    } catch (IOException ex) {
                        ex.getMessage();
                    }

                }
            }
        });
        addAll(tfName, tfstock, tfprix, cat, tfimage, btImage, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
    }

}
