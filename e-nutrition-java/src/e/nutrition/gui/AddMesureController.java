/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import e.nutrition.Models.Mesure;
import e.nutrition.Services.Mesure_Service;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * FXML Controller class
 *
 */
public class AddMesureController implements Initializable {

    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton btn_add;
    @FXML
    private JFXTextField taille;
    @FXML
    private JFXTextField poids;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addMesure(ActionEvent event) throws SQLException {
        Mesure_Service service = new Mesure_Service();
        Mesure mesure = new Mesure();
        mesure.setDate_mesure(Date.valueOf(date.getValue()));
        mesure.setPoids(Float.valueOf(poids.getText()));
        mesure.setTaille(Float.valueOf(taille.getText()));
        service.Add_Mesure(mesure);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION, service.Get_Imc_Details(mesure.getTaille(), mesure.getPoids()));
                alert.showAndWait();
          
    }
    
}
