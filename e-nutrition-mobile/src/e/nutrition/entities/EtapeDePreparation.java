/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

/**
 *
 * @author bacem
 */
public class EtapeDePreparation {
    private int id;
    private int order;
    private int duree;
    private String description;
    
    public EtapeDePreparation(int id, int order, int duree, String description) {
        this.id = id;
        this.order = order;
        this.duree = duree;
        this.description = description;
    }

    public EtapeDePreparation(int order, int duree, String description) {
        this.order = order;
        this.duree = duree;
        this.description = description;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EtapeDePreparation{" + "id=" + id + ", order=" + order + ", duree=" + duree + ", description=" + description + '}';
    }
    
    
}
