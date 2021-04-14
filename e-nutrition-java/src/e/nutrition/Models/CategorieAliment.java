/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bacem
 */
public class CategorieAliment {
   int id;
   private String nomCategorie;
   private List <Aliment> aliments = new ArrayList();

    public int getId() {
        return id;
    }



    public CategorieAliment(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public CategorieAliment() {
    }
 
    
    
 public int getNumberOfTimeUsed()
  {
      return aliments.size();
  }
    
  public void ajouterAliment(Aliment a)
  {
      if (a != null)
      {
          aliments.add(a);
      }
  } 
  public void supprimerAliment (Aliment a)
  {
      if (a != null)
      {
          aliments.remove(a);
      }
  }    
  
    public String getNomCategorie() {
        return nomCategorie;
    }
    
    @Override
    public String toString() {
        return "CategorieAliment{" + "nomCategorie=" + nomCategorie + '}';
    }


    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public List<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(List<Aliment> aliments) {
        this.aliments = aliments;
    }

    public CategorieAliment(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
    }
    

    
}
