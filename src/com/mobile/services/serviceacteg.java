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
import com.codename1.ui.events.ActionListener;
import com.mobile.entites.Categorieoff;
import com.mobile.entites.offres;

import com.mobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author Cyrine daly
 */
public class serviceacteg {

  
     private ConnectionRequest req;
  
     public static serviceacteg instance;
      public ArrayList<Categorieoff> of;
      public boolean resultOK;  
      
      
     public serviceacteg() {
                req = new ConnectionRequest(); 
     }
   
    

      public static  serviceacteg getInstance() {
    
       if (instance == null) {
            instance = new serviceacteg();
        }
        return instance;
    }
     
    
      public boolean addcateg(Categorieoff t) {

      
        String name=t.getName();
       
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
    //    String url = Statics.BASE_URL + "categorie/newcatjson?name="+ name"&description="+  t.getDescription();
          String url = Statics.BASE_URL + "categorie/newcatjson?name="+name+"&description="+t.getDescription() ;

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
    
    
       public ArrayList<Categorieoff> parseTasks(String jsonText) {
        try {
            of= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categorieoff t = new Categorieoff();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int) id);
                   t.setName(obj.get("name").toString());  
               
                  
          t.setDescription(obj.get("description").toString());  
  
                of.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return of;
    }
   public ArrayList<Categorieoff> getAllcategList() {
        String url = Statics.BASE_URL + "categorie/afficatej";
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
   
    public boolean deletecateg(Categorieoff t) {
    int id = t.getId();
    String url = Statics.BASE_URL + "categorie/deletecateg"+ "/" + id ;
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
    
    public boolean updateCateg(Categorieoff cat) {
        String description = cat.getDescription();
        String name =  cat.getName();
        int id =cat.getId();
    String url = Statics.BASE_URL + "categorie/" + cat.getId()+ "/editcategjson?Name=" + name + "&description=" + description;
   
    
    // Créer les paramètres pour la requête PUT

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
    
}
       
    
    