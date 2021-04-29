package e.nutrition.Services;

import e.nutrition.Utils.DataSource;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Models.tags.TagUtilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ServiceTag sT=new ServiceTag();

    
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
            
            int id=0;
            ps = cnx.prepareStatement("SELECT MAX(id) FROM utilisateur");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                id = rs.getInt(1);
            }
            
            ps = cnx.prepareStatement("INSERT INTO nutritionniste (id) VALUES (?)");
            ps.setInt(1, id);
            
            ps.executeUpdate();
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
            PreparedStatement ps = cnx.prepareStatement("DELETE FROM nutritionniste WHERE id=?");
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            ps = cnx.prepareStatement("DELETE FROM utilisateur WHERE id=?");
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Docteur supprimé !!");
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "echec de la suppression!!");
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
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
            String req = "SELECT * FROM nutritionniste t0 INNER JOIN utilisateur t1 ON t0.id = t1.id";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {    
                oblist.add(new Nutritionniste(rs.getInt(1), rs.getString(7), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getDate(6), rs.getInt(8), rs.getString(10), rs.getString(11), rs.getBoolean(13),
                        rs.getObject(14).toString(), rs.getInt(2)));
                
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error !!!" + e.getMessage());
        }
        return oblist;
    }
    
          public Nutritionniste getById(int id) {
          // Nutritionniste a = null;
         String requete = "SELECT t1.id as id, t1.nom as nom , t1.prenom as prenom, t1.sexe as sexe, "
                 + "t1.date_naiss as date_naiss ,t1.email as email, t1.tel as tel , t1.ville as ville , t1.adresse as adresse, t1.is_verified as isverified "
                 + "FROM nutritionniste t0 INNER JOIN utilisateur t1 ON t0.id = t1.id where t1.id='"+id+"'" ;
        try {
           
                PreparedStatement ps = cnx.prepareStatement(requete);
                ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                Nutritionniste a = new Nutritionniste(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getDate(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getBoolean(10));
                  
                sT.Display("tagutilisateur", a.getId()).forEach( (tag)-> {
               a.ajoutertag((TagUtilisateur)tag);

                });
                        return a ;

            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceNutritionniste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        return null ;
        
    }
}
