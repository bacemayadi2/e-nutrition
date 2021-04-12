package e.nutrition.Services;

import e.nutrition.Models.Challenge;
import e.nutrition.Models.Patient;
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
public class ServicePatient implements IService<Patient>
{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void Add(Patient t) {
        
    }

    @Override
    public void Delete(Patient t) {
        
    }

    @Override
    public void Update(Patient t) {
        
    }

    @Override
    public ObservableList<Patient> Display() 
    {
            ObservableList<Patient> oblist = FXCollections.observableArrayList();
            //List <Patient> list = new ArrayList<>();
            try
            {
                String req = "SELECT * FROM patient";
                PreparedStatement ps = cnx.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                while(rs.next())
                {
                    oblist.add(new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3), rs.getDate(4),
                            rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
                }
            }
            catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
            return oblist;
    }
}
