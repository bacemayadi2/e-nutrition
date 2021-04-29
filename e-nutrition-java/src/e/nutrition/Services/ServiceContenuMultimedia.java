/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Composition;
import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.Plat;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceContenuMultimedia {
    Connection cnx = DataSource.getInstance().getCnx();

  
    public int Add(ContenuMultimedia c) 
    {
        int generatedID=0;

        try 
      {
    String reqContenuM="INSERT INTO contenu_multimedia (updated_at, description, nom_file, dtype) VALUES (?, ?, ?, ?)";
            PreparedStatement psContenuM= cnx.prepareStatement(reqContenuM, Statement.RETURN_GENERATED_KEYS );
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            psContenuM.setTimestamp(1, date);
            psContenuM.setString(2, c.getDescription());
            psContenuM.setString(3, c.getNomFile());
            psContenuM.setString(4, "contenumultimedia");
            psContenuM.executeUpdate();
                   ResultSet rS = psContenuM.getGeneratedKeys();
            if (rS.next()) {
                generatedID= rS.getInt(1);
            }
             
      }  
      
      
            catch (SQLException ex) {
            Logger.getLogger(ServiceContenuMultimedia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedID;

    }


    public void Delete(ContenuMultimedia c) 
    {
           try 
      {
        String req = "DELETE FROM pidev3a.contenu_multimedia  WHERE id = ?"    ;
        PreparedStatement   ps= cnx.prepareStatement(req);
        ps.setInt(1, c.getId());
        ps.executeUpdate();
          System.out.println("contenuemultimediaSupprimer");
      }    
        catch (SQLException ex) 
        {
            Logger.getLogger(ServiceContenuMultimedia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  
    public void Update(ContenuMultimedia c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    public ContenuMultimedia rechercher(int id) {

        ContenuMultimedia c = null;
  try
        {
            String req = "SELECT id , updated_at ,description , nom_file , dtype FROM contenu_multimedia  WHERE id = ?"    ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
             c = new ContenuMultimedia(rs.getInt("id"),rs.getString("nom_file"),rs.getString("description"),rs.getDate("updated_at"));
        }
        catch (SQLException e)
        {
            System.out.println("error Contenu Multmedia");
            System.err.println(e.getMessage());
        }
        return c;   
    }

    
}
