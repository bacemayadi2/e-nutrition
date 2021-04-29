/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.Composition;
import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.Plat;
import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Models.tags.TagSuccessStory;
import e.nutrition.Services.ServiceAliment;
import e.nutrition.Services.ServicePlat;
import e.nutrition.Services.ServiceTag;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bacem
 */
public class ajouterplat implements Initializable {

    @FXML
    private TextField nomAliment;
    @FXML
    private TableView<Plat> table_aliment;
    @FXML
    private TableColumn<Plat, String> table_id;
    @FXML
    private TableColumn<Plat, String> table_nom;
    @FXML
    private TableColumn<Plat, Float> table_proteines;
    @FXML
    private TableColumn<Plat,Float> table_glucides;
    @FXML
    private TableColumn<Plat, Float> table_lipides;
    @FXML
    private TableColumn<Plat, Float> table_calorie;
    @FXML
    private Button ssavant;
    @FXML
    private AutocompleteMultiSelectionBox categorie_selector;

    /**
     * Initializes the controller class.
     */
    ServicePlat sP =new ServicePlat();
    ServiceAliment sa =new ServiceAliment();
        private List <File> selectedFiles = new ArrayList();
    ServiceTag sTag = new ServiceTag();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        refreshTableView();
         ObservableSet<String> categories = FXCollections.observableSet();
         sa.Display().forEach((c)-> {
             categories.add(c.getNom());
         });
          categorie_selector.setSuggestions(categories);
    }    

    @FXML
    private void btn_add_Aliment(ActionEvent event) {
               
        
            Plat p = new Plat("test",0,nomAliment.getText() , 0, 0, 0, 0, 0);
            categorie_selector.getTags().forEach((c)->{

            p.ajouterCompostions(new Composition(100, sa.rechercherExactAlimentparnom(c),p));
           });
                        JOptionPane.showMessageDialog(null, "confirmation d'ajout");
                    selectedFiles.forEach((file)-> {
        ContenuMultimedia contenuMultimedia=new ContenuMultimedia("",file);
              TagNourriture tagNourriture =new TagNourriture(contenuMultimedia);
              p.ajoutertag(tagNourriture);
        
               });
            sP.Add(p);// toDo change 74 to id user from sesion

                
            refreshTableView();
        
    }
     @FXML
    private void openFileChooser(ActionEvent event) {
        Scene scene = ssavant.getScene();
        Stage primaryStage = (Stage) scene.getWindow();
        FileChooser fileChooser = new FileChooser();
        ssavant.setOnAction(e -> {
            selectedFiles.add( fileChooser.showOpenDialog(primaryStage));
        });
        
    }

    @FXML
    private void btn_delete_Aliment(ActionEvent event) {
    }

  

    @FXML
    private void btn_modifier_Aliment(ActionEvent event) {
    }
    
            private void refreshTableView()
    {        
        table_id.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        
        table_proteines.setCellValueFactory(new PropertyValueFactory("proteines"));
        table_glucides.setCellValueFactory(new PropertyValueFactory("glucides"));
        table_lipides.setCellValueFactory(new PropertyValueFactory("lipidies"));
        table_calorie.setCellValueFactory(new PropertyValueFactory("calculerCalorie"));

  


        
        
      
        table_aliment.setItems(sP.Display());
        //System.out.println(sA.Display());
    }
    
}

