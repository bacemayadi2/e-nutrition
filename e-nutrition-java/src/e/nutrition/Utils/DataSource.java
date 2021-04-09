package e.nutrition.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ALADIN
 */
public class DataSource 
{
    private static DataSource instance;
    private Connection cnx;
                                 
    private final String URL ="jdbc:mysql://pidev.cam6mhx2dtqf.eu-west-3.rds.amazonaws.com:8080/pidev3a";
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
            System.out.println("Connecting !!!");
        }
        catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
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
