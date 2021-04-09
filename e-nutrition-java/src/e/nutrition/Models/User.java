package e.nutrition.Models;

import java.sql.Date;

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
    protected String tel;
    protected String ville;
    protected String adresse;

    public User(int id) 
    {
        this.id = id;
    }
    
    public User(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, String tel, String ville, String adresse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
    }

    public User(String nom, String prenom, String sexe, Date dateNaiss, String email, String tel, String ville, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
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

    @Override
    public String toString() 
    {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", dateNaiss=" + dateNaiss + ", email=" + email + ", tel=" + tel + ", ville=" + ville + ", adresse=" + adresse + '}';
    }
}
