/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author Dali
 */
public class Evenement {
    private String nom,lieu,description;
    private String beginAt,finishAt;
    private int capacite,id;
    private float prix;
    private EvenementType type;

    public Evenement() {
    }

    public Evenement(String nom, String lieu, String description, String beginAt, String finishAt, int capacite, float prix, EvenementType type) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.beginAt = beginAt;
        this.finishAt = finishAt;
        this.capacite = capacite;
        this.prix = prix;
        this.type = type;
                    System.err.println("l'erreur est dans le constructeur");

    }

        public Evenement(String nom, String lieu, String description, String beginAt, String finishAt, int capacite, float prix, String typeString) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.beginAt = beginAt;
        this.finishAt = finishAt;
        this.capacite = capacite;
        this.prix = prix;
        this.type.type = typeString;
            System.err.println("l'erreur est dans le constructeur");
    }
    
    public Evenement(String nom, String lieu, String description, String beginAt, String finishAt, int capacite, int id, float prix, EvenementType type) {
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.beginAt = beginAt;
        this.finishAt = finishAt;
        this.capacite = capacite;
        this.id = id;
        this.prix = prix;
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBeginAt() {
        return beginAt;
    }

    public void setBeginAt(String beginAt) {
        this.beginAt = beginAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = finishAt;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public EvenementType getType() {
        return type;
    }

    public void setType(EvenementType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evenement{" + "nom=" + nom + ", lieu=" + lieu + ", description=" + description + ", beginAt=" + beginAt + ", finishAt=" + finishAt + ", capacite=" + capacite + ", id=" + id + ", prix=" + prix + ", type=" + type + '}';
    }
    
    
    
}
