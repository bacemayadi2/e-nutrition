package e.nutrition.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import e.nutrition.Models.Nutritionniste;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;

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
    private JFXComboBox<String> doctor_sexe;
    @FXML
    private DatePicker doctor_dateNaiss;
    @FXML
    private TextField doctor_tel;
    @FXML
    private TextField doctor_ville;
    @FXML
    private TextField doctor_adresse;
    @FXML
    private ComboBox<String> liste_secretaires;
    @FXML
    private JFXPasswordField doctor_pass;
    @FXML
    private AnchorPane popup_registerDoctor;
    @FXML
    private Label btn_close_registerDoctor;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<String> sexe = FXCollections.observableArrayList();
        sexe.add("Homme");
        sexe.add("Femme");
        doctor_sexe.getItems().addAll(sexe);
        
        ObservableList<String> list = FXCollections.observableArrayList();
        
        ServiceSecretaire ss = new ServiceSecretaire();
        
        int i =0;
        while ( i < ss.Display().size() ) 
        {
            list.add(ss.Display().get(i).getEmail());
            i++;
        }
        liste_secretaires.getItems().addAll(list);
        
        ServiceNutritionniste sn = new ServiceNutritionniste();
        System.out.println("nutri: " + sn.Display());
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
    private void disable_styles(MouseEvent event) 
    {
        doctor_tel.getScene().setCursor(Cursor.DEFAULT);
        btn_close_registerDoctor.setStyle("-fx-background-color: none;");
    }
    
    @FXML
    private void btn_RegisterDoctor(ActionEvent event) throws SQLException 
    {
        Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement ps = cnx.prepareStatement("SELECT id FROM utilisateur WHERE email=?");
        ps.setString(1, liste_secretaires.getValue());
        int id=0;
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            id = rs.getInt(1);
        }
        
        ServiceNutritionniste sn = new ServiceNutritionniste();
        
        JSONArray array = new JSONArray();
        array.put("ROLE_DOCTOR");
        
        sn.Add(new Nutritionniste(doctor_email.getText(), doctor_nom.getText(), doctor_prenom.getText(),
                doctor_sexe.getValue(), Date.valueOf(doctor_dateNaiss.getValue()), Integer.parseInt(doctor_tel.getText()),
                doctor_ville.getText(), doctor_adresse.getText(), false, array, doctor_pass.getText(), id));
        
        //last parameter of constructor:  liste_secretaires.getCheckModel().getCheckedItems()
        
        List list = liste_secretaires.getItems();
    }
}
