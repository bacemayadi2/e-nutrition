/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Models;

import e.nutrition.Models.tags.TagNourriture;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bacem
 */
public interface ITag <T> {
  public void ajoutertag(T t);
  public void supprimertag(T t);
    
}
