package e.nutrition.entities;

import com.codename1.rad.models.Entity;
import e.nutrition.entities.tags.ChallengeTag;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ALADIN
 */
public class Challenge extends Entity
{
    private int id;
    private String titre;
    private String description;
    private String categorie;
    private Date dateDebut;
    private Date dateFin;
    private List <ChallengeTag> tags = new ArrayList();
    
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
        //Empty constructor
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
    
    
    
    public void ajoutertag(ChallengeTag t) {
      if (t != null)
      {
          tags.add(t);
      }
    }

    public List<ChallengeTag> getTags() {
        return tags;
    }

    public void setTags(List<ChallengeTag> tags) {
        this.tags = tags;
    }

    public void supprimertag(ChallengeTag t) {
     if (t!=null)
      {
          tags.remove(t);
      }
    }

    @Override
    public String toString() {
        return "Challenge: " + "id: " + id + ", titre: " + titre + ", description: " + description + ", categorie: " + categorie +
                ", dateDebut: " + dateDebut + ", dateFin: " + dateFin + "\n";
    }
}
