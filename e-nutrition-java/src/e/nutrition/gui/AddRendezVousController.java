/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.RendezVous;
import e.nutrition.Services.RendezVousService;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Abdelhamid
 */
public class AddRendezVousController implements Initializable {

    @FXML
    private DatePicker dateRendezVous;
    @FXML
    private TextArea descriptionRendezVous;
    @FXML
    private Button AjouterRendezVous;
    @FXML
    private Button AnnulerAjoutRendezVous;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterRDV(ActionEvent event) {
        RendezVousService rendezVousService = new RendezVousService();
        rendezVousService.add(new RendezVous(Date.from(dateRendezVous.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), descriptionRendezVous.getText()));
        JOptionPane.showMessageDialog(null, "Rendez-vous ajouté !");
    }

    @FXML
    private void annulerAjoutRDV(ActionEvent event) {
    }
    
}
