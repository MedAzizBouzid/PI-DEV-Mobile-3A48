/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String userName ; 
    private static String email; 
    private static String passowrd ;
    private static String image;
        private static String name;
    private static String lastname;
    private static int numtel;


    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserName() {
        return pref.get("username",userName);
    }

    public static void setUserName(String userName) {
         pref.set("username",userName);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getImage() {
        return pref.get("image",image);
    }

    public static void setImage(String photo) {
         pref.set("photo",photo);
    }

    public static String getName() {
        return pref.get("name",name);
    }

    public static void setName(String name) {
         pref.set("name",name);
    }

    public static String getLastname() {
        return pref.get("lastname",lastname);
    }

    public static void setLastname(String lastname) {
         pref.set("lastname",lastname);
    }

    public static int getNumtel() {
        return pref.get("numtel",numtel);
    }

    public static void setNumtel(int numtel) {
         pref.set("numtel",numtel);
    }
    
    
    
    
    
    
}
