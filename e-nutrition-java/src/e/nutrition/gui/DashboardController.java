/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class DashboardController implements Initializable {

    
    @FXML
    private Button homebtn;
    @FXML
    private Pane home;
    @FXML
    private Button RDV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void callhome(ActionEvent event) throws IOException {
        

       Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("home.fxml"));
       home.getChildren().clear();
       home.getChildren().add(newLoadedPane);
        
    }

    @FXML
    private void addRendezVous(ActionEvent event)throws IOException  {
        
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("AddRendezVous.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    private void callSuccess(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("DisplaySuccess.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    private void blog(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("List_Blog_Posts_.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    private void mesure(ActionEvent event) throws IOException {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Mesure_List_.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    @FXML
    private void addficheConsultation(ActionEvent event) throws IOException {
        
      Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("AddFicheConsultation.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);   
    }

    @FXML
    private void addMedicaments(ActionEvent event) throws IOException {
        
          Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("AddMedicament.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);   
        
    }

    @FXML
    private void addStat(ActionEvent event) throws IOException {
        
       Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Statistique.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);      
        
        
    }

    @FXML
    private void FicheFront(ActionEvent event) throws IOException {
              Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("FicheFront.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);      
        
    }
     @FXML 
    private void callNutrition(ActionEvent event) throws IOException{
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("frontplats.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    @FXML
    private void callaliment(ActionEvent event) throws IOException {
              Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("Aliment.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    @FXML
    private void callcategorie(ActionEvent event) throws IOException {
              Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("AddCategorieAliment.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    @FXML
    private void callajoutplat(ActionEvent event)throws IOException {
              Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("ajouterplat.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    @FXML
    private void AddChallenge(ActionEvent event) throws IOException
    {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("AddChallenge.fxml"));
        home.getChildren().clear();
        home.getChildren().add(newLoadedPane);
    }

    
    
}
