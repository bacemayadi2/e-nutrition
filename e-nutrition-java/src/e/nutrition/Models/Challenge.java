package e.nutrition.Models;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author ALADIN
 */
public class Challenge 
{
    private int id;
    private String titre;
    private String description;
    private String categorie;
    private Date dateDebut;
    private Date dateFin;
    
    public Challenge(int id, String titre, String description, String categorie, Date dateDebut, Date dateFin) 
    {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Challenge(String titre, String description, String categorie, Date dateDebut, Date dateFin) {
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Challenge() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Challenge: " + "id: " + id + ", titre: " + titre + ", description: " + description + ", categorie: " + categorie +
                ", dateDebut: " + dateDebut + ", dateFin: " + dateFin + "\n";
    }
}
