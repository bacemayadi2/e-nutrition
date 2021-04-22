/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.RendezVous;
import e.nutrition.Services.RendezVousService;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdelhamid
 */
public class DeleteRDVController implements Initializable {

    @FXML
    private ListView<String> rdvlist;
    @FXML
    private Button supprimerRDV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RendezVousService rendezVousService = new RendezVousService();
        List<RendezVous> listRendezVous = rendezVousService.display();
        for(RendezVous rdv : listRendezVous){
            rdvlist.getItems().add(rdv.toString());
        }
        
    }    

    @FXML
    private void supprimerRDV(ActionEvent event) {
        int selected = rdvlist.getSelectionModel().getSelectedIndex();
        rdvlist.getItems().remove(selected);
        String selectedItem = rdvlist.getSelectionModel().getSelectedItem();
        Integer indexToDelete = Integer.valueOf(selectedItem.substring(5, 7));
        RendezVousService rendezVousService = new RendezVousService();
        rendezVousService.deleteById(selected);
    }
    
}
