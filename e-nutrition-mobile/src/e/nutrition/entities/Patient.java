package e.nutrition.entities;

import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class Patient extends User
{
    private String styleDeVie;

    public Patient(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse, String styleDeVie, boolean isVerified) 
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse, isVerified);
        this.styleDeVie = styleDeVie;
    }

    //Display patients
    public Patient(int id, String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, String styleDeVie, boolean isVerified, String stringRoles)
    {
        super(id, email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, stringRoles);
        this.styleDeVie = styleDeVie;
    }
    
    public Patient(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, JSONArray roles, String password, String styleDeVie) 
    {
        
        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, roles, password);
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
        return "Patient: " + super.toString() + "styleDeVie=" + styleDeVie + "\n";
    }
}
