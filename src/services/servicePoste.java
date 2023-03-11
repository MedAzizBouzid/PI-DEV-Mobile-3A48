/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Poste;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;
import java.util.List;
/**
 *
 * @author USER
 */
public class servicePoste {
    public static servicePoste instance;
    public boolean resultOK;
    private ArrayList<Poste> Postes;
    ConnectionRequest req;
    public servicePoste(){
        req = new ConnectionRequest();
    }
      public static servicePoste getInstance(){
          if(instance==null)
              instance=new servicePoste();
          return instance;
      }
    public boolean addPoste(Poste p){
        String description = p.getDecription();
        String url = Statics.BAASE_URL+"ajouterjson?description="+description+"&titre="+p.getTitre();
         req.setUrl(url);
         
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode()==200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return resultOK;
    }
    public ArrayList<Poste> parsePostes(String jsonText){
        try {
            Postes =new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Poste p=new Poste();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);
                if (obj.get("description") == null) {
                    p.setDecription("null");
                } else {
                    p.setDecription(obj.get("description").toString());
                }
                 if (obj.get("titre") == null) {
                    p.setTitre("null");
                } else {
                    p.setTitre(obj.get("titre").toString());
                }
                p.setTitre(obj.get("titre").toString());
                Postes.add(p);
            }
        } catch (IOException ex) {
                        System.out.println(ex.getMessage());
        }
      return Postes;
    }
    
    public ArrayList<Poste> getAllPostes(){
        String url =Statics.BAASE_URL+"afficherjson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Postes = parsePostes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Postes;
        
    }
}
