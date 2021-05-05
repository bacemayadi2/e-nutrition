/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models.tags;

import e.nutrition.Models.ContenuMultimedia;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author bacem
 */
public class Tag {
    private int id ;
  
    private ContenuMultimedia contenuMultimedia;


    public Tag(int id, ContenuMultimedia contenuMultimedia) {
        this.id = id;
        this.contenuMultimedia = contenuMultimedia;
    }

    public Tag(ContenuMultimedia contenuMultimedia) {
        this.contenuMultimedia = contenuMultimedia;
    }

    public ContenuMultimedia getContenuMultimedia() {
        return contenuMultimedia;
    }

    public void setContenuMultimedia(ContenuMultimedia contenuMultimedia) {
        this.contenuMultimedia = contenuMultimedia;
    }

    public int getId() {
        return id;
    }
    
    
        public String getExtension()
    {
        String url=this.contenuMultimedia.getNomFile();
        if (url != null)
        {
            return url.split(".")[1];

        }
        return "null";
    }
        
    public String getUrl()
    {
        if (this.contenuMultimedia!= null)
        return "http://127.0.0.1:8000/multimedia/" +this.contenuMultimedia.getNomFile();
        else return "http://127.0.0.1:8000/multimedia/noimage.jpg";
    }
    
    public boolean isImage()
    {
        String[] extensions = { "jpg", "png", "JPG" ,"PNG"};
        return Arrays.stream(extensions).anyMatch(extensions::equals);    // Do some stuff.
    
       
    }
    public boolean isVideo()
    {
        String[] extensions = { "mp4","mkv","mpg","avi","mov","MOV"};
        return Arrays.stream(extensions).anyMatch(extensions::equals);    // Do some stuff.

    }
    
    
  
    
}
