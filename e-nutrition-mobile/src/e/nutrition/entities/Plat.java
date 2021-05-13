/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.entities;

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
   private float duree;

    public Plat(String nom) {
        super(nom);
    }

   
     public void ajouterEtapeDePreparation(EtapeDePreparation e)
  {
      if (e != null)
      {
          etapesDePreparation.add(e);
          this.calculerduree();

      }
  } 
    
  public void calculerduree()
  {
      this.duree=0;
      for (int i=0;i<etapesDePreparation.size();i++)
      {
              duree+=etapesDePreparation.get(i).getDuree()      ;
    
      }
   
  }
  public void supprimerEtapeDePreparation (EtapeDePreparation e)
  {
      if (e != null)
      {
          etapesDePreparation.remove(e);
          this.calculerduree();
      }
  }

    public float getDuree() {
        return duree;
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
  public int getSelectedAlimentsCalorie()
  {
      float tmplipid=0;
      float tmpprotein=0;
      float tmpglucide=0;
      Composition composition=null;

       if (compostions.size() !=0 )
   {
       Iterator<Composition>  i= compostions.iterator();
        while(i.hasNext())
        { 
        composition = i.next();
        if(composition.getAliment().isSelected())
        {
         tmplipid+=((composition.getAliment().getLipidies())/(composition.getAliment().getPoid()))* composition.getPoid();
         tmpglucide+=((composition.getAliment().getGlucides())/(composition.getAliment().getPoid()))* composition.getPoid();
         tmpprotein+=((composition.getAliment().getProteines())/(composition.getAliment().getPoid()))* composition.getPoid();
        }

        }
        
  }
      return Math.round((tmpglucide*4 + tmpprotein*9 +tmplipid*9)/(float)this.nbrportion);
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

    public Plat(String description, int nbrportion, int id, String nom, float lipidies, float glucides, float proteines, float poid, Nutritionniste n) {
        super(id, nom, lipidies, glucides, proteines, poid, n);
        this.description = description;
        this.nbrportion = nbrportion;
    }

    public Plat(String description, int nbrportion, String nom, float lipidies, float glucides, float proteines, float poid, Nutritionniste n) {
        super(nom, lipidies, glucides, proteines, poid, n);
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
             this.calculerduree();
              
    }

    public List<Composition> getCompostions() {
        return compostions;
    }

    public void setCompostions(List<Composition> compostions) {
        this.compostions = compostions;
    }
    
    public void recalculatewithNewPortion(int newNbPortion)
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
        composition.setPoid((composition.getPoid()/this.nbrportion)*newNbPortion);

         this.lipidies+=((composition.getAliment().getLipidies())/(composition.getAliment().getPoid()))* composition.getPoid();
         this.glucides+=((composition.getAliment().getGlucides())/(composition.getAliment().getPoid()))* composition.getPoid();
         this.proteines+=((composition.getAliment().getProteines())/(composition.getAliment().getPoid()))* composition.getPoid();

        }
                 nbrportion=newNbPortion;

  }
        
    }
  
   
    
}
