package e.nutrition.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Secretaire;
import e.nutrition.Services.ServiceNutritionniste;
import e.nutrition.Services.ServiceSecretaire;
import e.nutrition.Utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class RegisterSecretaireController implements Initializable 
{
    @FXML
    private Label btn_close_registerSecretaire;
    @FXML
    private Label btn_minimise_Secretaire;
    @FXML
    private TextField Secretaire_email;
    @FXML
    private TextField Secretaire_nom;
    @FXML
    private TextField Secretaire_prenom;
    @FXML
    private JFXComboBox<String> Secretaire_sexe;
    @FXML
    private DatePicker Secretaire_dateNaiss;
    @FXML
    private TextField Secretaire_tel;
    @FXML
    private TextField Secretaire_ville;
    @FXML
    private TextField Secretaire_adresse;
    @FXML
    private JFXPasswordField Secretaire_pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> sexe = FXCollections.observableArrayList();
        sexe.add("Homme");
        sexe.add("Femme");
        
        Secretaire_sexe.getItems().addAll(sexe);
    }

    @FXML
    private void disable_styles(MouseEvent event) 
    {
        btn_close_registerSecretaire.getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void change_cursor(MouseEvent event) 
    {
        Secretaire_tel.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void close_register_Secretaire(MouseEvent event) 
    {
        Stage stage = (Stage) Secretaire_tel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimise_register_Secretaire(MouseEvent event) 
    {
        
    }

    @FXML
    private void btn_RegisterSecretaire(ActionEvent event) throws SQLException 
    {
        ServiceSecretaire ss = new ServiceSecretaire();
        ss.Add(new Secretaire(Secretaire_nom.getText(), Secretaire_prenom.getText(), Secretaire_sexe.getValue(),
                Date.valueOf(Secretaire_dateNaiss.getValue()), Secretaire_email.getText(), Integer.parseInt(Secretaire_tel.getText()),
                Secretaire_ville.getText(), Secretaire_adresse.getText()));
    }
}
