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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class EditMesureController implements Initializable {

    private Mesure mesure;
   
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXButton btn_edit;
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
    public void InitSelectedMesure(Mesure c){
       mesure = c;
       
       taille.setText(String.valueOf(mesure.getTaille()));
       poids.setText(String.valueOf(mesure.getPoids()));
       
       
       
   }

    @FXML
    private void edit(ActionEvent event) throws SQLException {
         Mesure_Service service = new Mesure_Service();
         mesure.setTaille(Float.valueOf(taille.getText()));
         mesure.setPoids(Float.valueOf(poids.getText()));
         service.Update_Mesure(mesure.getId(), mesure);
    }
}
