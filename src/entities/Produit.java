/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author USER
 */
public class Produit {
    private int id,stock;
String nomCat;

    private float prix;
    String image,nom;
    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
  

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Produit() {
    }

    public Produit(int stock, float prix, String image, String nom,String nomCat) {
        this.stock = stock;
        this.prix = prix;
        this.image = image;
        this.nom = nom;
        this.nomCat=nomCat;
    }

    public Produit(int id, int stock, float prix, String image, String nom,String nomCat) {
        this.id = id;
        this.stock = stock;
        this.prix = prix;
        this.image = image;
        this.nom = nom;
        this.nomCat=nomCat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
 

    
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", stock=" + stock + ", prix=" + prix + ", nom=" + nom + ", image=" + image + '}';
    }

  

    
}
