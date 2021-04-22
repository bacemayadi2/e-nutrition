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
public class Aliment extends Nourriture   {
   private String codeABarre=null;
   private List <CategorieAliment> categories = new ArrayList();
   private List <Composition> compostions = new ArrayList();
    //private List <Proportion> proportions = new ArrayList();
   public String getCategorieString() 
   {
       String nomCategories="";
         Iterator<CategorieAliment>  i= categories.iterator();
        while(i.hasNext())
        {     
            nomCategories+=i.next().getNomCategorie()+ " ";
        }
   return nomCategories;   
  }
  public int getNumberOfTimeUsed()
  {
      return compostions.size();
  }
    
  public void ajouterCategorie(CategorieAliment c)
  {
      if (c != null)
      {
          categories.add(c);
      }
  }
  
  public void supprimerCategorie(CategorieAliment c)
  {
      if (c!=null)
      {
          categories.remove(c);
      }
  }
  
    public void ajouterCompostion(Composition c)
  {
      if (c != null)
      {
          compostions.add(c);
      }
  }
  
  public void supprimerComposition(Composition c)
  {
      if (c!=null)
      {
          compostions.remove(c);
      }
  }

    public String getCodeABarre() {
        return codeABarre;
    }

    public void setCodeABarre(String codeABarre) {
        this.codeABarre = codeABarre;
    }

    public List<CategorieAliment> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorieAliment> categories) {
        this.categories = categories;
    }

    public List<Composition> getCompostions() {
        return compostions;
    }

    public void setCompostions(List<Composition> compostions) {
        this.compostions = compostions;
    }

    @Override
    public String toString() {
        return "Aliment{" +super.toString() + ", categories=" + getCategorieString()+ '}';
    }

    public Aliment(int id, String nom, float lipidies, float glucides, float proteines, float poid ,String codeABarre, int userId) {
        super(id, nom, lipidies, glucides, proteines, poid, userId);
        this.codeABarre=codeABarre;
    }

    public Aliment() {
    }

    public Aliment(String nom, float lipidies, float glucides, float proteines, float poid,String codeABarre, int userId) {
        super(nom, lipidies, glucides, proteines, poid, userId);
        this.codeABarre=codeABarre;
    }
    
   @Override
    public String getDType()
    {
        return "aliment";
    }
    
  
  
  


    
    
}
