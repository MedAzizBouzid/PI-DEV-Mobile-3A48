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
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import entities.Categorie;
import entities.Produit;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import utils.Statics;
import java.util.List;
import static jdk.nashorn.internal.runtime.Debug.id;
import static utils.Statics.BAASE_URL;

/**
 *
 * @author USER
 */
public class serviceProduit {

    public static serviceProduit instance;
    public boolean resultOK = true;
    private ArrayList<Produit> Produits;
    private ArrayList<Categorie> Categories;

    private Produit produit;
    Resources res;
    ConnectionRequest req;

    public serviceProduit() {
        req = new ConnectionRequest();
    }

    public static serviceProduit getInstance() {
        if (instance == null) {
            instance = new serviceProduit();
        }
        return instance;
    }

    public boolean addProduit(Produit p) {
        String nom = p.getNom();
        String url = Statics.BAASE_URL + "ajoutMobile?nom=" + nom + "&image=" + p.getImage() + "&prix=" + p.getPrix() + "&stock=" + p.getStock() + "&categorie=" + p.getNomCat();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public ArrayList<Produit> parseProduits(String jsonText) {
        try {
            Produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Map<String, String> cat = (Map<String, String>) obj.get("categorie");

                Produit p = new Produit();
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int) id);
                if (obj.get("nom") == null) {
                    p.setNom("null");
                } else {
                    p.setNom(obj.get("nom").toString());
                }
                if (obj.get("image") == null) {
                    p.setImage("null");
                } else {
                    p.setImage(obj.get("image").toString());
                }
                p.setImage(obj.get("image").toString());
                if (obj.get("stock") == null) {
                    p.setNom("null");
                } else {
                    p.setStock(Integer.parseInt(obj.get("stock").toString()));
                }
                if (obj.get("prix") == null) {
                    p.setNom("null");
                } else {
                    p.setPrix(Float.parseFloat(obj.get("prix").toString()));
                }
                if (cat.get("type") == null) {
                    p.setNomCat("null");
                } else {
                    p.setNomCat(cat.get("type").toString());
                }

                Produits.add(p);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Produits;
    }

    public ArrayList<Categorie> parseCategories(String jsonText) {
        try {
            Categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(tasksListJson);

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                Categorie c = new Categorie();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                System.out.println(obj);
                if (obj.get("Type") == null) {
                    c.setType("null");
                } else {
                    c.setType(obj.get("Type").toString());
                }

                Categories.add(c);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return Categories;
    }

    public ArrayList<Produit> getAllProduits() {
        String url = Statics.BAASE_URL + "showMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;

    }

    public ArrayList<Categorie> getAllCategories() {
        String url = Statics.BAASE_URL + "categorieMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Categories = parseCategories(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Categories;

    }

    public Produit getProduitById(int id, String jsonText) {

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                float idFromJson = Float.parseFloat(obj.get("id").toString());
                int idInt = (int) idFromJson;
                if (idInt == id) {
                    Produit p = new Produit();
                    p.setId(idInt);
                    if (obj.get("nom") == null) {
                        p.setNom("null");
                    } else {
                        p.setNom(obj.get("nom").toString());
                    }
                    if (obj.get("image") == null) {
                        p.setImage("null");
                    } else {
                        p.setImage(obj.get("image").toString());
                    }
                    if (obj.get("stock") == null) {
                        p.setNom("null");
                    } else {
                        p.setStock(Integer.parseInt(obj.get("stock").toString()));
                    }
                    if (obj.get("prix") == null) {
                        p.setNom("null");
                    } else {
                        p.setPrix(Float.parseFloat(obj.get("prix").toString()));
                    }
                    return p;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;

    }

    public boolean deleteProduit(int id) {
        String url = Statics.BAASE_URL + "deleteJson/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public boolean modifierProduit(Produit produit) {
        String url = Statics.BAASE_URL + "editJson/" + produit.getId() + "?nom=" + produit.getNom() + "&image=" + produit.getImage() + "&prix=" + produit.getPrix() + "&stock=" + produit.getStock()+"&categorie=" + produit.getNomCat();
              // String url = Statics.BAASE_URL + "ajoutMobile?nom=" + nom + "&image=" + p.getImage() + "&prix=" + p.getPrix() + "&stock=" + p.getStock() + "&categorie=" + p.getNomCat();

        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseCodeListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

}
