/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycomany.utils.Statics;
import com.mycompany.entities.user;
import com.mycompany.gui.AjoutReclamationForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import java.util.Map;
/**
 *
 * @author dhiaz
 */
public class ServiceUser {

    //singleton 
    public static ServiceUser instance = null;

    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();

    }

    public boolean addUser(user t) {

        String email = t.getEmail();
        String password = t.getPassword();
        String nom = t.getNom();
        String prenom = t.getPrenom();
        String userName = t.getUserName();
        int numTel = t.getNumTel();
        String image = t.getImage();

        String url = Statics.BASE_URL + "/signupMobile?email=" + email + "&password=" + password + "&nom=" + nom + "&prenom=" + prenom
                + "&username=" + userName + "&numTel=" + numTel + "&image=" + image;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    /**
     * **********************************************************************
     */
    public void signin(TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/signinMobile?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("user not found") || json.equals("password not found")) {
                    Dialog.show("Authentication failed", "Wrong username or password", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    System.out.println("user ==" + user);

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());

                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                    float numtel = Float.parseFloat(user.get("numTel").toString());

                    SessionManager.setPassowrd(password.getText().toString());
                    SessionManager.setName(user.get("nom").toString());
                    SessionManager.setLastname(user.get("prenom").toString());
                    SessionManager.setNumtel((int) numtel);

                    SessionManager.setUserName(user.get("userName").toString());
                    SessionManager.setEmail(user.get("email").toString());
//                
//                //photo 
//                
                    if (user.get("image") != null) {
                        SessionManager.setImage(user.get("image").toString());
                    }

                    if (user.size() > 0) // l9a user
                    //                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    {
                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    /**
     * ***********************************************************************
     */
    public boolean editUser(user t) {

        String email = t.getEmail();
        String password = t.getPassword();
        String nom = t.getNom();
        String prenom = t.getPrenom();
        String userName = t.getUserName();
        int numTel = t.getNumTel();

        String url = Statics.BASE_URL + "/editUserMobile?email=" + email + "&password=" + password + "&nom=" + nom + "&prenom=" + prenom
                + "&username=" + userName + "&numTel=" + numTel + "&id=" + SessionManager.getId();

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

                    SessionManager.setPassowrd(t.getPassword());
                    SessionManager.setName(t.getNom());
                    SessionManager.setLastname(t.getPrenom());
                    SessionManager.setNumtel(t.getNumTel());

                    SessionManager.setUserName(t.getUserName());
                    SessionManager.setEmail(t.getEmail());
//                
//                //photo 
//                
                    if (t.getImage() != null) {
                        SessionManager.setImage(t.getImage());
                    }

                
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

    /************************************************/
     public boolean deleteAccount(int id) {

        

        String url = Statics.BASE_URL + "/deleteAccount?id=" + id;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
}
