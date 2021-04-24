package e.nutrition.Models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class Nutritionniste extends User
{
    private JSONArray secretaires;

    public Nutritionniste(int id) {
        super(id);
    }
    
    public Nutritionniste(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel,
            String ville, String adresse)
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaires = new JSONArray();
    }

    public Nutritionniste(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse) 
    {
        super(nom, prenom, sexe, dateNaiss, email, tel, ville, adresse);
        this.secretaires = new JSONArray();
    }

    public JSONArray getSecretaires() {
        return secretaires;
    }

    public void setSecretaires(JSONArray secretaires) {
        this.secretaires = secretaires;
    }

    public void AddSecretaire(Secretaire secretaire)
    {
        this.secretaires.put(secretaire.getId());
    }
    
    public void RemoveSecretaire(Secretaire secretaire)
    {
        // TODO: 
    }
    
    @Override
    public String toString() 
    {
        return "Nutritionniste: " + "secretaires:" + secretaires;
    }
}
