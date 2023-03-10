/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.entites;



/**
 *
 * @author Cyrine daly
 */
public class offres {
    private int ID ;
    private float prix;
    private int duree;
           private Categorieoff categ;
    
   
    private String description;

    public offres(float prix, int duree, String description) {
        this.prix = prix;
        this.duree = duree;
        this.description = description;
    }
 
    public offres(float prix, int duree, String description,Categorieoff categ) {
        this.prix = prix;
        this.duree = duree;
        this.description = description;
        this.categ =categ;
    }

    public offres() {
    }

    public offres(int ID, float prix, int duree, String description,Categorieoff categ) {
        this.ID = ID;
        this.prix = prix;
        this.duree = duree;
        this.description = description;
          this.categ =categ;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorieoff getCateg() {
        return categ;
    }

    public void setCateg(Categorieoff categ) {
        this.categ = categ;
    }

    @Override
    public String toString() {
        return "offres{" + "ID=" + ID + ", prix=" + prix + ", duree=" + duree + ", categ=" + categ + ", description=" + description + '}';
    }

    public offres(Categorieoff categ) {
        this.categ = categ;
    }
    

  

    
    
    
}
