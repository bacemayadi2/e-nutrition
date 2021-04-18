/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.Services;
import e.nutrition.Models.Medicament;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Admin
 */
public class ServiceMedicament implements IService <Medicament> {
   
    
   Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
        public void Add(Medicament t) 
    {
        try
        {
            String req = "INSERT INTO medicament (nom, quantite, duree) VALUES "
                    + "(?,?,?)             ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getQuantite());
            ps.setString(3, t.getDuree());
          
            ps.executeUpdate();
            System.out.println("medicament ajoutée !!");
        }
        catch(SQLException e)
        {
            System.out.println("medicament non ajoutée !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(Medicament t) {

         try
        {
            String req = "DELETE FROM medicament WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("medicament supprimée !!");
        }
        catch(SQLException e)
        {
            System.err.println("echec de la suppression!!");
            System.err.println(e.getMessage());
        }
        
    }

    @Override
    public void Update(Medicament t) {
        
        
        try
        {
            String req = "UPDATE medicament SET nom=?, quantite=?, duree=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getNom());
            ps.setString(2, t.getQuantite());
            ps.setString(3, t.getDuree());
            ps.setInt(4, t.getId());
            ps.executeUpdate();
            System.out.println("Success update !!");
        }
        catch (SQLException e)
        {
            System.out.println("Updated failed !!!");
            System.err.println(e.getMessage());
        }
        
      
    }

     public ObservableList<Medicament> Display() 
    {
        ObservableList<Medicament> oblist = FXCollections.observableArrayList();
        //List <Challenge> list = new ArrayList<>();
        try
        {
            String req = "SELECT * FROM medicament";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(new Medicament(rs.getInt(1),rs.getString(2), rs.getString(3),rs.getString(4)));
                
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }     
 
    
    
    
    
}
