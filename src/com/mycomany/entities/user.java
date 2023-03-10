/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author dhiaz
 */
public class user {
       private int id ;

        private String email ;

        private String password ;


        private String nom ;

    


        private String prenom ;

        private String userName;

        private int numTel;

        private String image;
        public user(String email, String password, String nom, String prenom, String userName, int numTel, String image) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.userName = userName;
        this.numTel = numTel;
        this.image = image;
    }

    public user(int id, String email, String password, String nom, String prenom, String userName, int numTel, String image) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.userName = userName;
        this.numTel = numTel;
        this.image = image;
    }

    public user(String email, String password, String nom, String prenom, String userName, int numTel) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.userName = userName;
        this.numTel = numTel;
    }

    public user() {
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "user{" + "email=" + email + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", userName=" + userName + ", numTel=" + numTel + ", image=" + image + '}';
    }
        
        
        
}
