/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities.tags;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.User;

/**
 *
 * @author bacem
 */
public class ChallengeTag extends Tag{

    public ChallengeTag(int id, ContenuMultimedia contenuMultimedia,int user_id) {
        super(id, contenuMultimedia);
    }

    public ChallengeTag(ContenuMultimedia contenuMultimedia,int user_id) {
        super(contenuMultimedia);
    }
    
    private int user_id ;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
        
}
