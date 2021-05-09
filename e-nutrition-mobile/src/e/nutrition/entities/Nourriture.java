/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import com.codename1.rad.models.Entity;
import e.nutrition.entities.tags.TagNourriture;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author bacem
 */
public class Nourriture extends Entity {
    protected int id;
    protected String nom;
    protected float lipidies,glucides,proteines,poid;
    protected Nutritionniste nutritionniste;
    private List <TagNourriture> tags = new ArrayList();
    
    public void ajoutertag(TagNourriture t) {
      if (t != null)
      {
          tags.add(t);
      }
    }

    public void supprimertag(TagNourriture t) {
     if (t!=null)
      {
          tags.remove(t);
      }
    }
        

    
    public List<TagNourriture> getTags() {
        return tags;
    }

    public Nourriture(String nom) {
        this.nom = nom;
    }
    



    public int getCalculerCalorie()
    {
        return Math.round(this.glucides*4 + this.proteines*9 + this.lipidies*9);
    }

    public Nourriture() {
    }

    public Nourriture(int id, String nom, float lipidies, float glucides, float proteines, float poid) {
        this.id = id;
        this.nom = nom;
        this.lipidies = lipidies;
        this.glucides = glucides;
        this.proteines = proteines;
        this.poid = poid;
    }
    

    public Nourriture(int id, String nom, float lipidies, float glucides, float proteines, float poid, Nutritionniste n) {
        this.id = id;
        this.nom = nom;
        this.lipidies = lipidies;
        this.glucides = glucides;
        this.proteines = proteines;
        this.poid = poid;
        this.nutritionniste = n;
    }

    public Nourriture(String nom, float lipidies, float glucides, float proteines, float poid, Nutritionniste nutritionniste) {
        this.nom = nom;
        this.lipidies = lipidies;
        this.glucides = glucides;
        this.proteines = proteines;
        this.poid = poid;
        this.nutritionniste = nutritionniste;
    }


    
       


    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nourriture other = (Nourriture) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTags(List<TagNourriture> tags) {
        this.tags = tags;
    }

    public float getLipidies() {
        return lipidies;
    }

    public void setLipidies(float lipidies) {
        this.lipidies = lipidies;
    }

    public float getGlucides() {
        return glucides;
    }

    public void setGlucides(float glucides) {
        this.glucides = glucides;
    }

    public float getProteines() {
        return proteines;
    }

    public void setProteines(float proteines) {
        this.proteines = proteines;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }



    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "nom=" + nom + ", poid=" + poid + ", lipidies=" + lipidies + ", glucides=" + glucides + ", proteines=" + proteines + '}';
    }

    public Nutritionniste getNutritionniste() {
        return nutritionniste;
    }

    public void setNutritionniste(Nutritionniste nutritionniste) {
        this.nutritionniste = nutritionniste;
    }

    
        public String getDType()
    {
        return "nourriture";
    }

        

        

    
}
