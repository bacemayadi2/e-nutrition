/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.Nutritionniste;
import e.nutrition.Services.ServiceNutritionniste;
import java.awt.Button;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class AddNutritionnisteController implements Initializable 
{
    @FXML
    private TextField doctor_nom;
    @FXML
    private TextField doctor_prenom;
    @FXML
    private TextField doctor_sexe;
    @FXML
    private DatePicker doctor_dateNaiss;
    @FXML
    private TextField doctor_email;
    @FXML
    private TextField doctor_tel;
    @FXML
    private TextField doctor_ville;
    @FXML
    private TextField doctor_adresse;
    
    private Button btn_RegisterDoctor;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_RegisterDoctor(ActionEvent event)
    {
        ServiceNutritionniste sn = new ServiceNutritionniste();
        sn.Add(new Nutritionniste(doctor_nom.getText(), doctor_prenom.getText(), doctor_sexe.getText(), Date.valueOf(doctor_dateNaiss.getValue()), doctor_email.getText(), doctor_tel.getText(), doctor_ville.getText(), doctor_adresse.getText(), null));
    
        JOptionPane.showMessageDialog(null, "confirmation d'ajout");
    }
    
}
