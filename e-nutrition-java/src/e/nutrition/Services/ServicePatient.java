package e.nutrition.Services;

import e.nutrition.Models.Patient;
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
public class ServicePatient implements IService<Patient>
{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Add(Patient t) 
    {
        try
        {
            System.out.println(t.toString());
            
            String req = "INSERT INTO utilisateur (email, nom, prenom, sexe, date_naiss, tel, ville, adresse, dtype, roles, is_verified, password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getSexe());
            ps.setDate(5, t.getDateNaiss());
            ps.setInt(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse());
            ps.setString(9, "patient");
            ps.setString(10, t.getRoles().toString());
            ps.setBoolean(11, t.isIsVerified());
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
            req = "INSERT INTO patient (id, style_de_vie) VALUES (?,?)";
            ps = cnx.prepareStatement(req);
            
            ps.setInt(1, id);
            ps.setString(2, t.getStyleDeVie());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Patient ajouter !!");
            System.out.println("Patient ajouter !!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Patient non ajouter !!");
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    public void Delete(Patient t)
    {
        try
        {
            String req = "DELETE FROM utilisateur WHERE id=?";
            String req1 = "DELETE FROM patient WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            PreparedStatement ps1 = cnx.prepareStatement(req1);
            ps.setInt(1, t.getId());
            ps1.setInt(1, t.getId());
            ps.executeUpdate();
            ps1.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Patient supprimé !!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "echec de la suppression!!");
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
        }
    }

    @Override
    public void Update(Patient t) 
    {
        try
        {
            String req = "UPDATE utilisateur SET email=?, nom=?, prenom=?, sexe=?, date_naiss=?, tel=?, ville=?, adresse=? WHERE id=?";
            String req1 = "UPDATE utilisateur SET style_de_vie=? WHERE id=?";
            
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getEmail());
            ps.setString(2, t.getNom());
            ps.setString(3, t.getPrenom());
            ps.setString(4, t.getSexe());
            ps.setDate(5, t.getDateNaiss()); 
            ps.setInt(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse());
            ps.setInt(9, t.getId());
            ps.executeUpdate();
            
            ps = cnx.prepareStatement(req1);
            ps.setString(8, t.getStyleDeVie());
            
            JOptionPane.showMessageDialog(null, "Mise a jour réussi");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Echec de mise à jour !!!");
            JOptionPane.showMessageDialog(null, "Error !!" + e.getMessage());
        }
    }

    @Override
    public ObservableList<Patient> Display() 
    {
            ObservableList<Patient> oblist = FXCollections.observableArrayList();
            try
            {
                String req = "SELECT t1.nom as nom , t1.prenom as prenom, t1.sexe as sexe, t1.date_naiss as date_naiss ,\n" +
                            "t1.email as email, t1.tel as tel , t1.ville as ville , t1.adresse as adresse, t0.style_de_vie as style, \n" +
                            "t1.is_verified as isverified FROM patient t0 INNER JOIN utilisateur t1 ON t0.id = t1.id";
                
                PreparedStatement ps = cnx.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    oblist.add(new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
                            rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBoolean(10)));
                }
            }
            catch (SQLException e)
            {
                JOptionPane.showMessageDialog(null, "Error !!" + e.getMessage());
            }
            return oblist;
    }
}
