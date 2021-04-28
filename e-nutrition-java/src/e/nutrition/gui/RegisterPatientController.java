package e.nutrition.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import e.nutrition.Models.Patient;
import e.nutrition.Services.ServicePatient;
import java.net.URL;
import java.sql.Date;
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
import org.json.JSONArray;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class RegisterPatientController implements Initializable {

    private Label btn_close_registerDoctor;
    @FXML
    private TextField patient_email;
    @FXML
    private TextField patient_nom;
    @FXML
    private TextField patient_prenom;
    @FXML
    private JFXComboBox<String> patient_sexe;
    @FXML
    private DatePicker patient_dateNaiss;
    @FXML
    private TextField patient_tel;
    @FXML
    private TextField patient_ville;
    @FXML
    private TextField patient_adresse;
    @FXML
    private JFXTextArea patient_style;
    @FXML
    private JFXPasswordField patient_pass;
    @FXML
    private Label btn_close_registerPatient;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> sexe = FXCollections.observableArrayList();
        sexe.add("Homme");
        sexe.add("Femme");
        patient_sexe.getItems().addAll(sexe);
    }    

    @FXML
    private void disable_styles(MouseEvent event) 
    {
        btn_close_registerPatient.getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void change_cursor(MouseEvent event) 
    {
        patient_tel.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void close_register_patient(MouseEvent event)
    {
        Stage stage = (Stage) patient_tel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimise_register_patient(MouseEvent event) 
    {
        
    }
    
    @FXML
    private void btn_RegisterPatient(ActionEvent event)
    {
        ServicePatient sp = new ServicePatient();
                
        JSONArray array = new JSONArray();
        array.put("ROLE_USER");
        array.put("ROLE_PATIENT");
        
        sp.Add(new Patient( patient_email.getText(), patient_nom.getText(), patient_prenom.getText(), patient_sexe.getValue(),
                Date.valueOf(patient_dateNaiss.getValue()), Integer.parseInt(patient_tel.getText()),
                patient_ville.getText(), patient_adresse.getText(), false, array, patient_style.getText()));
                
    }
}
