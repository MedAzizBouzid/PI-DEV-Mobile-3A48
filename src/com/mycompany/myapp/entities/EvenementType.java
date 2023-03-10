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
public class EvenementType {
    int id;
    String type;

    public EvenementType() {
    }

    public EvenementType(String type) {
        this.type = type;
    }

    public EvenementType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EvenementType{" + "id=" + id + ", type=" + type + "}";
    }
    
    
    
}
