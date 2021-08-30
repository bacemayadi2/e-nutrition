package e.nutrition.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
import javax.swing.JOptionPane;
/**
 *
 * @author ALADIN
 */
public class DataSource 
{
    private static DataSource instance;
    private Connection cnx;
                                 
    private final String URL ="jdbc:mysql://adminn.cam6mhx2dtqf.eu-west-3.rds.amazonaws.com:8080/pidev3a";
    private final String LOGIN = "adminn";
    private final String PASSWORD = "19190011";
    
//    private final String URL = "jdbc:mysql://localhost:3306/pidev";
//    private final String LOGIN = "root";
//    private final String PASSWORD = "";
    
    private DataSource()
    {
        try
        {
            cnx = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("You are connecting to the database !!!");
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Connection failed with the database !!!");
            JOptionPane.showMessageDialog(null, "Error !!" + ex.getMessage());
        }
    }
    
    public static DataSource getInstance()
    {
        if(instance == null)
        {
            instance = new DataSource();
        }
        return instance;
    }
    
    public Connection getCnx()
    {
        return cnx;
    }
}
