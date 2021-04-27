/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models.tags;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.User;

/**
 *
 * @author bacem
 */
public class TagUtilisateur extends Tag{

    public TagUtilisateur(int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
    }

    public TagUtilisateur(ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
    }
    
    private boolean photo_de_profile=false;
     
    public void setPhotoDeProfile()
    {
        photo_de_profile=true;
    }
    public void unSetPhotoDeProfile()
    {
        photo_de_profile=false;
    }

    public boolean is_photo_de_profile() {
        return photo_de_profile;
    }
    
}
