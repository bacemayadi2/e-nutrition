/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.CategorieAliment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author bacem
 */
public class AlimentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField nomAliment;
    @FXML
    private Button btn_add_Aliment;
    @FXML
    private Button btn_delete_Aliment;
      //---------table
    @FXML
    private TableColumn<CategorieAliment,String> table_id;
    @FXML
    private TableColumn<CategorieAliment,String> table_nom;
    @FXML
    private TableColumn<CategorieAliment,String> table_categories_aliment;
    @FXML
    private TableColumn<CategorieAliment,String> table_poid;
    @FXML
    private TableColumn<CategorieAliment,String> table_proteines;
    @FXML
    private TableColumn<CategorieAliment,String> table_glucides;
    @FXML
    private TableColumn<CategorieAliment,String> table_lipides;
    @FXML
    private TableColumn<CategorieAliment,String> table_calories;
    @FXML
    private TableView<CategorieAliment> table_categorie_aliment;
    @FXML 
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
