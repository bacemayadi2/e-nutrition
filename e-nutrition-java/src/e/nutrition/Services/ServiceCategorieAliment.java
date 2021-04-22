/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Challenge;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceCategorieAliment implements IService<CategorieAliment>{

        Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Add(CategorieAliment c) {
               try
        {
            String req = "INSERT INTO categorie_aliment  (nom_categorie ) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNomCategorie());
            ps.executeUpdate();
            System.out.println("categorie ajouter !!");
        }
        catch(SQLException e)
        {
            System.out.println("categorie non ajouter !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(CategorieAliment c) {
        if (c.getNumberOfTimeUsed()==0)
        {
            
       try
        {
            String req = "DELETE FROM categorie_aliment WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getId());
            ps.executeUpdate();
            System.out.println("categorie supprimé !!");
        }
               catch(SQLException e)
        {
            System.out.println("categorie non supprimer !!");
            System.out.println(e.getMessage());
        }
        }
        else
        System.out.println("impossible de  supprimer une categorie utliser par d'autre aliment");

        
    }

    @Override
    public void Update(CategorieAliment c) {
        
       try
        {
            String req = "UPDATE  categorie_aliment SET nom_categorie=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, c.getNomCategorie());
            ps.setInt(2, c.getId());
            ps.executeUpdate();
            System.out.println("categorie modifier !!");
        }
        catch(SQLException e)
        {
            System.out.println("categorie non modifier !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<CategorieAliment> Display() {
                ObservableList<CategorieAliment> oblist = FXCollections.observableArrayList();

  try
        {
            String req = "SELECT * FROM categorie_aliment";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               CategorieAliment c=new CategorieAliment(rs.getInt("id"),rs.getString("nom_categorie"));
              String req2 = "SELECT t1.id as id, t1.nom as nom, t1.lipides as lipides, t1.glucides as glucides, t1.proteines as protenies, t1.poid as poid, t0.code_abarre as codeABarre , t1.nutritionniste_id as user_id , t1.dtype FROM aliment t0 INNER JOIN nourriture t1 ON t0.id = t1.id INNER JOIN aliment_categorie_aliment ON t0.id = aliment_categorie_aliment.aliment_id WHERE aliment_categorie_aliment.categorie_aliment_id = ?"    ;
              PreparedStatement ps2 = cnx.prepareStatement(req2);
              ps2.setInt(1, rs.getInt("id"));
              ResultSet rs2 = ps2.executeQuery();
              while(rs2.next())
              {
                  c.ajouterAliment(new Aliment(rs2.getInt("id"), rs2.getString("nom"), rs2.getFloat("lipides"), rs2.getFloat("glucides"),rs2.getFloat("protenies"), rs2.getFloat("poid"), rs2.getString("codeABarre"), rs2.getInt("user_id")));
              }
              
              oblist.add(c);
                
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;    }
    
    public CategorieAliment rechercherExactCategorie(String nom ) {
     CategorieAliment c=null;

        try
        {
            String req = "SELECT  * FROM categorie_aliment where nom_categorie = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, nom);
            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
               c=new CategorieAliment(rs.getInt("id"),rs.getString("nom_categorie"));            
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return c;    }
    
}
