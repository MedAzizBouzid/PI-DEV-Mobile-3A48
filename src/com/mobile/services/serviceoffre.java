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
import com.codename1.ui.events.ActionListener;
import com.mobile.entites.Categorieoff;
import com.mobile.entites.offres;


import com.mobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Cyrine daly
 */
public class serviceoffre {
     private ConnectionRequest req;
     private static serviceoffre instance;
  public ArrayList<offres> of;
      public boolean resultOK;
   
    

    public serviceoffre() {
        req = new ConnectionRequest();
    }
      public static serviceoffre getInstance() {
    
       if (instance == null) {
            instance = new serviceoffre();
        }
        return instance;
    }
      
      
       public boolean addoffre(offres t) {

        String description = t.getDescription();
        int duree =  t.getDuree();
          Categorieoff categ =  t.getCateg();
        float prix=t.getPrix();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = Statics.BASE_URL + "offres/newjson?prix="+prix+"&description="+description+"&durree="+duree+"&categ="+categ.getId() ;

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
      
       public ArrayList<offres> parseTasks(String jsonText) {
        try {
            of= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                offres t = new offres();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setID((int) id);
                t.setPrix(((int) Float.parseFloat(obj.get("prix").toString())));
                    t.setDuree(((int) Float.parseFloat(obj.get("duree").toString()))); 
          t.setDescription(obj.get("description").toString());  
          
Map<String, Object> categorieJson = (Map<String, Object>) obj.get("categ");
//System.out.println("categorieJson: " + categorieJson);
  //  t.setCateg(categorieJson);
 Categorieoff categorie = new Categorieoff();
if(categorieJson != null){
  
   
    //categorie.setId((int) Float.parseFloat(categorieJson.get("id").toString()));
    categorie.setName(categorieJson.get("name").toString());
    categorie.getName();
    t.setCateg(categorie);
} else {
   System.out.println("Category is null");
     categorie.setName("null");
   t.setCateg(categorie);
   
}  of.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return of;
    }
      
       
       
  
    
    
       public ArrayList<offres> getAlloffreList() {
        String url = Statics.BASE_URL + "offres/mobile";
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
       
       
 public boolean modifierOffre(offres t) {

        String description = t.getDescription();
        int duree =  t.getDuree();
        float prix=t.getPrix();
        int id = t.getID();
        Categorieoff categ=t.getCateg();
        
        //String url = Statics.BASE_URL + "update?id=" + t.getID() + "&name=" + t.getName() + "&status=" + t.getStatus();
  String url = Statics.BASE_URL + "offres/" + t.getID() + "/editjson?prix=" + prix + "&description=" + description + "&durree=" + duree+"&categ="+categ.getId();

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
 
 public boolean deleteOffre(offres t) {
    int id = t.getID();
    String url = Statics.BASE_URL + "offres/deletejson"+ "/" + id ;
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
  public ArrayList<offres> trioffreparprix() {
    String url = Statics.BASE_URL + "offres/trimobile";
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
