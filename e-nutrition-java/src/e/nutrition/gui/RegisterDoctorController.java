package e.nutrition.gui;

import com.jfoenix.controls.JFXPasswordField;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Secretaire;
import e.nutrition.Services.ServiceNutritionniste;
import e.nutrition.Services.ServicePatient;
import e.nutrition.Services.ServiceSecretaire;
import java.awt.Frame;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import org.controlsfx.control.CheckComboBox;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class RegisterDoctorController implements Initializable {

    @FXML
    private TextField doctor_email;
    @FXML
    private TextField doctor_nom;
    @FXML
    private TextField doctor_prenom;
    @FXML
    private TextField doctor_sexe;
    @FXML
    private DatePicker doctor_dateNaiss;
    @FXML
    private TextField doctor_tel;
    @FXML
    private TextField doctor_ville;
    @FXML
    private TextField doctor_adresse;
    @FXML
    private CheckComboBox<Secretaire> liste_secretaires;
    @FXML
    private JFXPasswordField doctor_pass;
    @FXML
    private AnchorPane popup_registerDoctor;
    @FXML
    private Label btn_close_registerDoctor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Secretaire> list = FXCollections.observableArrayList();
        ServiceSecretaire ss = new ServiceSecretaire();
        
        int i =0;
        while ( i < ss.Display().size() ) 
        {
            list.add(ss.Display().get(i));
            System.out.println("nom: " + ss.Display().get(i));
            i++;
        }
        liste_secretaires.getItems().addAll(list);
        
        ServicePatient sp = new ServicePatient();
        System.out.println("patients:  " + sp.Display());
    }    

    @FXML
    private void close_register_doctor(MouseEvent event) 
    {
        Stage stage = (Stage) doctor_tel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void minimise_doctor_register(MouseEvent event) 
    {
        //this.setState(Frame.ICONIFIED);
    }

    @FXML
    private void change_cursor(MouseEvent event) 
    {
        doctor_adresse.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void btn_RegisterDoctor(ActionEvent event) 
    {
        ServiceNutritionniste sn = new ServiceNutritionniste();
        sn.Add(new Nutritionniste(doctor_nom.getText(), doctor_prenom.getText(), doctor_sexe.getText(),
                Date.valueOf(doctor_dateNaiss.getValue()), doctor_email.getText(), Integer.parseInt(doctor_tel.getText()),
                doctor_ville.getText(), doctor_adresse.getText()));
        //last parameter of constructor:  liste_secretaires.getCheckModel().getCheckedItems()
        
        List list = liste_secretaires.getItems();
        System.out.println("listaa: " + list);
    }

    @FXML
    private void disable_styles(MouseEvent event) 
    {
        doctor_tel.getScene().setCursor(Cursor.DEFAULT);
        btn_close_registerDoctor.setStyle("-fx-background-color: none;");
    }
    
}
