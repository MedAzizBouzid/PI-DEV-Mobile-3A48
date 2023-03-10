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
public class EvenementType_Service {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<EvenementType> EvenementTypes;

    public EvenementType_Service() {
        request = DataSource.getInstance().getRequest();
    }

    public ArrayList<EvenementType> parseEvenementTypes(String jsonText) {

        try {
            EvenementTypes = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                int id = (int) Float.parseFloat(obj.get("id").toString());
                String type = obj.get("typeE").toString();

                EvenementTypes.add(new EvenementType(id, type));

            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return EvenementTypes;
    }
    
    public ArrayList<EvenementType> getAllEvenementTypes() {
        String url = Statics.BASE_URL + "typeEvenement/api/list";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                EvenementTypes = parseEvenementTypes(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return EvenementTypes;
    }
    
    public boolean addEvenementType(EvenementType s) {

        String url = Statics.BASE_URL + "typeEvenement/api/new?type=" + s.getType();

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
    
    public void deleteEvenementType(int id) {
     
        String url = Statics.BASE_URL + "typeEvenement/api/delete/" + id;

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
    
    public boolean updateEvenementType(EvenementType s) {
       
        String url = Statics.BASE_URL + "typeEvenement/api/update/" + s.getId() + "?typeE=" + s.getType();

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
