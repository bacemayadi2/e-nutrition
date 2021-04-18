/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;
import e.nutrition.Models.FicheConsultation;
import e.nutrition.Services.ServiceFicheConsultation;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
    private ComboBox<?> fichePatientAffectation;
  
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

    private int fiche_Id;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          refreshTableView();
        // TODO
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
        
     sf.Add(new FicheConsultation(Date.valueOf(ficheDateCreation.getValue()),(Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText())),ficheSymptome.getText(),ficheDescription.getText(),ficheApetit.getText()));
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

    @FXML
    private void btn_modifier_fiche(ActionEvent event) {
         sf.Update(new FicheConsultation(fiche_Id, Date.valueOf(ficheDateCreation.getValue()),
                 (Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText()))
                 ,ficheSymptome.getText(),ficheDescription.getText(),ficheApetit.getText()));
     
            refreshTableView(); 
        
        
    }
}