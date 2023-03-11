/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dali
 */
public class Evenement_Service {
    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Evenement> Evenements;

    public Evenement_Service() {
        request = DataSource.getInstance().getRequest();
        }
    
    public boolean addEvenement(Evenement s) {

        String url = Statics.BASE_URL + "evenement/api/new?nom=" + s.getNom()+"&lieu="+s.getLieu()+"&typeE="+s.getType().getType()+"&beginAt="+s.getBeginAt()+"&finishAt="+s.getFinishAt()+"&description="+s.getDescription()+"&capacite="+s.getCapacite()+"&prix="+s.getPrix();
                                System.err.println("l'erreur dans le service");

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
    public ArrayList<Evenement> parseEvenements(String jsonText) {

        try {
            Evenements = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String lieu = obj.get("lieu").toString();
                String beginAt = obj.get("beginAt").toString();
                String finishAt = obj.get("finishAt").toString();
                String description = obj.get("description").toString();                                              
                int capacite= ((Double) obj.get("capacite")).intValue();
                float prix=(float) 0.0;
                
                if (obj.get("prix") != null) {
                     prix = Float.parseFloat(obj.get("prix").toString());
                } 
                

                EvenementType EventType = new EvenementType();
                Map<String, Object> type = ((Map<String, Object>) obj.get("type"));
                for (Map.Entry<String, Object> entry : type.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.equals("id")) {
                        float idt = Float.parseFloat(value.toString());
                        EventType.setId((int) idt);
                    }
                    if (key.equals("typeE")) {
                        EventType.setType(value.toString());
                    }
                    
                }
                Evenements.add(new Evenement(nom, lieu, description, beginAt, finishAt, capacite, id, prix, EventType));

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return Evenements;
    }
    
    public ArrayList<Evenement> getAllEvenements() {
        String url = Statics.BASE_URL + "evenement/api/list";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvenements(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Evenements;
    }
    
    public void deleteEvenement(int id) {
     
        String url = Statics.BASE_URL + "evenement/api/delete/" + id;

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

    }
        public boolean updateEvenement(Evenement s) {

        String url = Statics.BASE_URL + "evenement/api/update/"+s.getId()+"?nom=" + s.getNom()+"&lieu="+s.getLieu()+"&typeE="+s.getType().getType()+"&beginAt="+s.getBeginAt()+"&finishAt="+s.getFinishAt()+"&description="+s.getDescription()+"&capacite="+s.getCapacite()+"&prix="+s.getPrix();
                                System.err.println("l'erreur dans le service");

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

}
