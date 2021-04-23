/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;
import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Medicament;
import e.nutrition.Models.Patient;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceMedicament;
import e.nutrition.Services.ServicePatient;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AddFicheConsultationController implements Initializable {

    @FXML
    private DatePicker ficheDateCreation;
    @FXML
    private TextField fichePoids;
    @FXML
    private TextField ficheTaille;
    @FXML
    private TextField ficheSymptome;
    @FXML
    private TextArea ficheDescription;
    @FXML
    private TextField ficheApetit;
    @FXML
    private ComboBox<String> fichePatientAffectation;
      ObservableList<String> listnom = FXCollections.observableArrayList();

    @FXML
    private TableColumn<FicheConsultation, String> table_idd;
    @FXML
    private TableColumn<FicheConsultation, String> table_date_creation;
    @FXML
    private TableColumn<FicheConsultation, String> table_poids;
    @FXML
    private TableColumn<FicheConsultation, String> table_taille;
    @FXML
    private TableColumn<FicheConsultation, String> table_symptome;
    @FXML
    private TableColumn<FicheConsultation, String> table_apetit;
    @FXML
    private TableColumn<FicheConsultation, String> table_description;
     @FXML
    private TableView<FicheConsultation> tableview_fiche;
   private ServiceFicheConsultation sf = new ServiceFicheConsultation();
   ServicePatient sp = new ServicePatient();
   
    private int fiche_Id;
    @FXML
    private TableView<Medicament> tableview_medicaments;
    @FXML
    private TableColumn<Medicament, Integer> table_id2;
    @FXML
    private TableColumn<Medicament, String> table_nom2;
    @FXML
    private TableColumn<Medicament, String> table_quantite2;
    @FXML
    private TableColumn<Medicament, String> table_duree2;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AddFicheConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
          refreshTableView();
        // TODO
    }   
    
    public void fillcombo() throws SQLException{
                
        List<Patient> list = sp.Display();
                    System.out.println(list);

        for (Patient aux : list)
        {
          listnom.add(aux.getNom());
        }
        fichePatientAffectation.setItems(listnom);
    }
    
    @FXML
    private void fichePatientAffectation(ActionEvent event) {
    }

    @FXML
    private void btn_supprimer_fiche(ActionEvent event) {
        
           FicheConsultation FicheConsultation = tableview_fiche.getSelectionModel().getSelectedItem();
        sf.Delete(FicheConsultation);
        refreshTableView();
    }

    @FXML
    private void btn_ajouter_fiche(ActionEvent event) {
        
     sf.Add(new FicheConsultation(Date.valueOf(ficheDateCreation.getValue()),(Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText())),ficheSymptome.getText(),ficheApetit.getText(),ficheDescription.getText()));
        System.out.println(Date.valueOf(ficheDateCreation.getValue()));
        System.out.println(fichePoids.getText());
    
        System.out.println(ficheTaille.getText());
        System.out.println(ficheSymptome.getText());
           System.out.println(ficheApetit.getText());

        System.out.println(ficheDescription.getText());
  
    
        JOptionPane.showMessageDialog(null, "confirmation d'ajout");
        
        refreshTableView();             
        
        
    }

    private void refreshTableView() {
        table_idd.setCellValueFactory(new PropertyValueFactory("id"));
        table_date_creation.setCellValueFactory(new PropertyValueFactory("creation_date"));
        table_poids.setCellValueFactory(new PropertyValueFactory("poids"));
        table_taille.setCellValueFactory(new PropertyValueFactory("taille"));
        table_symptome.setCellValueFactory(new PropertyValueFactory("symptome"));
        table_apetit.setCellValueFactory(new PropertyValueFactory("apetit"));
        table_description.setCellValueFactory(new PropertyValueFactory("description"));
         
        tableview_fiche.setItems(sf.Display());  
  System.out.println(sf.Display()); 
                
        
        
}
    
    

    private void btn_modifier_fiche(ActionEvent event) {
         sf.Update(new FicheConsultation(fiche_Id, Date.valueOf(ficheDateCreation.getValue()),
                 (Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText()))
                 ,ficheSymptome.getText(),ficheApetit.getText(),ficheDescription.getText()));
     
            refreshTableView(); 
        
        
    }

    @FXML
    private void AfficherMed(ActionEvent event) {
       FicheConsultation selected = tableview_fiche.getSelectionModel().getSelectedItem();
        ServiceMedicament sm = new ServiceMedicament();
         
        table_id2.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom2.setCellValueFactory(new PropertyValueFactory("nom"));
        table_quantite2.setCellValueFactory(new PropertyValueFactory("quantite"));
        table_duree2.setCellValueFactory(new PropertyValueFactory("duree"));
        
        tableview_medicaments.setItems(sm.DisplayByFiche(selected.getId()));    
    }
   

    
  /*  @FXML
    private void btn_supprimer_medicaments(ActionEvent event) {
         Medicament medicament = tableview_medicaments.getSelectionModel().getSelectedItem();
         ServiceMedicament sm = new ServiceMedicament();
        sm.Delete(medicament);
       
        
        
    }
*/

    @FXML
    private void btn_supprimer_medicaments(ActionEvent event) {
             Medicament medicament = tableview_medicaments.getSelectionModel().getSelectedItem();
         ServiceMedicament sm = new ServiceMedicament();
        sm.Delete(medicament);
      
         AfficherMed(event); 
    }
    
}