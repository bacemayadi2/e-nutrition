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
            ps.setInt(2, t.getNutritionniste());
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
            String req = "DELETE FROM secretaire WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Secretaire supprimé !!!");
        }
        catch(SQLException e)
        {
            System.err.println("Echec de la suppression !!!");
            System.err.println(e.getMessage());
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
            String req = "SELECT t1.id as id, t1.nom as nom , t1.prenom as prenom, t1.sexe as sexe, t1.date_naiss as date_naiss , t1.email as email, t1.tel as tel , t1.ville as ville , t1.adresse as adresse, t0.nutritionniste_id as nutritionniste_id  FROM secretaire t0 INNER JOIN utilisateur t1 ON t0.id = t1.id";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(new Secretaire(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
                        rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10)));
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
        }
        return oblist;
    }
}
