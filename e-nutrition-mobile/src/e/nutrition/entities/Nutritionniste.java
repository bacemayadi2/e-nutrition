package e.nutrition.entities;

import e.nutrition.entities.tags.TagUtilisateur;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class Nutritionniste extends User
{
    private int secretaire;
     private List <FicheConsultation> fiches = new ArrayList();

    
    public Nutritionniste(int id) {
        super(id);
    }
    

//    public Nutritionniste(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville, String adresse, boolean isVerified, JSONArray roles, int secretaire) {
//        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, roles);
//        this.secretaire = secretaire;
//    }

    //Display doctors with id
    public Nutritionniste(int id, String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville, String adresse, boolean isVerified, String stringRoles, int secretaire) {
        super(id, email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, stringRoles);
        this.secretaire = secretaire;
    }
    //Display doctors without id
    public Nutritionniste(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville, String adresse, boolean isVerified, String stringRoles, int secretaire) {
        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, stringRoles);
        this.secretaire = secretaire;
    }

    
    //register
    public Nutritionniste(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel,
            String ville, String adresse, boolean isVerified, JSONArray roles, String password, int secretaire)
    {
        super(email, nom, prenom, sexe, dateNaiss, tel, ville, adresse, isVerified, roles, password);
        this.secretaire = secretaire;
    }

    public Nutritionniste(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville, String adresse, boolean isVerified) 
    {
        super(id, nom, prenom, sexe, dateNaiss, email, tel, ville, adresse, isVerified);
    }

    public int getSecretaire() {
        return secretaire;
    }

    public void setSecretaire(int secretaire) {
        this.secretaire = secretaire;
    }

    public List<FicheConsultation> getFiches() {
        return fiches;
    }

    public void setFiches(List<FicheConsultation> fiches) {
        this.fiches = fiches;
    }


    @Override
    public String toString() 
    {
        return super.toString() + "secretaire:" + secretaire + "\n";
    }
}
