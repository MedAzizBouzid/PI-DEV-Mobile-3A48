/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.entites;

import java.util.Date;
/**
 *
 * @author Cyrine daly
 */
public class abonnement {
      private int id;
    private String salle;
    private String mpayement;
     private String email;
      private String name;
      private Date dateD;
      private offres offre;

    public abonnement() {
    }

    public abonnement(int id, String salle, String mpayement, String email, String name, Date dateD, offres offre) {
        this.id = id;
        this.salle = salle;
        this.mpayement = mpayement;
        this.email = email;
        this.name = name;
        this.dateD = dateD;
        this.offre = offre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }

    public String getMpayement() {
        return mpayement;
    }

    public void setMpayement(String mpayement) {
        this.mpayement = mpayement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateD() {
        return dateD;
    }

    public void setDateD(Date dateD) {
        this.dateD = dateD;
    }

    public offres getOffre() {
        return offre;
    }

    public void setOffre(offres offre) {
        this.offre = offre;
    }

    @Override
    public String toString() {
        return "abonnement{" + "id=" + id + ", salle=" + salle + ", mpayement=" + mpayement + ", email=" + email + ", name=" + name + ", dateD=" + dateD + ", offre=" + offre + '}';
    }

    public abonnement(String salle, String mpayement, String email, String name, offres offre) {
        this.salle = salle;
        this.mpayement = mpayement;
        this.email = email;
        this.name = name;
  
        this.offre = offre;
    }
    

   
      
}
