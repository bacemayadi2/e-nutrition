package e.nutrition.Models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ALADIN
 */
public class Secretaire extends User
{
    private int nutritionniste;

    public Secretaire(int id, String nom, String prenom, String sexe, Date dateNaiss,
            String email, int tel, String ville, String adresse, int nutritionniste) 
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.nutritionniste = nutritionniste;
    }

    public Secretaire(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse, int nutritionniste) {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
    }
    
    public int getNutritionniste() {
        return nutritionniste;
    }

    public void setNutritionniste(int nutritionniste) {
        this.nutritionniste = nutritionniste;
    }

    @Override
    public String toString() 
    {
        return "Secretaire{" + "nutritionniste=" + nutritionniste + '}';
    }
    
    
    
}
