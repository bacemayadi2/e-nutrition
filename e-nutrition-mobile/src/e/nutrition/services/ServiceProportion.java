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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import e.nutrition.entities.Aliment;
import e.nutrition.entities.Nutritionniste;
import e.nutrition.entities.Plat;
import e.nutrition.entities.Proportion;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bacem
 */
public class ServiceProportion {
        ArrayList<Proportion> proportions = new ArrayList();

    ArrayList<Aliment> aliments = new ArrayList();
    public static ServiceProportion instance = null;
    private ConnectionRequest req;

    public static ServiceProportion getInstance() {
        if (instance == null) {
            instance = new ServiceProportion();
        }
        return instance;
    }

    private ServiceProportion() {
        req = new ConnectionRequest();
    }
      public Aliment parseAliment(Object objliste )
            {
                 Aliment a = null ;
              Map<String,Object> map =(Map<String,Object>)objliste;
                        int alimentid =(int)Float.parseFloat(map.get("id").toString());

                        return ServiceAliment.getInstance().getAlimentbyid(alimentid);

            }

    
    public ArrayList<Proportion> parseProportion(String jsonText){
        {
        JSONParser j=new JSONParser();  
                try 
                {   
                    Map<String,Object> platsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    
                    List<Map<String,Object>> list =(List<Map<String,Object>>)platsListJson.get("root");
                    for (Map<String,Object> obj :list)
                    {
                      
                        String datestring=obj.get("date").toString();
                        Proportion p =new  Proportion((int)Float.parseFloat(obj.get("id").toString()), Float.parseFloat(obj.get("poid").toString()),
                                Float.parseFloat(obj.get("calorie").toString()),
                                Float.parseFloat(obj.get("proteines").toString()),
                                Float.parseFloat(obj.get("lipides").toString()),
                                 Float.parseFloat(obj.get("glucides").toString()),
                                new SimpleDateFormat("yyyy-MM-dd-HH:mm").parse(datestring.substring(0, 10) +"-"+ datestring.substring(12,16) ),
                                parseAliment(obj.get("aliment"))
                        );
                        proportions.add(p);
  
                    }
                } catch (IOException ex ) {
                }
                catch(ParseException pe ){
                    System.out.println("error date");
                }
            return proportions; 
        }
    }
    
    public ArrayList<Proportion> getProportion(int iduser){
        String url =Statics.BASE_URL+"/api/getProportion/"+iduser;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                proportions= parseProportion(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return proportions;
  
                
        }
    
    
}
