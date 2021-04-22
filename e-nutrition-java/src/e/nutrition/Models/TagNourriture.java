/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

/**
 *
 * @author bacem
 */
public class TagNourriture extends Tag {
    
    Nourriture nourriture;
    public TagNourriture(int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
    }

    public TagNourriture(ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
    }

    public TagNourriture(Nourriture nourriture, int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
        this.nourriture = nourriture;
    }

    public TagNourriture(Nourriture nourriture, ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
        this.nourriture = nourriture;
    }
    
    @Override
    public  String getDType(){
    return "tagnourriture";
    }
}
