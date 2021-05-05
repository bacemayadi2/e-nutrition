/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities.tags;

import e.nutrition.entities.ContenuMultimedia;

/**
 *
 * @author bacem
 */
public class TagBlogPost extends Tag{

    public TagBlogPost(int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
    }

    public TagBlogPost(ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
    }
    
}
