package e.nutrition.Services;

import e.nutrition.Utils.DataSource;
import e.nutrition.Models.Nutritionniste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            String req = "INSERT INTO Nutritionniste (nom, prenom, sexe, dateNaiss, email, tel, ville, adresse, secretaires) VALUES "
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
            String req = "DELETE FROM Nutritionniste WHERE id=?";
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
        
    }

    @Override
    public void Display() 
    {
        
    }

  

      
}
