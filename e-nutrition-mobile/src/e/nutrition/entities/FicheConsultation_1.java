/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import e.nutrition.entities.tags.TagFicheConsultation;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class FicheConsultation_1 {
    private int id;
    private Date creation_date;
    private float poids;
    private float taille;
    private String symptome;
    private String apetit;
      private String description;
      private int patient;
      private String nompatient;
   private int nutritionniste;
   private String nomnutritionniste;
     private List <TagFicheConsultation> tags = new ArrayList();
 
    public void ajoutertag(TagFicheConsultation t) {
      if (t != null)
      {
          tags.add(t);
      }
    }

    public void supprimertag(TagFicheConsultation t) {
     if (t!=null)
      {
          tags.remove(t);
      }
    }

    public List<TagFicheConsultation> getTags() {
        return tags;
    }

    public void setTags(List<TagFicheConsultation> tags) {
        this.tags = tags;
    }
        
    
    
    

    public String getNompatient() {
        return nompatient;
    }

    public void setNompatient(String nompatient) {
        this.nompatient = nompatient;
    }

    public String getNomnutritionniste() {
        return nomnutritionniste;
    }

    public void setNomnutritionniste(String nomnutritionniste) {
        this.nomnutritionniste = nomnutritionniste;
    }
   
    
 
   private List <Medicament> medicaments = new ArrayList();

    public FicheConsultation_1(int id, Date creation_date, float poids, float taille, String symptome, String apetit, String description,int patient,int nutritionniste) {
        this.id = id;
        this.creation_date = creation_date;
        this.poids = poids;
        this.taille = taille;
        this.symptome = symptome;
        this.apetit = apetit;
        this.description = description;
        this.patient=patient;
          this.nutritionniste=nutritionniste;
   
    }

     public FicheConsultation_1(Date creation_date, float poids, float taille, String symptome, String apetit, String description,int patient,int nutritionniste) {
        this.creation_date = creation_date;
        this.poids = poids;
        this.taille = taille;
        this.symptome = symptome;
        this.apetit = apetit;
        this.description = description;
        this.patient=patient;
         this.nutritionniste=nutritionniste;
        
    }
 
    public FicheConsultation_1(int id, Date creation_date, float poids, float taille, String symptome, String apetit, String description) {
        this.id = id;
        this.creation_date = creation_date;
        this.poids = poids;
        this.taille = taille;
        this.symptome = symptome;
        this.apetit = apetit;
        this.description = description;
       
    }

    public FicheConsultation_1(Date creation_date, float poids, float taille, String symptome, String apetit, String description) {
        this.creation_date = creation_date;
        this.poids = poids;
        this.taille = taille;
        this.symptome = symptome;
        this.apetit = apetit;
        this.description = description;
       
    }
    
    
    public List<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public String getApetit() {
        return apetit;
    }

    public void setApetit(String apetit) {
        this.apetit = apetit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getNutritionniste() {
        return nutritionniste;
    }

    public void setNutritionniste(int nutritionniste) {
        this.nutritionniste = nutritionniste;
    }

  public float getCalculerImc()
    {
        return (this.getPoids()/this.getTaille());
    }

    @Override
    public String toString() {
        return "FicheConsultation{" + "id=" + id + ", creation_date=" + creation_date + ", poids=" + poids + ", taille=" + taille + ", symptome=" + symptome + ", apetit=" + apetit + ", description=" + description + '}';
    }

    
   
   
    
    
    
    
    
    
    
    
    
}
