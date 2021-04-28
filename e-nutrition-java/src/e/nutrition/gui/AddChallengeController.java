package e.nutrition.gui;

import e.nutrition.Models.Challenge;
import e.nutrition.Services.ServiceChallenge;
import java.awt.Button;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ALADIN
 */
public class AddChallengeController implements Initializable
{
    private javafx.scene.control.TextField challengeTitre;
    private javafx.scene.control.TextField challengeCategorie;
    private DatePicker challengeDateDebut;
    private DatePicker challengeDateFin;
    private javafx.scene.control.TextArea challengeDescription;
    
    private Button btn_add_challenge;

//_______________________________________________________________________________________________________________________
    private TableColumn<Challenge, String> table_id;
    private TableColumn<Challenge, String> table_titre;
    private TableColumn<Challenge, String> table_desc;
    private TableColumn<Challenge, String> table_cat;
    private TableColumn<Challenge, String> table_datedeb;
    private TableColumn<Challenge, String> table_datefin;
    private TableView<Challenge> tableview_challenge;
    @FXML
    private javafx.scene.control.TextField search_field;
    
    private ServiceChallenge sc = new ServiceChallenge();
   
    private int challenge_Id;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {        
        refreshTableView();
    }

    private void btn_add_challenge(ActionEvent event) 
    {
        if( checkFields() )
        {
            sc.Add(new Challenge(challengeTitre.getText(), challengeDescription.getText(), challengeCategorie.getText(), 
                Date.valueOf(challengeDateDebut.getValue()), Date.valueOf(challengeDateFin.getValue())));
        
            JOptionPane.showMessageDialog(null, "confirmation d'ajout");
            refreshTableView();
        }
    }

    private void btn_update_challenge(ActionEvent event) 
    {
        if( checkFields() )
        {
            sc.Update(new Challenge(challenge_Id, challengeTitre.getText(), challengeDescription.getText(),
                    challengeCategorie.getText(), Date.valueOf(challengeDateDebut.getValue()),
                    Date.valueOf(challengeDateFin.getValue())));
            refreshTableView(); 
        }
    }
    
    @FXML
    private void btn_get_challenge(ActionEvent event) 
    {
        Challenge challenge = tableview_challenge.getSelectionModel().getSelectedItem();
        challengeTitre.setText(challenge.getTitre());
        challengeDescription.setText(challenge.getDescription());
        challengeCategorie.setText(challenge.getCategorie());
        challengeDateDebut.setValue(challenge.getDateDebut().toLocalDate());
        challengeDateFin.setValue(challenge.getDateFin().toLocalDate());
    }
    
    @FXML
    private void btn_delete_challenge(ActionEvent event) 
    {
        Challenge challenge = tableview_challenge.getSelectionModel().getSelectedItem();
        sc.Delete(challenge);
        refreshTableView();
    }
    
     @FXML
    private void btn_search(ActionEvent event) 
    {
        if(search_field.getText().isEmpty())
        {
            refreshTableView();
        }
        else
        {
            table_id.setCellValueFactory(new PropertyValueFactory("id"));
            table_titre.setCellValueFactory(new PropertyValueFactory("titre"));
            table_desc.setCellValueFactory(new PropertyValueFactory("description"));
            table_cat.setCellValueFactory(new PropertyValueFactory("categorie"));
            table_datedeb.setCellValueFactory(new PropertyValueFactory("dateDebut"));
            table_datefin.setCellValueFactory(new PropertyValueFactory("dateFin"));
            tableview_challenge.setItems(sc.Search(search_field.getText()));
        }
        
    }
    
    private void refreshTableView()
    {
        table_titre.setCellValueFactory(new PropertyValueFactory("titre"));
        table_desc.setCellValueFactory(new PropertyValueFactory("description"));
        table_cat.setCellValueFactory(new PropertyValueFactory("categorie"));
        table_datedeb.setCellValueFactory(new PropertyValueFactory("dateDebut"));
        table_datefin.setCellValueFactory(new PropertyValueFactory("dateFin"));
        
        tableview_challenge.setItems(sc.Display());
    }
    
    private boolean checkFields()
    {
        if(challengeTitre.getText().isEmpty() || challengeCategorie.getText().isEmpty() || challengeDescription.getText().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "All fields are required !!!");
            return false;
        }
        else
        {
            if(challengeDateDebut.getValue() == null || challengeDateFin.getValue() == null || 
                    challengeDateDebut.getValue().isBefore(LocalDate.now()) ||
                    challengeDateDebut.getValue().isAfter(challengeDateFin.getValue()))
            {
//                System.out.println("date fin class:  " + challengeDateDebut.getValue().getClass());
//                System.out.println("sys date: " + LocalDate.now());
//                System.out.println("sys date class: " + LocalDate.now().getClass());
                JOptionPane.showMessageDialog(null, "You must select a valid date !!!");
                return false;
            }
        }
        return true;
    }

   
}
