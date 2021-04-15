/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Challenge;
import e.nutrition.Services.ServiceCategorieAliment;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bacem
 */
public class AddCategorieAlimentController implements Initializable {

    @FXML
    private TextField nomCategorie;
    @FXML 
    private Button btn_add_Categorie;
    @FXML
    private Button btn_delete_Categorie;
  

    
    //---------table
    @FXML
    private TableColumn<CategorieAliment,String> table_id;
    @FXML
    private TableColumn<CategorieAliment,String> table_nom;
    @FXML
    private TableColumn<CategorieAliment,String> table_utiliser;
    @FXML
    private TableView<CategorieAliment> table_categorie_aliment;
    @FXML 
    
    
    private ServiceCategorieAliment sC= new ServiceCategorieAliment();
    
    private int IdCategorie;
    
     
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTableView();
    }  

    
     private boolean checkFields()
    {
        if(nomCategorie.getText().isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "nom categorie requit");
            return false;
        }
        else
        {
            //toDo check  uncity du nom  
        }
        return true;
    }
     
        @FXML
    private void btn_add_Categorie(ActionEvent event) 
    {
        if( checkFields() )
        {
            sC.Add(new CategorieAliment(nomCategorie.getText()));
        
            JOptionPane.showMessageDialog(null, "confirmation d'ajout");
            refreshTableView();
        }
    }

  
        @FXML
    private void btn_delete_Categorie(ActionEvent event) 
    {
        
        CategorieAliment categorie = table_categorie_aliment.getSelectionModel().getSelectedItem();
        if (categorie.getNumberOfTimeUsed()== 0)
        {
        sC.Delete(categorie);
        refreshTableView();
        }
        else
        {
        JOptionPane.showMessageDialog(null, "impossible de supprimer une categorie utiliser par des aliments");

        }
    }
    
    
      private void refreshTableView()
    {        
        table_id.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom.setCellValueFactory(new PropertyValueFactory("nomCategorie"));
        table_nom.setCellFactory( TextFieldTableCell.forTableColumn() );
        table_nom.setOnEditCommit((CellEditEvent<CategorieAliment, String> t) -> {
            CategorieAliment c;
            
            
            (c =(CategorieAliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setNomCategorie(t.getNewValue());
            sC.Update(c);
        });
        table_utiliser.setCellValueFactory(new PropertyValueFactory("numberOfTimeUsed"));
            
        table_categorie_aliment.setItems(sC.Display());
    }
    
}
