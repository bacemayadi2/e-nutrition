/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models.tags;

import e.nutrition.Models.ContenuMultimedia;

/**
 *
 * @author bacem
 */
public class TagFicheConsultation extends Tag{

    public TagFicheConsultation(int id, ContenuMultimedia contenuMultimedia) {
        super(id, contenuMultimedia);
    }

    public TagFicheConsultation(ContenuMultimedia contenuMultimedia) {
        super(contenuMultimedia);
    }
    
}
