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
import e.nutrition.entities.Composition;
import e.nutrition.entities.ContenuMultimedia;
import e.nutrition.entities.EtapeDePreparation;
import e.nutrition.entities.Nutritionniste;
import e.nutrition.entities.Plat;
import e.nutrition.entities.tags.TagNourriture;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.exec.util.StringUtils;



/**
 *
 * @author bacem
 */
public class ServicePlat {
    public boolean resultOK;
    private ConnectionRequest req;
    ArrayList <Plat> plats = new ArrayList();

    public ServicePlat() {
        req=new ConnectionRequest();
    }
     public int  parseNutritioniste(Object objliste )
            {
              Map<String,Object> map =(Map<String,Object>)objliste;


                        return (int)Float.parseFloat(map.get("id").toString());

            }
    
    public Aliment parseAliment(Object objliste )
            {
                 Aliment a = null ;
              Map<String,Object> map =(Map<String,Object>)objliste;

                             a = new Aliment((int)Float.parseFloat(map.get("id").toString()), map.get("nom").toString(), (int)Float.parseFloat(map.get("lipides").toString()),(int)Float.parseFloat(map.get("glucides").toString()) , (int)Float.parseFloat(map.get("proteines").toString()), (int)Float.parseFloat(map.get("poid").toString()));
                             a.setTags(parsetag(map.get("tagNourriture")));
                             System.out.println(a.getTags());
                        return a;

            }
            
        public List<Composition> parseCompostion(Object objliste )
            {
                List<Composition> compositions= new ArrayList();
              List<Map<String,Object>> list =(List<Map<String,Object>>)objliste;
                        for (Map<String,Object> objetape :list )
                        {
                            compositions.add(new Composition((int)Float.parseFloat(objetape.get("id").toString()),Float.parseFloat(objetape.get("poid").toString()), parseAliment(objetape.get("aliment"))));
                        }   
                        return compositions;

            }
        
    
    public List<EtapeDePreparation> parseEtapeDePreparation(Object objliste)
            {
                List<EtapeDePreparation> etapes= new ArrayList();
              List<Map<String,Object>> listetape =(List<Map<String,Object>>)objliste;
                        for (Map<String,Object> objetape :listetape )
                        {

                            etapes.add(new EtapeDePreparation((int)Float.parseFloat(objetape.get("ordre").toString()),(int)Float.parseFloat(objetape.get("duree").toString()), objetape.get("description").toString()));
                        }   
                        return etapes;

            }
    

     public List<TagNourriture> parsetag(Object objtag)
            {
             List <TagNourriture> tags = new ArrayList();
              List<Map<String,Object>> listtags =(List<Map<String,Object>>)objtag;
                        for (Map<String,Object> oobjtag :listtags )
                        {
                            Object objMultimedia= oobjtag.get("contenuMultimedia");

                                    ServiceMultimedia sC=new ServiceMultimedia();

            tags.add(new TagNourriture(sC.parseContenuMultimedia(oobjtag.get("contenuMultimedia"))));
        }
                       
                           
                        return tags;

            }

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
                        Plat p =new Plat(obj.get("description").toString(), (int)Float.parseFloat(obj.get("nbrportion").toString()),(int)Float.parseFloat(obj.get("id").toString()) ,obj.get("nom").toString() ,Float.parseFloat(obj.get("lipides").toString()),Float.parseFloat(obj.get("glucides").toString()) , Float.parseFloat(obj.get("proteines").toString()),Float.parseFloat(obj.get("poid").toString()) , n );
                        p.setEtapesDePreparation(parseEtapeDePreparation(obj.get("etapeDePreparation")));
                        p.setCompostions(parseCompostion(obj.get("compostions")));
                        p.setTags(parsetag(obj.get("tagNourriture")));
                       
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
        System.out.println(plats);
        return plats;
  
                
        }
    
    
}
