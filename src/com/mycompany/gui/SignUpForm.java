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
import com.codename1.components.FloatingHint;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.user;
import com.mycompany.services.ServiceUser;
import java.io.IOException;
import java.util.Vector;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {
String fileName="";
    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField name = new TextField("", "name", 20, TextField.ANY);
        TextField lastname = new TextField("", "lastname", 20, TextField.ANY);
        TextField image = new TextField("", "image", 20, TextField.ANY);

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField numtel = new TextField("", "numtel", 20, TextField.ANY);

        username.setSingleLineTextArea(false);
        name.setSingleLineTextArea(false);
        lastname.setSingleLineTextArea(false);

        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        numtel.setSingleLineTextArea(false);
        image.setSingleLineTextArea(false);
/******************************************************/
Button btImage=new Button("Ajouter image");
            Label tfimage=new Label();
            btImage.addActionListener((e)->{
                String path=Capture.capturePhoto(Display.getInstance().getDisplayWidth (),-1);
//                String path="/C:/Users/dhiaz/OneDrive/Bureau/Symfony/Body_Rock/public/uploads";
                 
                if(path!=null){

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
                System.out.println(Display.getInstance().getDisplayWidth ());
                                System.out.println(fileName);
                                                System.out.println(img);





                }
            });

/*******************************************************/
        Button next = new Button("SignUp");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(name),
                createLineSeparator(),
                new FloatingHint(lastname),
                createLineSeparator(),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(numtel),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                btImage
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener((e) -> {
            /**
             * **************************
             */
//            String test="test";
//            if ( Pattern.compile(".s").matcher(name.getText()).matches()) {
//                Dialog.show("Error", "Invalid name", "OK", null);
//                return;
//            }
            if (name.getText().length() == 0 || lastname.getText().length() == 0 || username.getText().length() == 0 || email.getText().length() == 0 || password.getText().length() == 0 ) {
                Dialog.show("Error", "Please fill all the fields", "OK", null);
            } else {
                // proceed with the form submission

                user userr = new user(email.getText(), password.getText(), name.getText(), lastname.getText(), username.getText(), Integer.parseInt(numtel.getText()), fileName);
                if (ServiceUser.getInstance().addUser(userr)) {
                    Dialog.show("Success", "Account created", "OK", null);
                    new SignInForm(res).show();
                } else {
                    Dialog.show("Error", "Request Error", "OK", null);
                    new SignInForm(res).show();

                }
            }

        });
    }

}
