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
import e.nutrition.entities.FicheConsultation;
import e.nutrition.entities.ContenuMultimedia;

import e.nutrition.entities.tags.TagFicheConsultation;
import e.nutrition.entities.tags.TagNourriture;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.exec.util.StringUtils;

/**
 *
 * @authorS Admin
 */
/*public class ServiceFicheConsultation {
    //singleton
     public static ServiceFicheConsultation instance=null;
     private ConnectionRequest req;
     
     
       public static ServiceFicheConsultation getInstance() {
        if (instance == null) {
            instance = new ServiceFicheConsultation();
        }
        return instance;
    }
       
       
     public ServiceFicheConsultation() {
        req = new ConnectionRequest();
    }   
      */
    
// public ArrayList<FicheConsultation> affichageFiches(){
     
    /* ArrayList<FicheConsultation> result =new ArrayList<>();
     
           String url = Statics.BASE_URL+"/FicheListeMobile";
           System.out.println(url);
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
           
            public void actionPerformed(NetworkEvent evt) {
                 JSONParser jsonp;
            jsonp = new JSONParser();
            
               
            try{
            
             Map<String,Object>mapFicheConsultation=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));    
             List<Map<String,Object>> listOfMaps =(List<Map<String,Object>>) mapFicheConsultation.get("root");
             for(Map<String,Object> obj:listOfMaps){
                FicheConsultation f=new FicheConsultation();
                float id=Float.parseFloat(obj.get("id").toString());
             
                String symptome=obj.get("symptome").toString();
                 String apetit=obj.get("apetit").toString();
                 
                 f.setId((int)id);
                  
                  f.setSymptome(symptome);
                     f.setApetit(apetit);
                  
                    
                 
               //date
           /*    String DateCoverter=obj.get("creation_date").toString().substring(obj.get("creation_date").toString().indexOf("timestamp")+10,obj.get("obj").toString().lastIndexOf(")"));
               Date currentTime=new Date(Double.valueOf(DateCoverter).longValue()*1000);
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
               String dateString =formatter.format(currentTime);
               f.setCreation_date(dateString);
              
             //insert data into arraylist result
             result.add(f);
                 System.out.println( result.add(f));
               
            }
            }catch(Exception ex)
            {
                ex.printStackTrace();
            }
 
            }
         });
         
         NetworkManager.getInstance().addToQueueAndWait(req);
       
         return result;
   
 }
  */
    
    public class ServiceFicheConsultation {
    public boolean resultOK;
    private ConnectionRequest req ;
    ArrayList <FicheConsultation> fiches = new ArrayList();
    

    public ServiceFicheConsultation() {
        req=new ConnectionRequest();
    }
    public int  parseNutritioniste(Object objliste )
            {
              Map<String,Object> map =(Map<String,Object>)objliste;

                System.out.println(map);
                        return (int)Float.parseFloat(map.get("id").toString());
                        

            }
    public int  parsePatient(Object objliste )
            {
              Map<String,Object> map =(Map<String,Object>)objliste;

       System.out.println(map);
                        return (int)Float.parseFloat(map.get("id").toString());

            }

    public ArrayList<FicheConsultation> parseFiche(String jsonText) throws ParseException{
        {
        JSONParser j=new JSONParser();
                try 
                {
                    Map<String,Object> fichesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String,Object>> list =(List<Map<String,Object>>)fichesListJson.get("root");
                    for (Map<String,Object> obj :list)
                    {
                        System.out.println("1");
                         FicheConsultation f = new FicheConsultation(
                                   new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("CreationDate").toString().substring(0,10)),
                                   (int)Float.parseFloat(obj.get("Poids").toString()),
                                   (int)Float.parseFloat(obj.get("Taille").toString()),
                             
                                    obj.get("Symptome").toString(),  
                                    obj.get("Apetit").toString(),
                                    obj.get("Description").toString(),
                                  parsePatient(obj.get("patient")),
                    parseNutritioniste(obj.get("nutritionniste"))
                               );
                 
                            
                            
                                   //(int)Float.parseFloat(obj.get("nutritionniste").toString()),
                               // (int)Float.parseFloat(obj.get("patient").toString()));
                               f.setTags(parsetag(obj.get("tagFicheConsultation")));
                      fiches.add(f);
                        System.out.println(f);
                        //fiches.add(f);
                    }
                } catch (IOException ex) {
                }
                
            return fiches; 
        }
    }
    
    public ArrayList<FicheConsultation> getAllFiches(){
        String url =Statics.BASE_URL+"/FicheListeMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              
                  try {
                    fiches = parseFiche(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error!!" + ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("done");
        return fiches;
  
                
        }
    
    
      public List<TagFicheConsultation> parsetag(Object objtag)
            {
             List <TagFicheConsultation> tags = new ArrayList();
              List<Map<String,Object>> listtags =(List<Map<String,Object>>)objtag;
                        for (Map<String,Object> oobjtag :listtags )
                        {
                            Object objMultimedia= oobjtag.get("contenuMultimedia");

                                    ServiceMultimedia sC=new ServiceMultimedia();

            tags.add(new TagFicheConsultation(sC.parseContenuMultimedia(oobjtag.get("contenuMultimedia"))));
        }
                       
                           
                        return tags;

            }

    
}
 
 
 
 
 
 
