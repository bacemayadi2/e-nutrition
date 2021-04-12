package e.nutrition.Services;

import e.nutrition.Utils.DataSource;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Patient;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            String req = "INSERT INTO nutritionniste (nom, prenom, sexe, dateNaiss, email, tel, ville, adresse, secretaires) VALUES "
                    + "(?,?,?,?,?,?,?,?,?)             ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getSexe());
            ps.setDate(4, t.getDateNaiss());
            ps.setString(5, t.getEmail());
            ps.setString(6, t.getTel());
            ps.setString(7, t.getVille());
            ps.setString(8, t.getAdresse());
//            ps.setArray(9, t.getSecretaires());
            ps.executeUpdate();
            System.out.println("Personne ajouter !!");
        }
        catch(SQLException e)
        {
            System.out.println("Personne non ajouter !!");
            System.out.println(e.getMessage());
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
            ps.setString(6, t.getTel());
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
        //List <Nutritionniste> list = new ArrayList<>();
        try
        {
            String req = "SELECT * FROM nutritionniste";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(new Nutritionniste(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3),
                        rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }  
}
