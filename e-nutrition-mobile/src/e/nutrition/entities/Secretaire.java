package e.nutrition.entities;

import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class Secretaire extends User
{
    private int nutritionniste;

    //Display Secretaire without id
    public Secretaire(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, String stringRoles, int nutritionniste) {
        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, stringRoles);
        this.nutritionniste = nutritionniste;
    }
    
    //Display Secretaire with id
    public Secretaire(int id, String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, String stringRoles, int nutritionniste) {
        super(id, email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, stringRoles);
        this.nutritionniste = nutritionniste;
    }

    
    //register
    public Secretaire(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, JSONArray roles, String password, int nutritionniste) {
        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, roles, password);
        this.nutritionniste = nutritionniste;
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
