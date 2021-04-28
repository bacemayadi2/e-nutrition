package e.nutrition.gui;

import e.nutrition.Models.Secretaire;
import e.nutrition.Services.ServiceSecretaire;
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
public class GestionSecretairesController implements Initializable {

    @FXML
    private TextField search_field;
    @FXML
    private TableColumn<Secretaire, String> table_email;
    @FXML
    private TableColumn<Secretaire, String> table_nom;
    @FXML
    private TableColumn<Secretaire, String> table_prenom;
    @FXML
    private TableColumn<Secretaire, String> table_sexe;
    @FXML
    private TableColumn<Secretaire, String> table_dateNaiss;
    @FXML
    private TableColumn<Secretaire, String> table_tel;
    @FXML
    private TableColumn<Secretaire, String> table_ville;
    @FXML
    private TableColumn<Secretaire, String> table_adresse;
    @FXML
    private TableColumn<Secretaire, String> table_isVerified;
    @FXML
    private TableColumn<Secretaire, String> table_doctor;
    @FXML
    private TableColumn<Secretaire, String> table_roles;

    
    private final ServiceSecretaire ss = new ServiceSecretaire();
    @FXML
    private TableView<Secretaire> tableview_secretaire;
    
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
    private void btn_delete_secretaire(ActionEvent event) throws SQLException 
    {
        Secretaire secretaire = tableview_secretaire.getSelectionModel().getSelectedItem();
        
        if(secretaire != null )
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
                ss.Delete(secretaire);
                refreshTableView();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Sélectionner un patient à supprimer SVP !!!");
        }
    }
    
    @FXML
    private void btn_search_secretaire(ActionEvent event)
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
        table_doctor.setCellValueFactory(new PropertyValueFactory("nutritionniste"));
        table_roles.setCellValueFactory(new PropertyValueFactory("stringRoles"));
        tableview_secretaire.setItems(ss.Display());
    }

    
    
}
