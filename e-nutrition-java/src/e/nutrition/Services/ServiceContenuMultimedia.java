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
import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bacem
 */
public class ServiceContenuMultimedia {
    Connection cnx = DataSource.getInstance().getCnx();

  
    public void Add(ContenuMultimedia t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void Delete(ContenuMultimedia t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    public void Update(ContenuMultimedia t) {
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
