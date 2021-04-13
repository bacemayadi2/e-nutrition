package e.nutrition.Services;

import e.nutrition.Models.Challenge;
import e.nutrition.Utils.DataSource;
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
public class ServiceChallenge implements IService<Challenge>
{

    Connection cnx = DataSource.getInstance().getCnx();
    
    @Override
    public void Add(Challenge t)
    {
        try
        {
            String req = "INSERT INTO challenge (titre, description, categorie, date_debut, date_fin) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getCategorie());
            ps.setDate(4, t.getDateDebut());
            ps.setDate(5, t.getDateFin());
            ps.executeUpdate();
            System.out.println("Challenge ajouter !!");
        }
        catch(SQLException e)
        {
            System.out.println("Challenge non ajouter !!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void Delete(Challenge t) 
    {
        try
        {
            String req = "DELETE FROM challenge WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
            System.out.println("Challenge supprimé !!");
        }
        catch(SQLException e)
        {
            System.err.println("echec de la suppression!!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void Update(Challenge t) 
    {
        try
        {
            String req = "UPDATE challenge SET titre=?, description=?, categorie=?, date_debut=?, date_fin=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getDescription());
            ps.setString(3, t.getCategorie());
            ps.setDate(4, t.getDateDebut());
            ps.setDate(5, t.getDateFin()); 
            ps.setInt(6, t.getId());
            ps.executeUpdate();
            System.out.println("Success update !!");
        }
        catch (SQLException e)
        {
            System.out.println("Updated failed !!!");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public ObservableList<Challenge> Display() 
    {
        ObservableList<Challenge> oblist = FXCollections.observableArrayList();
        //List <Challenge> list = new ArrayList<>();
        try
        {
            String req = "SELECT * FROM challenge";
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                oblist.add(new Challenge(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getDate(6)));
                
            }
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return oblist;
    }
    
}
