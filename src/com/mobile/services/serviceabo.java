/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;

import com.codename1.ui.events.ActionListener;
import com.mobile.entites.Categorieoff;

import com.mobile.entites.abonnement;
import com.mobile.entites.offres;



import com.mobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;




/**
 *
 * @author Cyrine daly
 */
public class serviceabo {
    
     private ConnectionRequest req;
  
     public static serviceabo instance;
      public ArrayList<abonnement> of;
      public boolean resultOK; 
      
          public serviceabo() {
                req = new ConnectionRequest(); 
     }
      
        public static  serviceabo getInstance() {
    
       if (instance == null) {
            instance = new serviceabo();
        }
        return instance;
    }
        public boolean addAbonnement(abonnement t){

        String salle=t.getSalle();
        Date Date= t.getDateD();
       offres offres=t.getOffre();
        String mpayement= t.getMpayement();
        String Email=t.getEmail();
         String name=t.getName();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL +"abonnement/newabjson?salle="+salle+"&mpayement="+mpayement+"&email="+Email+"&name="+name+"&offre="+offres;
                
                

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    
    }
        
        
        public ArrayList<abonnement> parseTasks(String jsonText) {
        try {
            of= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                abonnement t = new abonnement();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                  t.setSalle(obj.get("salle").toString());  
                         t.setMpayement(obj.get("MPayement").toString());  
                                t.setEmail(obj.get("Email").toString());  
                                       t.setName(obj.get("Name").toString());  
                                      try {
   Date date = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("DateD").toString());
    t.setDateD(date); 
} catch (ParseException e) {
    System.err.println("Erreur lors de l'analyse de la date : " + e.getMessage());
}
       
       Map<String, Object> categorieJson = (Map<String, Object>) obj.get("offre");
//System.out.println("categorieJson: " + categorieJson);
  //  t.setCateg(categorieJson);
 offres categorie = new offres();
if(categorieJson != null){
  
   
    categorie.setID((int) Float.parseFloat(categorieJson.get("id").toString()));  
          
   categorie.getID();
   t.setOffre(categorie);
} 
//else {
  //   System.out.println("com.mobile.services.serviceabo.parseTasks()");         
                  
         
//}
                of.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return of;
    }
public ArrayList<abonnement> getAllcategList() {
    String url = Statics.BASE_URL + "abonnement/affiabj";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
       
            String responseData = new String(req.getResponseData());
          
            of = parseTasks(responseData);
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return of;
}

 public boolean deleteab(abonnement t) {
    int id = t.getId();
    String url = Statics.BASE_URL + "abonnement/deleteabj"+ "/" + id ;
    req.setUrl(url);
    req.setPost(false);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
        
}
   public ArrayList<abonnement> triabpardate() {
    String url = Statics.BASE_URL + "abonnement/abtri";
   req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                of = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return of;


  
    
}
}
