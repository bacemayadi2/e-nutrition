/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

/**
 *
 * @author Admin
 */
public class Medicament {
    
  private int id;
  private String nom;
  private String quantite;
  private String duree;

    public Medicament(int id, String nom, String quantite, String duree) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.duree = duree;
    }

    public Medicament(String nom, String quantite, String duree) {
        this.nom = nom;
        this.quantite = quantite;
        this.duree = duree;
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

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Medicament{" + "id=" + id + ", nom=" + nom + ", quantite=" + quantite + ", duree=" + duree + '}';
    }
    
    
    
    
    
    
}
