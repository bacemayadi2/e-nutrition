/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import java.util.Date;

/**
 *
 * @author Ayoub
 */
public class Mesure {
    
    private Integer id;
    private float taille;
    private float poids;
    private Date date_mesure;
    private Patient patient_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getTaille() {
        return taille;
    }

    public void setTaille(float taille) {
        this.taille = taille;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public Date getDate_mesure() {
        return date_mesure;
    }

    public void setDate_mesure(Date date_mesure) {
        this.date_mesure = date_mesure;
    }

       public Patient getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Patient patient_id) {
        this.patient_id = patient_id;
    }
    
    public Mesure() {
    }

    public Mesure(float taille, float poids, Date date_mesure , Patient patient_id) {
        this.taille = taille;
        this.poids = poids;
        this.date_mesure = date_mesure;
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "Mesure{" + "id=" + id + ", taille=" + taille + ", poids=" + poids + ", date_mesure=" + date_mesure + ", user_patient=" + patient_id + '}';
    }

    public void InitSelectedBlog(Mesure mesure) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    
    
}
