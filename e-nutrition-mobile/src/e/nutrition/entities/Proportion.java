/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

import com.codename1.rad.models.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author bacem
 */
public class Proportion extends Entity{
    private int id ;
    private float poid,calorie,proteines,glucides,lipides;
    private Date dateConsommation;

    private Aliment aliment ;

    public Proportion(int id, float poid, float calorie, float proteines, float glucides, float lipides, Date dateConsommation, Aliment aliment) {
        this.id = id;
        this.poid = poid;
        this.calorie = calorie;
        this.proteines = proteines;
        this.glucides = glucides;
        this.lipides = lipides;
        this.dateConsommation = dateConsommation;
        this.aliment = aliment;
    }

    public float getProteines() {
        return proteines;
    }

    public void setProteines(float proteines) {
        this.proteines = proteines;
    }

    public float getGlucides() {
        return glucides;
    }

    public void setGlucides(float glucides) {
        this.glucides = glucides;
    }

    public float getLipides() {
        return lipides;
    }

    public void setLipides(float lipides) {
        this.lipides = lipides;
    }

    


    @Override
    public String toString() {
        return "Proportion{" + "id=" + id + ", poid=" + poid + ", calorie=" + calorie + ", dateConsommation=" + dateConsommation + ", aliment=" + aliment + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public float getCalorie() {
        return calorie;
    }

    public void setCalorie(float calorie) {
        this.calorie = calorie;
    }

    public Date getDateConsommation() {
        return dateConsommation;
    }

    public void setDateConsommation(Date dateConsommation) {
        this.dateConsommation = dateConsommation;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }
     


    
}
