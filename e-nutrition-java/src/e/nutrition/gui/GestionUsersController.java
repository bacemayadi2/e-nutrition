package e.nutrition.gui;

import e.nutrition.Models.User;
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
public class GestionUsersController implements Initializable
{
    @FXML
    private TableView<User> tableview_users;
    @FXML
    private TableColumn<User, String> table_email;
    @FXML
    private TableColumn<User, String> table_nom;
    @FXML
    private TableColumn<User, String> table_prenom;
    @FXML
    private TableColumn<User, String> table_sexe;
    @FXML
    private TableColumn<User, String> table_dateNaiss;
    @FXML
    private TableColumn<User, String> table_tel;
    @FXML
    private TableColumn<User, String> table_ville;
    @FXML
    private TableColumn<User, String> table_adresse;
    @FXML
    private TableColumn<User, String> table_isVerified;
    @FXML
    private TableColumn<User, String> table_roles;
    @FXML
    private TextField search_field;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    private void btn_delete_user(ActionEvent event) 
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
        table_roles.setCellValueFactory(new PropertyValueFactory("stringRoles"));
        tableview_users.setItems(User.Display());
    }
}
