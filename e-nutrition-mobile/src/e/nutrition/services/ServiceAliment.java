/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import e.nutrition.entities.Aliment;
import e.nutrition.entities.CategorieAliment;
import e.nutrition.entities.Nutritionniste;
import e.nutrition.entities.Plat;
import e.nutrition.entities.tags.TagNourriture;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bacem
 */
public class ServiceAliment {
    //singleton

    ArrayList<Aliment> aliments = new ArrayList();
    public static ServiceAliment instance = null;
    private ConnectionRequest req;

    public static ServiceAliment getInstance() {
        if (instance == null) {
            instance = new ServiceAliment();
            instance.getAllAliment();
        }
        return instance;
    }

    private ServiceAliment() {
        req = new ConnectionRequest();
    }

    public List<TagNourriture> parsetag(Object objtag) {
        List<TagNourriture> tags = new ArrayList();
        List<Map<String, Object>> listtags = (List<Map<String, Object>>) objtag;
        for (Map<String, Object> oobjtag : listtags) {
            Object objMultimedia = oobjtag.get("contenuMultimedia");

            ServiceMultimedia sC = new ServiceMultimedia();

            tags.add(new TagNourriture(sC.parseContenuMultimedia(oobjtag.get("contenuMultimedia"))));
        }

        return tags;

    }

    public List<CategorieAliment> parseCategorie(Object objcategorie) {
        List<CategorieAliment> categories = new ArrayList();
        List<Map<String, Object>> list = (List<Map<String, Object>>) objcategorie;
        for (Map<String, Object> obj : list) {

            categories.add(new CategorieAliment(obj.get("nomCategorie").toString()));
        }

        return categories;

    }

    public ArrayList<Aliment> parseAliment(String jsonText) {
        {
            JSONParser j = new JSONParser();
            try {
                Map<String, Object> alimenjson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

                List<Map<String, Object>> list = (List<Map<String, Object>>) alimenjson.get("root");
                for (Map<String, Object> obj : list) {

                    Aliment a = new Aliment((int) Float.parseFloat(obj.get("id").toString()), obj.get("nom").toString(), Float.parseFloat(obj.get("lipides").toString()), Float.parseFloat(obj.get("glucides").toString()), Float.parseFloat(obj.get("proteines").toString()), Float.parseFloat(obj.get("poid").toString()));
                    a.setCategories(parseCategorie(obj.get("categorieAliment")));
                    a.setTags(parsetag(obj.get("tagNourriture")));
                    aliments.add(a);
                }
            } catch (IOException ex) {
            }
            return aliments;
        }
    }

    public ArrayList<Aliment> getAllAliment() {
        //no need to get back aliment list if already fetched 
        if (aliments.size() > 0) {
            return aliments;
        }

        String url = Statics.BASE_URL + "/api/allAliment";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                aliments = parseAliment(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return aliments;

    }

    public Aliment getAlimentbyid(int id) {
        //no need to get back aliment list if already fetched 
        for (int i = 0; i < aliments.size(); i++) {
            if (aliments.get(i).getId() == id) {
                return aliments.get(i);
            }
        }
        return null;

    }

}
