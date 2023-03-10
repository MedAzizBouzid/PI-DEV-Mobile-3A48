/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.EvenementType;
import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

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

}
