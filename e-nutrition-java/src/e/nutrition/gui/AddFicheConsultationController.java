/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;
import com.itextpdf.text.DocumentException;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Medicament;
import e.nutrition.Models.Patient;
import e.nutrition.Models.Pdf;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceMedicament;
import e.nutrition.Services.ServicePatient;
import e.nutrition.Utils.DataSource;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javax.swing.JOptionPane;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

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
    @FXML
    private TableColumn<Medicament, String> table_patientname;
    @FXML
    private TableColumn<Medicament, String> table_nutritionnistename;
    @FXML
    private Button pdf;
    @FXML
    private Label id_fiche_label;
    @FXML
    private TextField recherche;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AddFicheConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
          refreshTableView();
          RechercheAV();
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
        RechercheAV();
    }

    @FXML
    private void btn_ajouter_fiche(ActionEvent event) {
        fichePatientAffectation.getValue();
        
     sf.Add(new FicheConsultation(Date.valueOf(ficheDateCreation.getValue()),(Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText())),ficheSymptome.getText(),ficheApetit.getText(),ficheDescription.getText(),64));
        System.out.println(Date.valueOf(ficheDateCreation.getValue()));
        System.out.println(fichePoids.getText());
    
        System.out.println(ficheTaille.getText());
        System.out.println(ficheSymptome.getText());
           System.out.println(ficheApetit.getText());

        System.out.println(ficheDescription.getText());
  
    
        JOptionPane.showMessageDialog(null, "confirmation d'ajout");
        
        refreshTableView();    
        RechercheAV();  
    }
    
    
    
        private Statement ste;
    private Connection con;
    ObservableList<FicheConsultation> cls = FXCollections.observableArrayList();


    private void refreshTableView() {
               
               cls.clear();
        table_idd.setCellValueFactory(new PropertyValueFactory("id"));
        table_date_creation.setCellValueFactory(new PropertyValueFactory("creation_date"));
        
        table_poids.setCellValueFactory(new PropertyValueFactory("poids"));
       
        table_taille.setCellValueFactory(new PropertyValueFactory("taille"));
        table_symptome.setCellValueFactory(new PropertyValueFactory("symptome"));
            table_symptome.setCellFactory( TextFieldTableCell.forTableColumn() );
        table_symptome.setOnEditCommit((TableColumn.CellEditEvent<FicheConsultation, String> t) -> {
            FicheConsultation f;
            
            
            (f =(FicheConsultation) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setSymptome(t.getNewValue());
            sf.Update(f);
        });
        table_apetit.setCellValueFactory(new PropertyValueFactory("apetit"));
        table_description.setCellValueFactory(new PropertyValueFactory("description"));
        table_patientname.setCellValueFactory(new PropertyValueFactory("nompatient"));
        table_nutritionnistename.setCellValueFactory(new PropertyValueFactory("nomnutritionniste"));
         
        ObservableList<FicheConsultation> list =sf.DisplayAll2(64);
        for (FicheConsultation aux : list)
        {
            cls.add(aux);
        }
        tableview_fiche.setItems(cls);  
  System.out.println(sf.Display()); 
                
        
        
}
    
    

    private void btn_modifier_fiche(ActionEvent event) {
         sf.Update(new FicheConsultation(fiche_Id, Date.valueOf(ficheDateCreation.getValue()),
                 (Float.valueOf(fichePoids.getText())),(Float.valueOf(ficheTaille.getText()))
                 ,ficheSymptome.getText(),ficheApetit.getText(),ficheDescription.getText()));
     
            refreshTableView(); 
            RechercheAV();
        
        
    }

    @FXML
    private void AfficherMed(ActionEvent event) {
       FicheConsultation selected = tableview_fiche.getSelectionModel().getSelectedItem();
       id_fiche_label.setText(String.valueOf(selected.getId()));
        ServiceMedicament sm = new ServiceMedicament();
         
        table_id2.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom2.setCellValueFactory(new PropertyValueFactory("nom"));
        table_quantite2.setCellValueFactory(new PropertyValueFactory("quantite"));
        table_duree2.setCellValueFactory(new PropertyValueFactory("duree"));
        
        tableview_medicaments.setItems(sm.DisplayByFiche(selected.getId()));    
    }
   
        public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<FicheConsultation> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(fichecons -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (fichecons.getNompatient().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (fichecons.getSymptome().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(fichecons.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<FicheConsultation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableview_fiche.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableview_fiche.setItems(sortedData);
    }

    


    @FXML
    private void btn_supprimer_medicaments(ActionEvent event) {
             Medicament medicament = tableview_medicaments.getSelectionModel().getSelectedItem();
         ServiceMedicament sm = new ServiceMedicament();
        sm.Delete(medicament);
      
         AfficherMed(event); 
    }

    @FXML
    private void makepdf(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException {
             Medicament medicament = tableview_medicaments.getSelectionModel().getSelectedItem();
             
                     Pdf p = new Pdf();
                     
                  p.add("Medicament",id_fiche_label.getText());
                  
            String title = "succes ";
        String message = "PDF crée avec succes";
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
                  
    }
    
    
    
    
}