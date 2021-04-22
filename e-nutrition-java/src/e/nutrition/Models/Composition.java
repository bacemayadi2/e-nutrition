/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

/**
 *
 * @author bacem
 */
public class Composition {
   private int id;
   private float poid;
   private Aliment aliment;
   private Plat plat;

    @Override
    public String toString() {
        return "Composition{" + "id=" + id + ", poid=" + poid + ", aliment=" + aliment + ", plat=" + plat + '}';
    }
   
   public float calculerCalorieParpoid()
   {
   return ( (this.aliment.getCalculerCalorie()/this.aliment.getPoid())*this.poid )   ; 
   }

    public Composition(int id, float poid, Aliment aliment, Plat plat) {
        this.id = id;
        this.poid = poid;
        this.aliment = aliment;
        this.plat = plat;
    }

    public Composition(float poid, Aliment aliment, Plat plat) {
        this.poid = poid;
        this.aliment = aliment;
        this.plat = plat;
    }

    public float getPoid() {
        return poid;
    }

    public void setPoid(float poid) {
        this.poid = poid;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public Plat getPlat() {
        return plat;
    }

    public void setPlat(Plat plat) {
        this.plat = plat;
    }

    public int getId() {
        return id;
    }
    
   
   
}
