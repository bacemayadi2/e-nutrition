package e.nutrition.Models;

import java.sql.Date;

/**
 *
 * @author ALADIN
 */
public class Patient extends User
{
    private String styleDeVie;

    public Patient(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse, String styleDeVie, boolean isVerified) {
        
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.styleDeVie = styleDeVie;
    }

    public Patient(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville, 
            String adresse, String styleDeVie, boolean isVerified) {
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
        return "Patient: " + super.toString() + "styleDeVie=" + styleDeVie + "\n";
    }
}
