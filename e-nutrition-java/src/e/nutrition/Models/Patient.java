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
public class Patient extends User
{
    private String styleDeVie;

    public Patient(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, String tel, String ville,
            String adresse, String styleDeVie) {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.styleDeVie = styleDeVie;
    }

    public Patient(String nom, String prenom, String sexe, Date dateNaiss, String email, String tel, String ville, 
            String adresse, String styleDeVie) {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.styleDeVie = styleDeVie;
    }

    public String getStyleDeVie() 
    {
        return styleDeVie;
    }

    public void setStyleDeVie(String styleDeVie)
    {
        this.styleDeVie = styleDeVie;
    }

    @Override
    public String toString() 
    {
        return "Patient{" + "styleDeVie=" + styleDeVie + '}';
    }
}
