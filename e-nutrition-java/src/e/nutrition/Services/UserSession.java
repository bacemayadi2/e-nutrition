package e.nutrition.Services;

import e.nutrition.Models.User;
import e.nutrition.Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.swing.JOptionPane;

/**
 *
 * @author ALADIN
 */
public class UserSession 
{
    private static UserSession instance;
    private static User user;

    static Connection cnx = DataSource.getInstance().getCnx();
    
            
    public static boolean login(String credential, String password) throws SQLException 
    {
        Optional<User> possibleUser = UserSession.getInstnace().validateLogin(credential, password);
        
        System.out.println("passible user: " + possibleUser);
        
        if(possibleUser.isPresent())
        {
            user = possibleUser.get();
            try
            {
                System.out.println("id user: " + user.getId());
                PreparedStatement ps = cnx.prepareStatement("SELECT roles FROM utilisateur WHERE id=?");
                ps.setInt(1, user.getId());
                ResultSet rs = ps.executeQuery();
                String roles = "";
                while(rs.next())
                {
                    roles = rs.getObject(1).toString();
                }
                System.out.println("roles::" + roles);
            }
            catch(SQLException e)
            {
                System.out.println("error !!" + e.getMessage());
            }
            return true;
        }
        JOptionPane.showMessageDialog(null, "Vérifier les informations de connexion SVP !!!");
        return false;
    }
    
    public Optional<User> validateLogin(String credential, String password) throws SQLException 
    {
        PreparedStatement ps = cnx.prepareStatement("SELECT * FROM utilisateur WHERE email=? ");
        ps.setString(1, credential);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && password.equals(rs.getString("password")))
        {
            return Optional.of(resultSetToUser(rs));
        }
        return Optional.empty();
    }
    
    public static User getUser() 
    {
        return user;
    }

    public void cleanUserSession()
    {
        instance = null;
    }
    
    public static UserSession getInstnace() {
        if (instance == null)
            return instance = new UserSession();
        return instance;
    }
    
    private User resultSetToUser(ResultSet rs) throws SQLException 
    {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setEmail(rs.getString("email"));
        u.setNom(rs.getString("nom"));
        u.setPrenom(rs.getString("prenom"));
        u.setSexe(rs.getString("sexe"));
        u.setDateNaiss(rs.getDate("date_naiss"));
        u.setTel(rs.getInt("tel"));
        u.setVille(rs.getString("ville"));
        u.setAdresse(rs.getString("adresse"));
        u.setStringRoles(rs.getObject("roles").toString());
        u.setIsVerified(rs.getBoolean("is_verified"));
        return u;
    }
}
