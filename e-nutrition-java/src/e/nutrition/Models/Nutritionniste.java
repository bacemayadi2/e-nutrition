package e.nutrition.Models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author ALADIN
 */
public class Nutritionniste extends User
{
    private List<Secretaire> secretaires;

    public Nutritionniste(int id) {
        super(id);
    }
    
    public Nutritionniste(List<Secretaire> secretaires, int id, String nom, String prenom, String sexe, Date dateNaiss,
            String email, String tel, String ville, String adresse) {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaires = secretaires;
    }

    public Nutritionniste(List<Secretaire> secretaires, String nom, String prenom, String sexe, Date dateNaiss,
            String email, String tel, String ville, String adresse) {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaires = secretaires;
    }

    public List<Secretaire> getSecretaires() {
        return secretaires;
    }

    public void setSecretaires(List<Secretaire> secretaires) {
        this.secretaires = secretaires;
    }

    @Override
    public String toString() 
    {
        return "Nutritionniste{" + "secretaires=" + secretaires + '}';
    }
    
  
}
