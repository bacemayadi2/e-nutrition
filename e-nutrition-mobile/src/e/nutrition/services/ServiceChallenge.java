package e.nutrition.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import e.nutrition.entities.Challenge;
import e.nutrition.entities.ContenuMultimedia;
import e.nutrition.entities.EtapeDePreparation;
import e.nutrition.entities.tags.ChallengeTag;
import e.nutrition.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.exec.util.StringUtils;

/**
 *
 * @author ALADIN
 */
public class ServiceChallenge 
{
    
    public boolean resultOK;
    private ConnectionRequest req;
    ArrayList <Challenge> challenges = new ArrayList<>();

    
    public ServiceChallenge() {
        req=new ConnectionRequest();
    }
    public List<EtapeDePreparation> parseEtapeDePreparation(Object objliste)
            {
                List<EtapeDePreparation> etapes= new ArrayList();
              List<Map<String,Object>> listetape =(List<Map<String,Object>>)objliste;
                        for (Map<String,Object> objetape :listetape )
                        {
                            etapes.add(new EtapeDePreparation((int)Float.parseFloat(objetape.get("ordre").toString()),(int)Float.parseFloat(objetape.get("duree").toString()), objetape.get("description").toString()));
                        }   
                        System.out.println(etapes);
                        return etapes;

            }
    
        public List<ChallengeTag> parsetag(Object obj)
            {
             List <ChallengeTag> tags = new ArrayList();
              List<Map<String,Object>> listtags =(List<Map<String,Object>>)obj;
                        for (Map<String,Object> objtag :listtags )
                        {
                            Object objMultimedia= objtag.get("contenuMultimedia");
                            String multimedia =objMultimedia.toString();
                            if (multimedia != null)
                            {
                               String nom= StringUtils.split(multimedia, "=")[1];
                               nom = nom.substring(0, nom.length()-1) ;
                                                 System.out.println(nom);
                                tags.add(new ChallengeTag(new ContenuMultimedia(nom)));
                            }                        
                        }  
                        
                        System.out.println(tags);
                        return tags;
            }
    

    public ArrayList<Challenge> parseChallenge(String jsonText) throws ParseException{
        {
        JSONParser j = new JSONParser();
                try 
                {
                    Map<String,Object> challengesJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    List<Map<String,Object>> list =(List<Map<String,Object>>)challengesJson.get("root");
                    
                    for (Map<String,Object> obj :list)
                    {                        
                        Challenge challenge = new Challenge(
                                    obj.get("titre").toString(), 
                                    obj.get("categorie").toString(),
                                    obj.get("description").toString(),
                                    new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateDebut").toString().substring(0, 10)),
                                    new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateFin").toString().substring(0, 10)));
                                    
                        challenges.add(challenge);
                    }
                } catch (IOException ex) {
                }
            return challenges; 
        }
    }
    
    public ArrayList<Challenge> getAllChallenges()
    {
        String url = Statics.BASE_URL+"/api/displayCallenges";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    challenges = parseChallenge(new String(req.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error!!" + ex.getMessage());
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return challenges;
    }
    
}
