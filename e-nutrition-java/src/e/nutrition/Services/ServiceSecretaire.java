package e.nutrition.Services;

import e.nutrition.Models.Secretaire;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author ALADIN
 */
public class ServiceSecretaire implements IService<Secretaire>
{
    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void Add(Secretaire t) {
        try
        {
            String req = "INSERT INTO utilisateur (email, nom, prenom, sexe, date_naiss, tel, ville, adresse, dtype, is_verified, roles, password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getSexe());
            ps.setDate(5, t.getDateNaiss());
            ps.setInt(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse());
            ps.setString(9, "secretaire");
            ps.setBoolean(10, false);
            ps.setString(11, t.getRoles().toString());
            ps.setString(12, "00000000");
            ps.executeUpdate();
                       
            
            req = "SELECT MAX(id) FROM utilisateur";
            ps = cnx.prepareStatement(req);
            
            int id=0;
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                id = rs.getInt(1);
            }
            
            System.out.println("getid: " + id);
            System.out.println("getdoc: " + t.getNutritionniste());
            
            req = "INSERT INTO secretaire (id, nutritionniste_id) VALUES (?,?)";
            ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.setObject(2, null);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Secretaire ajouté !!!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Secretaire non ajouté !!");
            JOptionPane.showMessageDialog(null, "Erreur !!" + e.getMessage());
            System.out.println("message" + e.getMessage());
        }
    }

    @Override
    public void Delete(Secretaire t) 
    {
        try
        {
            String req = "DELETE FROM utilisateur WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Secrétaire supprimé !!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "echec de la suppression!!");
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
        }
    }

    @Override
    public void Update(Secretaire t) {
         try
        {
            String req = "UPDATE secretaire SET nom=?, prenom=?, sexe=?, dateNaiss=?, email=?, tel=?, ville=?, adresse=?, nutritionniste=?  WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getSexe());
            ps.setDate(4, t.getDateNaiss());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse());
            ps.setObject(9, t.getNutritionniste()); 
            ps.setInt(10, t.getId());
            ps.executeUpdate();
            System.out.println("Success update !!!");
        }
        catch (SQLException e)
        {
            System.out.println("Updated failed !!!");
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public ObservableList<Secretaire> Display() 
    {
        ObservableList<Secretaire> oblist = FXCollections.observableArrayList();
        try
        {
            String req = "SELECT * FROM secretaire t0 INNER JOIN utilisateur t1 ON t0.id = t1.id";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {                                
                oblist.add(new Secretaire(rs.getInt(1), rs.getString(8), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getDate(7), rs.getInt(9), rs.getString(11), rs.getString(12), rs.getBoolean(14),
                        rs.getObject(15).toString(), rs.getInt(2)));
            }
        }
        catch (SQLException e)
        { 
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
        }
        return oblist;
    }
}
