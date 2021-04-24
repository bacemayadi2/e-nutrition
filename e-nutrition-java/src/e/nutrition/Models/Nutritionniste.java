package e.nutrition.Models;

import java.sql.Date;

/**
 *
 * @author ALADIN
 */
public class Nutritionniste extends User
{
    private int secretaire;

    public Nutritionniste(int id) {
        super(id);
    }
    
    public Nutritionniste(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel,
            String ville, String adresse, int secretaire)
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaire = secretaire;
    }

    public Nutritionniste(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel,
            String ville, String adresse)
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
    }
    
    public Nutritionniste(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse, int secretaire) 
    {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaire = secretaire;
    }

    public int getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(int secretaire) {
        this.secretaire = secretaire;
    }
    
    @Override
    public String toString() 
    {
        return super.toString() + "secretaire:" + secretaire + "\n";
    }
}
