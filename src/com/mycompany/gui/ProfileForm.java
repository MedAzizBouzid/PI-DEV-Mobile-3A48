/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.user;
import com.mycompany.services.ServiceUser;
import java.io.IOException;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    Image imgU = null;
    String fileName = "";

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        System.out.println(SessionManager.getImage());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
        /**
         * ***********************************
         */
        try {
            imgU = Image.createImage("file://C:/Users/dhiaz/AppData/Local/Temp/" + SessionManager.getImage());
//            imgU = Image.createImage("file://C:/Users/dhiaz/AppData/Local/Temp/temp7676216807825127570s.jpg");
            System.out.println(imgU);
            imgU = imgU.scaled(220, 200);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        EncodedImage encImg = EncodedImage.createFromImage(imgU, false);
        Label lbImage = new Label("Image: ");
        lbImage.setIcon(encImg);
        /**
         * ***********************************
         */
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(imgU, "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));

        TextField username = new TextField(SessionManager.getUserName());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField name = new TextField(SessionManager.getName());
        name.setUIID("TextFieldBlack");
        addStringValue("Name", name);

        TextField lastname = new TextField(SessionManager.getLastname());
        lastname.setUIID("TextFieldBlack");
        addStringValue("Last Name", lastname);

        TextField email = new TextField(SessionManager.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField numtel = new TextField(String.valueOf(SessionManager.getNumtel()));
        numtel.setUIID("TextFieldBlack");
        addStringValue("Phone", numtel);

        TextField password = new TextField(SessionManager.getPassowrd(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        /**
         * ***************************************************
         */
        Button btImage = new Button("Update image");
        Label tfimage = new Label();
        btImage.addActionListener((e) -> {
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);

            if (path != null) {

                Image imgedit = null;
                try {
                    imgedit = Image.createImage(path);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                tfimage.setIcon(imgedit);
                File file = new File(path);
                fileName = file.getName();
                System.out.println(path);
                System.out.println(fileName);

            }
        });
        add(btImage);
        /**
         * ****************************************************
         */

        System.out.println(SessionManager.getEmail());

        Button next = new Button("Edit");
        Button delete = new Button("Delete");

        add(next);
        next.requestFocus();
        next.addActionListener((e) -> {
            user userr = new user(email.getText(), password.getText(), name.getText(), lastname.getText(), username.getText(), Integer.parseInt(numtel.getText()), fileName);
            if (ServiceUser.getInstance().editUser(userr)) {
                Dialog.show("Success", "Account Updated", "OK", null);
                new ProfileForm(res).show();
            } else {
                Dialog.show("Error", "Request Error", "OK", null);

            }
//            ServiceUtilisateur.getInstance().signup(username, password, email, numtel, roles, res);

        });
        add(delete);
        delete.requestFocus();
        delete.addActionListener((e) -> {
            if (Dialog.show("Confirmation", "Are you sure you want to delete your account", "Yes", "No")) {
                if (ServiceUser.getInstance().deleteAccount(SessionManager.getId())) {
                    Dialog.show("Success", "Account Deleted", "OK", null);
                    new SignInForm(res).show();
                }
            }

        });
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
