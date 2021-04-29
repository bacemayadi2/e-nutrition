package e.nutrition.Models;

import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.roles = new JSONArray();
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
    
    public static ObservableList<User> Display() throws SQLException
    {
        ObservableList<User> list = FXCollections.observableArrayList();
        
        Connection cnx = DataSource.getInstance().getCnx();
        String req = "SELECT email, nom, prenom, sexe, date_naiss, tel, ville, adresse, is_verified, roles  FROM utilisateur";
        
        PreparedStatement ps = cnx.prepareCall(req);
        
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            list.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
            rs.getInt(6), rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getObject(10).toString()));          
        }
        return list;
    }
}
