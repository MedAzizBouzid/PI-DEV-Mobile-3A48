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
public class Poste {
    private int id;
    private String titre,decription;

    public Poste(int id, String titre, String decription) {
        this.id = id;
        this.titre = titre;
        this.decription = decription;
    }

    public Poste(String titre, String decription) {
        this.titre = titre;
        this.decription = decription;
    }

    public Poste() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "Poste{" + "id=" + id + ", titre=" + titre + ", decription=" + decription + '}';
    }
    
}
