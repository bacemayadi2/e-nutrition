package e.nutrition.Models;

import java.sql.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class User 
{
    protected int id;
    protected String nom;
    protected String prenom;
    protected String sexe;
    protected Date dateNaiss;
    protected String email;
    protected int tel;
    protected String ville;
    protected String adresse;
    protected boolean isVerified = false;
    protected JSONArray roles ;
    
    public User(int id) 
    {
        this.id = id;
    }

    public User(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse) 
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.roles = new JSONArray();
        this.roles.put("ROLE_USER");
    }

    public User(String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville, String adresse ) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.roles = new JSONArray();
        this.roles.put("ROLE_USER");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Date getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(Date dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public JSONArray getRoles() {
        return roles;
    }

    public void setRoles(JSONArray roles) {
        this.roles = roles;
    }

    
    
    

    @Override
    public String toString() 
    {
        return "User: " + "nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", dateNaiss=" + dateNaiss + ", email=" + email + ", tel=" + tel + ", ville=" + ville + ", adresse=" + adresse + ", isVerified=" + isVerified + " || ";
    }
}
