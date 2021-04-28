package e.nutrition.gui;

import e.nutrition.Models.Nutritionniste;
import e.nutrition.Services.ServiceNutritionniste;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class GestionDoctorsController implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private TableView<Nutritionniste> tableview_doctors;
    @FXML
    private TableColumn<Nutritionniste, String> table_email;
    @FXML
    private TableColumn<Nutritionniste, String> table_nom;
    @FXML
    private TableColumn<Nutritionniste, String> table_prenom;
    @FXML
    private TableColumn<Nutritionniste, String> table_sexe;
    @FXML
    private TableColumn<Nutritionniste, String> table_dateNaiss;
    @FXML
    private TableColumn<Nutritionniste, String> table_tel;
    @FXML
    private TableColumn<Nutritionniste, String> table_ville;
    @FXML
    private TableColumn<Nutritionniste, String> table_adresse;
    @FXML
    private TableColumn<Nutritionniste, String> table_isVerified;
    @FXML
    private TableColumn<Nutritionniste, String> table_secretaire;
    @FXML
    private TableColumn<Nutritionniste, String> table_roles;

    private final ServiceNutritionniste sn = new ServiceNutritionniste();
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
    }    

    @FXML
    private void btn_delete_doctor(ActionEvent event) throws SQLException 
    {
        Nutritionniste nutritionniste = tableview_doctors.getSelectionModel().getSelectedItem();
        
        if(nutritionniste != null )
        {
            String[] options = {"Oui", "Non"};
            int n = JOptionPane.showOptionDialog(null,
            "Confirmation de la suppression ??",
            "Suppression",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,     //custom Icon
            options,  //the titles of buttons of type array
            options[0]); //default button title
        
            if(n == 0)
            {
                sn.Delete(nutritionniste);
                refreshTableView();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Sélectionner un patient à supprimer SVP !!!");
        }
    }
    
    @FXML
    private void btn_search_doctor(ActionEvent event)
    {
        
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
        table_secretaire.setCellValueFactory(new PropertyValueFactory("secretaire"));
        table_roles.setCellValueFactory(new PropertyValueFactory("stringRoles"));
        tableview_doctors.setItems(sn.Display());
    }

    
}
