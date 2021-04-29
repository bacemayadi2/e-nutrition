package e.nutrition.gui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import e.nutrition.Services.UserSession;
import e.nutrition.Utils.Methods;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class LoginController implements Initializable {

    @FXML
    private Label btn_exit;
    @FXML
    private Label btn_minimise;
    @FXML
    private JFXTextField login_email;
    @FXML
    private JFXPasswordField login_password;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_exit(MouseEvent event) 
    {
        System.exit(0);
    }

    @FXML
    private void btn_login(ActionEvent event) throws SQLException, IOException 
    {
//        if(CheckFields())
//        {
            if(UserSession.login(login_email.getText(), login_password.getText()))
            {
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
//        }
    }

    @FXML
    private void btn_registerDoctor(MouseEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterDoctor.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void btn_registerPatient(MouseEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterPatient.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void btn_registerSecretaire(MouseEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("RegisterSecretaire.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }   

    @FXML
    private void btn_handMouse_Disable(MouseEvent event) 
    {
        btn_exit.getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void btn_handMouse_Enable(MouseEvent event) 
    {
        btn_exit.getScene().setCursor(Cursor.HAND);
    }

    @FXML
    private void btn_close_disableStyle(MouseEvent event) 
    {
        btn_exit.getScene().setCursor(Cursor.DEFAULT);
        btn_exit.setStyle("-fx-background-color: none;");
        btn_minimise.setStyle("-fx-background-color: none;");
    }

    @FXML
    private void btn_close_enableStyle(MouseEvent event) 
    {            
        btn_exit.getScene().setCursor(Cursor.HAND);
        btn_exit.setStyle("-fx-background-color: red;");
    }

    @FXML
    private void btn_minimise_style(MouseEvent event)
    {
        btn_minimise.getScene().setCursor(Cursor.HAND);
        btn_minimise.setStyle("-fx-background-color: blue;");
    }

    public boolean CheckFields()
    {
        if( !Methods.isValid(login_email.getText()) )
        {
            JOptionPane.showMessageDialog(null, "Vérifier E-Mail SVP !!!");
            return false;
        }
        
        if(login_password.getText().isEmpty() || login_password.getText().length() < 8)
        {
            JOptionPane.showMessageDialog(null, "Mot de passe invalide !!!");
            return false;
        }
       return true;
    }


    

}
