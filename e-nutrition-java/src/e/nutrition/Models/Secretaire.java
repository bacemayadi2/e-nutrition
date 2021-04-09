/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import java.sql.Date;

/**
 *
 * @author ALADIN
 */
public class Secretaire extends User
{
    private Nutritionniste nutritionniste;

    public Secretaire(Nutritionniste nutritionniste, int id, String nom, String prenom, String sexe, Date dateNaiss,
            String email, String tel, String ville, String adresse) 
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.nutritionniste = nutritionniste;
    }

    public Secretaire(Nutritionniste nutritionniste, String nom, String prenom, String sexe, Date dateNaiss,
            String email, String tel, String ville, String adresse) 
    {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.nutritionniste = nutritionniste;
    }

    public Nutritionniste getNutritionniste() {
        return nutritionniste;
    }

    public void setNutritionniste(Nutritionniste nutritionniste) {
        this.nutritionniste = nutritionniste;
    }

    @Override
    public String toString() 
    {
        return "Secretaire{" + "nutritionniste=" + nutritionniste + '}';
    }
    
    
    
}
