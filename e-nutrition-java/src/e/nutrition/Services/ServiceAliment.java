/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Composition;
import e.nutrition.Models.Plat;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceAliment implements IService<Aliment>{

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Add(Aliment a) {
           try
        {
 
            //start mere 
            String reqmere = "INSERT INTO nourriture (nom, lipides, glucides, proteines, poid, nutritionniste_id, dtype) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement mere = cnx.prepareStatement(reqmere);
            mere.setString(1, a.getNom());
            mere.setFloat(2, a.getLipidies());
            mere.setFloat(3, a.getGlucides());
            mere.setFloat(4, a.getProteines());
            mere.setFloat(5, a.getPoid());
            mere.setInt(6, a.getUser()); //get from session in futur 
            mere.setString(7,"aliment");
            mere.executeUpdate();
            //fin mere
                       //start lock
            //String reqlocking = "LOCK TABLES nourriture WRITE"; // lock table jusqu'a la ajout de 2 table mere et fille 
           // PreparedStatement lock = cnx.prepareStatement(reqlocking);
           // lock.executeUpdate();
            //fin lock 
            //start fille 
            
            String reqfille="INSERT INTO aliment (id, code_abarre) VALUES ((SELECT MAX(id) from nourriture ),?)  ";
            PreparedStatement fille = cnx.prepareStatement(reqfille);
            fille.setString(1, a.getCodeABarre());
            fille.executeUpdate();
            System.out.println("aliment ajouter !!");
            //fin fille 
            
            //start categorie_aliment
            Iterator<CategorieAliment>  i= a.getCategories().iterator();
            while(i.hasNext())
           {     
              String reqCategorie="INSERT INTO aliment_categorie_aliment (aliment_id, categorie_aliment_id) VALUES (?, ?)";
              PreparedStatement categorie = cnx.prepareStatement(reqCategorie);
              categorie.setInt(1, a.getId());
              categorie.setInt(2, i.next().getId());

           }
            //fin categorie_aliment 
            //start unlock 
          //  String reqUnlocking = "UNLOCK TABLES";
         //   PreparedStatement unlock = cnx.prepareStatement(reqUnlocking);
          //  unlock.executeUpdate();
            //end unlock 


        }
        catch(SQLException e)
        {
            System.out.println("aliment non ajouter !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(Aliment a) {
    if (a.getNumberOfTimeUsed()==0)
        {
            
       try
        {
            String fillereq = "DELETE FROM aliment WHERE id=?";
            PreparedStatement fille = cnx.prepareStatement(fillereq);
            fille.setInt(1, a.getId());
            fille.executeUpdate();
            String merereq = "DELETE FROM nourriture WHERE id=?";
            PreparedStatement mere = cnx.prepareStatement(merereq);
            mere.setInt(1, a.getId());
            mere.executeUpdate();
            String categoriereq= "DELETE FROM aliment_categorie_aliment WHERE aliment_id=?";
            PreparedStatement categorie = cnx.prepareStatement(categoriereq);
            categorie.setInt(1, a.getId());
            categorie.executeUpdate();
            System.out.println("aliment supprimé !!");
        }
               catch(SQLException e)
        {
            System.out.println("aliment non supprimer !!");
            System.out.println(e.getMessage());
        }
        }
        else
        System.out.println("impossible de  supprimer un aliment utliser par des plat");

        
    }

    @Override
    public void Update(Aliment a) {
    try
        {
            System.out.println(a);
              String merereq = "UPDATE  nourriture SET nom=? ,lipides=? ,glucides=?, proteines=? , poid=? WHERE id=?";
            PreparedStatement mere = cnx.prepareStatement(merereq);
            mere.setString(1, a.getNom());
            mere.setFloat(2, a.getLipidies());
            mere.setFloat(3, a.getGlucides());
            mere.setFloat(4, a.getProteines());
            mere.setFloat(5, a.getPoid());
            mere.setInt(6, a.getId());
            mere.executeUpdate();
            String fillereq = "UPDATE  aliment SET code_abarre=?  WHERE id=?";
            PreparedStatement fille = cnx.prepareStatement(fillereq);
            fille.setString(1, a.getCodeABarre());
            fille.setInt(2, a.getId());
            fille.executeUpdate();
            //delete old categorie
            String delcategoriereq= "DELETE FROM aliment_categorie_aliment WHERE aliment_id=?";
            PreparedStatement delcategorie = cnx.prepareStatement(delcategoriereq);
            delcategorie.setInt(1, a.getId());
            delcategorie.executeUpdate();
            //add new categories 
            Iterator<CategorieAliment>  i= a.getCategories().iterator();
            while(i.hasNext())
           {     
              String newreqCategorie="INSERT INTO aliment_categorie_aliment (aliment_id, categorie_aliment_id) VALUES (?, ?)";
              PreparedStatement newcategorie = cnx.prepareStatement(newreqCategorie);
              newcategorie.setInt(1, a.getId());
              newcategorie.setInt(2, i.next().getId());
           }
            
            System.out.println("aliment modifier !!");
            
        }
        catch(SQLException e)
        {
            System.out.println("aliment non modifier !!");
            System.out.println(e.getMessage());
        }
    }
    @Override
    public ObservableList<Aliment> Display() {
               ObservableList<Aliment> oblist = FXCollections.observableArrayList();

  try
        {
            String req = "SELECT t1.id as id, t1.nom as nom, t1.lipides as lipides, t1.glucides as glucides, t1.proteines as protenies, t1.poid as poid, t0.code_abarre as codeABarre , t1.nutritionniste_id as user_id  FROM aliment t0 INNER JOIN nourriture t1 ON t0.id = t1.id "    ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
              Aliment a=new Aliment(rs.getInt("id"), rs.getString("nom"),rs.getFloat("lipides") , rs.getFloat("glucides"), rs.getFloat("protenies"), rs.getFloat("poid"), rs.getString("codeABarre"),rs.getInt("user_id"));
              String req2 = "SELECT t0.id AS id, t0.nom_categorie AS nom_categorie FROM categorie_aliment t0 INNER JOIN aliment_categorie_aliment ON t0.id = aliment_categorie_aliment.categorie_aliment_id WHERE aliment_categorie_aliment.aliment_id = ?"    ;
              PreparedStatement ps2 = cnx.prepareStatement(req2);
              ps2.setInt(1, a.getId());
              ResultSet rsCategoris = ps2.executeQuery();

              while(rsCategoris.next())
              {
                  a.ajouterCategorie(new CategorieAliment(rsCategoris.getInt("id"), rsCategoris.getString("nom_categorie")));
              }
              String req3 = "SELECT t0.id AS id, t0.poid AS poid, t0.aliment_id AS aliment_id, t0.plat_id AS plat_id FROM composition t0 WHERE t0.aliment_id = ?"    ;
              PreparedStatement ps3 = cnx.prepareStatement(req3);
              ps3.setInt(1, a.getId());
              ResultSet rsCompostion = ps3.executeQuery();

                 while(rsCompostion.next())
              {
                String req4 = "SELECT * from plat where id =  (select plat_id from composition where id = ? ) "    ;
                PreparedStatement ps4 = cnx.prepareStatement(req4);
                ps4.setInt(1, rsCompostion.getInt("id"));
                ResultSet rsplat = ps4.executeQuery();
                if (rsplat.next())
                {
                    a.ajouterCompostion(new Composition(rsCompostion.getInt("id"),a ,new Plat(rsplat.getInt("id"), rsplat.getString("description"), rsplat.getString("nbrportion")) ));
                }
              }

              oblist.add(a);
                
            }
        }
        catch (SQLException e)
        {
            System.out.println("error");
            System.err.println(e.getMessage());
        }
        return oblist;   
    }    
    
}
