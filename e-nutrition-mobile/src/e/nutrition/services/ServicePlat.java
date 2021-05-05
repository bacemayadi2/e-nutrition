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
import e.nutrition.entities.Composition;
import e.nutrition.entities.Nutritionniste;
import e.nutrition.entities.Plat;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author bacem
 */
public class ServicePlat {
    public boolean resultOK;
    private ConnectionRequest req ;
    ArrayList <Plat> plats = new ArrayList();

    public ArrayList<Plat> parsePlat(String jsonText){
        {
        JSONParser j=new JSONParser();
                try 
                {
                    Map<String,Object> platsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String,Object>> list =(List<Map<String,Object>>)platsListJson.get("root");
                    for (Map<String,Object> obj :list)
                    {
                        Nutritionniste n ;
                        if( (obj.get("nutritionniste").toString() ) != "null")
                            n = new Nutritionniste("static", "satic");
                        else 
                            n =new Nutritionniste(0);
                        Plat p =new Plat(obj.get("description").toString(), (int)Float.parseFloat(obj.get("nbrportion").toString()),(int)Float.parseFloat(obj.get("id").toString()) ,obj.get("nom").toString() ,Float.parseFloat(obj.get("lipides").toString()),Float.parseFloat(obj.get("glucides").toString()) , Float.parseFloat(obj.get("proteines").toString()),Float.parseFloat(obj.get("poid").toString()) , n);
                        plats.add(p);
                    }
                } catch (IOException ex) {
                }
            return plats; 
        }
    }
    
    public ArrayList<Plat> getAllPlats(){
        String url =Statics.BASE_URL+"/api/afficherplatfrontall";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                plats= parsePlat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return plats;
  
                
        }
    
    
}
