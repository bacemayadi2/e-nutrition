/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.services;

import com.codename1.rad.models.Tag;
import e.nutrition.entities.ContenuMultimedia;
import e.nutrition.entities.tags.TagNourriture;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bacem
 */
public class ServiceMultimedia {
        public ContenuMultimedia parseContenuMultimedia(Object objliste) 
            {
                ContenuMultimedia contenu=null;
              Map<String,Object> map =(Map<String,Object>)objliste;
                   
              
                                if (map != null)
                           contenu= (new ContenuMultimedia(map.get("nomFile").toString()));
                           
                     
                            return contenu;
            }
   
      
    
    
}
