/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Admin
 */
public class Categorie {
   private  String Type;
   private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie() {
    }

    public Categorie(String Type) {
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(int id,String Type) {
        this.Type = Type;
        this .id=id;
    }
    
    public void setType(String Type) {
        this.Type = Type;
    }
    
}
