/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Medicament;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceMedicament;
import java.awt.Button;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddMedicamentController implements Initializable {

    @FXML
    private TextField medicamentNom;
    @FXML
    private TextField medicamentQuantite;
    @FXML
    private TextField medicamentDuree;
    @FXML
    private ComboBox<String> medicamentfiche;

    /**
     * Initializes the controller class.
     */
    private ServiceMedicament sm = new ServiceMedicament();
    @FXML
    private TableView<Medicament> tableview_medicament;
    @FXML
    private TableColumn<Medicament, String> table_quantite;
    @FXML
    private TableColumn<Medicament,String > table_duree;
    @FXML
    private TableColumn<Medicament, String> table_id;
    @FXML
    private TableColumn<Medicament,String> table_nom1;
    
     private int medicament_Id;
       ObservableList<String> listdesc = FXCollections.observableArrayList();
            ServiceFicheConsultation ser = new ServiceFicheConsultation();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AddMedicamentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         refreshTableVieww();
    }    
    
        public void fillcombo() throws SQLException{
                
        List<FicheConsultation> list = ser.Display();
            System.out.println(list);
        for (FicheConsultation aux : list)
        {
          listdesc.add(aux.getDescription());
        }
        medicamentfiche.setItems(listdesc);
    }

    @FXML
    private void fichePatientAffectation(ActionEvent event) {
    }

    
    @FXML
    private void btn_ajouter_medicament(ActionEvent event) {
        
        FicheConsultation list = ser.getByDesc(medicamentfiche.getValue());
        
        sm.Add(new Medicament(medicamentNom.getText(),medicamentQuantite.getText(),medicamentDuree.getText(),list.getId()));
        System.out.println(medicamentNom.getText());
        System.out.println(medicamentQuantite.getText());
        System.out.println(medicamentDuree.getText());
        JOptionPane.showMessageDialog(null, "confirmation d'ajout"); 
        refreshTableVieww(); 
             String title = "succes ";
        String message = "medicament ajouté avec succes";
         TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        
    }
    
  
     private void refreshTableVieww() {
        table_id.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom1.setCellValueFactory(new PropertyValueFactory("nom"));
        table_quantite.setCellValueFactory(new PropertyValueFactory("quantite"));
        table_duree.setCellValueFactory(new PropertyValueFactory("duree"));
       
         
        tableview_medicament.setItems(sm.Display());  
    
     }

    @FXML
    private void btn_supprimer_medicament(ActionEvent event) {
         Medicament medicament = tableview_medicament.getSelectionModel().getSelectedItem();
        sm.Delete(medicament);
        refreshTableVieww(); 
        String title = "succes ";
        String message = "medicament supprimé avec succes";
         TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        
        
        
    }
 

    @FXML
    private void btn_get_medicamentt(ActionEvent event) {
        
         Medicament medicament = tableview_medicament.getSelectionModel().getSelectedItem();
        medicament_Id = medicament.getId();
        medicamentNom.setText(medicament.getNom());
        medicamentQuantite.setText(medicament.getQuantite());
         medicamentDuree.setText(medicament.getDuree());
       
        
        System.out.println("medicament id: " + medicament_Id);
      
    }

    @FXML
    private void btn_modifier_medicamentt(ActionEvent event) {
        
       sm.Update(new Medicament(medicamentNom.getText(),medicamentQuantite.getText(),medicamentDuree.getText()));
            refreshTableVieww();   
        
        
    }
    
    
    }
    
    
     
     
  


