/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models.tags;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.tags.Tag;

/**
 *
 * @author bacem
 */
public class TagNourriture extends Tag {
    
    public TagNourriture(int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
    }

    public TagNourriture(ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
    }

  
}
