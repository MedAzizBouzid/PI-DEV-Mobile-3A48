/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceUser;

/**
 *
 * @author dhiaz
 */
public class ChangePwd extends BaseForm {

    TextField code;
        TextField password;


    public ChangePwd(Resources res) {
        super(new BorderLayout());
        System.out.println(SessionManager.getEmail());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");

        add(BorderLayout.NORTH,
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );

        code = new TextField("", "Enter verification code", 20, TextField.ANY);
        code.setSingleLineTextArea(false);
        
        password = new TextField("", "Enter New passord", 20, TextField.ANY);
        password.setSingleLineTextArea(false);

        Button valider = new Button("Valider");
        Label haveAnAcount = new Label("Retour se connecter?");
        Button signIn = new Button("Renouveler votre mot de passe");
        signIn.addActionListener(e -> previous.showBack());//yarja3 lel window ely9ablha
        signIn.setUIID("CenterLink");

        Container content = BoxLayout.encloseY(
                new FloatingHint(code),
                createLineSeparator(),
                 new FloatingHint(password),
                createLineSeparator(),
                valider,
                FlowLayout.encloseCenter(haveAnAcount),
                signIn
        );

        content.setScrollableY(true);

        add(BorderLayout.CENTER, content);

        valider.requestFocus();

        valider.addActionListener(e -> {

            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDialog = ip.showInfiniteBlocking();

            if (ServiceUser.getInstance().changePwd(code, password)) {

                ipDialog.dispose();
                Dialog.show("Reset Password", "Your password has been updated successfuly", new Command("OK"));
                new SignInForm(res).show();
                refreshTheme();
            }else{
                ipDialog.dispose();
                Dialog.show("Fail", "Invalide Code", new Command("OK"));
                new ChangePwd(res).show();
                refreshTheme();
            }
            

        });

    }
}
