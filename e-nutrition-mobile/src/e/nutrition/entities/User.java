package e.nutrition.entities;

import e.nutrition.entities.tags.TagUtilisateur;
//import e.nutrition.Utils.DataSource;
//import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;

/**
 *
 * @author ALADIN
 */
public class User 
{
    protected int id;
    protected String email;
    protected String nom;
    protected String prenom;
    protected String sexe;
    protected Date dateNaiss;
    protected int tel;
    protected String ville;
    protected String adresse;
    protected boolean isVerified = false;
    protected JSONArray roles;
    protected String stringRoles;
    protected String password;
         private List<TagUtilisateur> tags = new ArrayList();

    public TagUtilisateur getPhotoDeProfile()
    {
        Iterator<TagUtilisateur>  i= tags.iterator();
        while(i.hasNext())
        {    
            TagUtilisateur t= i.next();
             if ( t.is_photo_de_profile() )
                 return t;
        }
        
      return null;
    }    
    public void ajoutertag(TagUtilisateur t) {
      if (t != null)
      {
          tags.add(t);
      }
    }

    public void supprimertag(TagUtilisateur t) {
     if (t!=null)
      {
          tags.remove(t);
      }
    }

    public List<TagUtilisateur> getTags() {
        return tags;
    }

    public void setTags(List<TagUtilisateur> tags) {
        this.tags = tags;
    }
    
    public User() 
    {
        //Empty contructor
    }
    
    public User(int id) 
    {
        this.id = id;
    }

    public User(int id, String nom, String prenom, String sexe, Date dateNaiss, String email, int tel, String ville,
            String adresse, boolean isVerified) 
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
    }

    //register
    public User(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, JSONArray roles, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.email = email;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.roles = roles;
        this.password = password;
    }

    //display users with id
    public User(int id, String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, String stringRoles) {
        this.id = id;
        this.email = email;                
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.isVerified = isVerified;
        this.stringRoles = stringRoles;
    }

    //display users
     public User(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified, String stringRoles) {
        this.email = email;                
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.isVerified = isVerified;
        this.stringRoles = stringRoles;
    }
    
    public User(String email, String nom, String prenom, String sexe, Date dateNaiss, int tel, String ville,
            String adresse, boolean isVerified) {
        this.email = email;                
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaiss = dateNaiss;
        this.tel = tel;
        this.ville = ville;
        this.adresse = adresse;
        this.isVerified = false;
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

    public void setRoles(JSONArray roles)
    {
        this.roles = roles;
    }
    
    public String getStringRoles() {
        return stringRoles;
    }

    public void setStringRoles(String stringRoles) {
        this.stringRoles = stringRoles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Override
    public String toString() 
    {
        return "User: " + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", dateNaiss=" + dateNaiss + ", email=" + email + ", tel=" + tel + ", ville=" + ville + ", adresse=" + adresse + ", isVerified=" + isVerified + " || ";
    }
    

}
