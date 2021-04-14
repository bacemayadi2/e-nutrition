/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import java.util.Objects;

/**
 *
 * @author bacem
 */
public class Nourriture {
    protected int id;
    protected String nom;
    protected float lipidies,glucides,proteines,poid;
    protected int userId;

    public float calculerCalorie()
    {
        return this.glucides*4 + this.proteines*9 + this.lipidies*9;
    }

    public Nourriture() {
    }

    public Nourriture(int id, String nom, float lipidies, float glucides, float proteines, float poid, int userId) {
        this.id = id;
        this.nom = nom;
        this.lipidies = lipidies;
        this.glucides = glucides;
        this.proteines = proteines;
        this.poid = poid;
        this.userId = userId;
    }

    public Nourriture(String nom, float lipidies, float glucides, float proteines, float poid, int userId) {
        this.nom = nom;
        this.lipidies = lipidies;
        this.glucides = glucides;
        this.proteines = proteines;
        this.poid = poid;
        this.userId = userId;
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

    public int getUser() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  "nom=" + nom + ", poid=" + poid + ", lipidies=" + lipidies + ", glucides=" + glucides + ", proteines=" + proteines + '}';
    }
    

    
}
