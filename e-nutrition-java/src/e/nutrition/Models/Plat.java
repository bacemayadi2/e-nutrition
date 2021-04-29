/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author bacem
 */
public class Plat extends Nourriture {
   private String description;
   private int nbrportion;
   private List <EtapeDePreparation> etapesDePreparation = new ArrayList();
   private List <Composition> compostions = new ArrayList();

    public Plat(String nom) {
        super(nom);
    }

   
     public void ajouterEtapeDePreparation(EtapeDePreparation e)
  {
      if (e != null)
      {
          etapesDePreparation.add(e);
      }
  } 
  public void supprimerEtapeDePreparation (EtapeDePreparation e)
  {
      if (e != null)
      {
          etapesDePreparation.remove(e);
      }
  }
  
       public void ajouterCompostions(Composition c)
  {
      if (c != null)
      {
          compostions.add(c);
      }
      calculeNutritiveValue();
  } 
  public void supprimerCompostions (Composition c)
  {
      if (c != null)
      {
          compostions.remove(c);
      }
      calculeNutritiveValue();
  }
  
  public void calculeNutritiveValue()
  {
   this.lipidies=0;
   this.glucides=0;
   this.proteines=0;
   Composition composition=null;
   if (compostions.size() !=0 )
   {
       Iterator<Composition>  i= compostions.iterator();
        while(i.hasNext())
        { 
        composition = i.next();

         this.lipidies+=((composition.getAliment().getLipidies())/(composition.getAliment().getPoid()))* composition.getPoid();
         this.glucides+=((composition.getAliment().getGlucides())/(composition.getAliment().getPoid()))* composition.getPoid();
         this.proteines+=((composition.getAliment().getProteines())/(composition.getAliment().getPoid()))* composition.getPoid();


        }
  }
  }

    public Plat(int id ,String description, int nbrportion) {
        this.id =id;
        this.description = description;
        this.nbrportion = nbrportion;
    }

    public Plat(String description, int nbrportion, int id, String nom, float lipidies, float glucides, float proteines, float poid, int userId) {
        super(id, nom, lipidies, glucides, proteines, poid, userId);
        this.description = description;
        this.nbrportion = nbrportion;
    }

    public Plat(String description, int nbrportion, String nom, float lipidies, float glucides, float proteines, float poid, int userId) {
        super(nom, lipidies, glucides, proteines, poid, userId);
        this.description = description;
        this.nbrportion = nbrportion;
    }

    public Plat() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrportion() {
        return nbrportion;
    }

    public void setNbrportion(int nbrportion) {
        this.nbrportion = nbrportion;
    }

    public List<EtapeDePreparation> getEtapesDePreparation() {
        return etapesDePreparation;
    }

    public void setEtapesDePreparation(List<EtapeDePreparation> etapesDePreparation) {
        this.etapesDePreparation = etapesDePreparation;
    }

    public List<Composition> getCompostions() {
        return compostions;
    }

    public void setCompostions(List<Composition> compostions) {
        this.compostions = compostions;
    }
  
   
    
}
