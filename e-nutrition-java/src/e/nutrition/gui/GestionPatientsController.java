package e.nutrition.gui;

import e.nutrition.Models.Patient;
import e.nutrition.Models.User;
import e.nutrition.Services.ServicePatient;
import e.nutrition.Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import org.json.JSONArray;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class GestionPatientsController implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private TableView<Patient> tableview_patients;
    @FXML
    private TableColumn<Patient, String> table_email;
    @FXML
    private TableColumn<Patient, String> table_nom;
    @FXML
    private TableColumn<Patient, String> table_prenom;
    @FXML
    private TableColumn<Patient, String> table_sexe;
    @FXML
    private TableColumn<Patient, String> table_dateNaiss;
    @FXML
    private TableColumn<Patient, String> table_tel;
    @FXML
    private TableColumn<Patient, String> table_ville;
    @FXML
    private TableColumn<Patient, String> table_adresse;
    @FXML
    private TableColumn<Patient, String> table_isVerified;
    @FXML
    private TableColumn<Patient, String> table_style;
    @FXML
    private TableColumn<Patient, String> table_roles;

    
    private final ServicePatient sp = new ServicePatient();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
            refreshTableView();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        Connection cnx = DataSource.getInstance().getCnx();
        try
        {
            PreparedStatement ps = cnx.prepareStatement("SELECT roles FROM utilisateur ");
            ResultSet rs = ps.executeQuery();
            List<String> list = new ArrayList<>();
            
            while(rs.next())
            {
                list.add(rs.getObject(1).toString());
            }
            
             for(String i : list)
            {
                System.out.println("role " + i);
                if(i.contains("ROLE_ADMIN"))
                {
                    System.out.println("admiin");
                }
            }
            
            
        }
        catch(SQLException e)
        {
            System.out.println("error !!" + e.getMessage());
        }
        
       
        
    }    

    @FXML
    private void btn_delete_patient(ActionEvent event) throws SQLException 
    {
        Patient patient = tableview_patients.getSelectionModel().getSelectedItem();
        
        if(patient != null )
        {
            String[] options = {"Oui", "Non"};
            int n = JOptionPane.showOptionDialog(null,
            "Confirmation de la suppression ??",
            "Suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //do not use a custom Icon
            options,  //the titles of buttons
            options[0]); //default button title
        
            if(n == 0)
            {
                sp.Delete(patient);
                refreshTableView();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Sélectionner un patient à supprimer SVP !!!");
        }
        
    }
    
    @FXML
    private void btn_search(ActionEvent event) throws SQLException 
    {
        if(search_field.getText().isEmpty())
        {
            refreshTableView();
        }
        else
        {
            table_email.setCellValueFactory(new PropertyValueFactory("email"));
            table_nom.setCellValueFactory(new PropertyValueFactory("nom"));
            table_prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
            table_sexe.setCellValueFactory(new PropertyValueFactory("sexe"));
            table_dateNaiss.setCellValueFactory(new PropertyValueFactory("dateNaiss"));
            table_tel.setCellValueFactory(new PropertyValueFactory("tel"));
            table_ville.setCellValueFactory(new PropertyValueFactory("ville"));
            table_adresse.setCellValueFactory(new PropertyValueFactory("adresse"));
            table_isVerified.setCellValueFactory(new PropertyValueFactory("isVerified"));
            table_style.setCellValueFactory(new PropertyValueFactory("styleDeVie"));
            table_roles.setCellValueFactory(new PropertyValueFactory("stringRoles"));
            tableview_patients.setItems(sp.Search(search_field.getText()));
        }
    }
    
    private void refreshTableView() throws SQLException
    {
        table_email.setCellValueFactory(new PropertyValueFactory("email"));
        table_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        table_prenom.setCellValueFactory(new PropertyValueFactory("prenom"));
        table_sexe.setCellValueFactory(new PropertyValueFactory("sexe"));
        table_dateNaiss.setCellValueFactory(new PropertyValueFactory("dateNaiss"));
        table_tel.setCellValueFactory(new PropertyValueFactory("tel"));
        table_ville.setCellValueFactory(new PropertyValueFactory("ville"));
        table_adresse.setCellValueFactory(new PropertyValueFactory("adresse"));
        table_isVerified.setCellValueFactory(new PropertyValueFactory("isVerified"));
        table_style.setCellValueFactory(new PropertyValueFactory("styleDeVie"));
        table_roles.setCellValueFactory(new PropertyValueFactory("stringRoles"));
        tableview_patients.setItems(sp.Display());
    }
}
