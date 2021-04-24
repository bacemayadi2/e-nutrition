package e.nutrition.Services;

import e.nutrition.Utils.DataSource;
import e.nutrition.Models.Nutritionniste;
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
public class ServiceNutritionniste implements IService <Nutritionniste>
{
    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void Add(Nutritionniste t) 
    {
        try
        {
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
            ps.setString(9, "nutritionniste");
            ps.setString(10, t.getRoles().toString());
            ps.setBoolean(11, false);
            ps.setString(12, "00000000");
            ps.executeUpdate();
            
//            req = "";
//            ps = cnx.prepareStatement(req);
//            
//            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nutritionniste ajouté !!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Nutritionniste non ajouté !!");
            JOptionPane.showMessageDialog(null, "Error !!" + e.getMessage());
        }
    }

    @Override
    public void Delete(Nutritionniste t) 
    {
        try
        {
            String req = "DELETE FROM nutritionniste WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Personne supprimé !!");
        }
        catch(SQLException e)
        {
            System.err.println("echec de l'operation!!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void Update(Nutritionniste t) 
    {
        try
        {
            String req = "UPDATE nutritionniste SET nom=?, prenom=?, sexe=?, dateNaiss=?, email=?, tel=?, ville=?, adresse=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getSexe());
            ps.setDate(4, t.getDateNaiss());
            ps.setString(5, t.getEmail());
            ps.setInt(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse()); 
            ps.setInt(9, t.getId());
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Nutritionniste> Display() 
    {
        ObservableList<Nutritionniste> oblist = FXCollections.observableArrayList();
        try
        {
            String req = "SELECT t1.id as id, t1.nom as nom , t1.prenom as prenom, t1.sexe as sexe, t1.date_naiss as date_naiss ,\n" +
"t1.email as email, t1.tel as tel , t1.ville as ville , t1.adresse as adresse, t1.is_verified as isverified\n" +
"FROM nutritionniste t0 INNER JOIN utilisateur t1 ON t0.id = t1.id";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {                
                oblist.add(new Nutritionniste(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }
    
    
}
