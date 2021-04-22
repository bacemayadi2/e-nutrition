/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.Aliment;
import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.Composition;
import e.nutrition.Services.ServiceAliment;
import e.nutrition.Services.ServiceCategorieAliment;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import javax.swing.JOptionPane;

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
    private TextField codeABarre;
    @FXML
    private TextField poid;
    @FXML
    private TextField lipides;
    @FXML
    private TextField proteines;
    @FXML
    private TextField glucides;
    @FXML
    private Button btn_add_Aliment;
    @FXML
    private Button btn_delete_Aliment;
    @FXML
    private Button btn_modifier_Aliment;
    
      //---------table
    @FXML
    private TableColumn<Aliment,String> table_id;
    @FXML
    private TableColumn<Aliment,String> table_nom;
    @FXML
    private TableColumn<Aliment,String> table_categories_Aliment;
    @FXML
    private TableColumn<Aliment,Float> table_poid;
    @FXML
    private TableColumn<Aliment,Float> table_proteines;
    @FXML
    private TableColumn<Aliment,Float> table_glucides;
    @FXML
    private TableColumn<Aliment,Float> table_lipides;
    @FXML
    private TableColumn<Aliment,Float> table_calories;
    @FXML
    private TableView<Aliment> table_aliment;
    @FXML 
    private AutocompleteMultiSelectionBox categorie_selector;
    

    private ServiceAliment sA= new ServiceAliment();
    private ServiceCategorieAliment sCA = new ServiceCategorieAliment();
    private List <Aliment> alimentsToUpdate = new ArrayList();

    private int IdAliment;
    
    private void alimentsToUpdatef(Aliment a) //add or modifer aliment To  update
    {
         if (alimentsToUpdate.contains(a))
            {
                int i = alimentsToUpdate.indexOf(a);
                alimentsToUpdate.set(i,a);
            }
            else 
            alimentsToUpdate.add(a);  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         refreshTableView();
         ObservableSet<String> categories = FXCollections.observableSet();
         sCA.Display().forEach((c)-> {
             categories.add(c.getNomCategorie());
         });
          categorie_selector.setSuggestions(categories);


    }    
     private boolean checkFields()
    {
        if(nomAliment.getText().isEmpty() || poid.getText().isEmpty() || lipides.getText().isEmpty() || glucides.getText().isEmpty() || proteines.getText().isEmpty() ) 
        {
            JOptionPane.showMessageDialog(null, "tout les champ sauf code a barre sont obligatoir");
            return false;
        }
        else
        {
            //toDo check  uncity du nom  
        }
        return true;
    }
     
           private void refreshTableView()
    {        
        table_id.setCellValueFactory(new PropertyValueFactory("id"));
        table_nom.setCellValueFactory(new PropertyValueFactory("nom"));
        table_nom.setCellFactory( TextFieldTableCell.forTableColumn() );
        table_nom.setOnEditCommit((TableColumn.CellEditEvent<Aliment, String> t) -> {
            Aliment a;
            (a =(Aliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setNom(t.getNewValue());
           alimentsToUpdatef(a);
        });
        table_categories_Aliment.setCellValueFactory(new PropertyValueFactory("categorieString"));
        
        table_poid.setCellValueFactory(new PropertyValueFactory("poid"));
        table_poid.setCellFactory( TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        table_poid.setOnEditCommit((TableColumn.CellEditEvent<Aliment, Float> t) -> {
            Aliment a;
    
            (a =(Aliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setPoid(t.getNewValue());
           alimentsToUpdatef(a);
        });
        
        table_proteines.setCellValueFactory(new PropertyValueFactory("proteines"));
        table_proteines.setCellFactory( TextFieldTableCell.forTableColumn(new FloatStringConverter()) );
        table_proteines.setOnEditCommit((TableColumn.CellEditEvent<Aliment, Float> t) -> {
            Aliment a;
    
            (a =(Aliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setProteines(t.getNewValue());
           alimentsToUpdatef(a);
        });
        table_glucides.setCellValueFactory(new PropertyValueFactory("glucides"));
        table_glucides.setCellFactory( TextFieldTableCell.forTableColumn(new FloatStringConverter()) );
        table_glucides.setOnEditCommit((TableColumn.CellEditEvent<Aliment, Float> t) -> {
            Aliment a;
    
            (a =(Aliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setGlucides(t.getNewValue());
           alimentsToUpdatef(a);
        });
        table_lipides.setCellValueFactory(new PropertyValueFactory("lipidies"));
        table_lipides.setCellFactory( TextFieldTableCell.forTableColumn(new FloatStringConverter()) );
        table_lipides.setOnEditCommit((TableColumn.CellEditEvent<Aliment, Float> t) -> {
            Aliment a;
    
            (a =(Aliment) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setLipidies(t.getNewValue());
           alimentsToUpdatef(a);
        });
        
        table_calories.setCellValueFactory(new PropertyValueFactory("calculerCalorie"));
        table_aliment.setItems(sA.Display());
        //System.out.println(sA.Display());
    }
           
    @FXML
    private void btn_add_Aliment(ActionEvent event) 
    {
        if( checkFields() )
        {
            Aliment a = new Aliment(nomAliment.getText(),Float.parseFloat( lipides.getText()),Float.parseFloat(glucides.getText()) , Float.parseFloat(proteines.getText()), Float.parseFloat(poid.getText()), codeABarre.getText(), 74 );
            categorie_selector.getTags().forEach((c)->{

            a.ajouterCategorie(sCA.rechercherExactCategorie(c));
           });
            sA.Add(a);// toDo change 74 to id user from sesion
            JOptionPane.showMessageDialog(null, "confirmation d'ajout");
            refreshTableView();
        }
    }

  
        @FXML
    private void btn_delete_Aliment(ActionEvent event) 
    {
        
        Aliment aliment = table_aliment.getSelectionModel().getSelectedItem();
        if (aliment.getNumberOfTimeUsed()== 0)
        {
        sA.Delete(aliment);
        refreshTableView();
        }
        else
        {
        JOptionPane.showMessageDialog(null, "impossible de supprimer un aliment utiliser par des plat");
        }
    }
    @FXML
        private void btn_modifier_Aliment(ActionEvent event) 
    {
        
        if (alimentsToUpdate.size()!= 0)
        {
              Iterator<Aliment>  i= alimentsToUpdate.iterator();
        while(i.hasNext())

        {     
            sA.Update(i.next());
        }
                refreshTableView();

        }
        else
        {
        JOptionPane.showMessageDialog(null, "il faut dabord modifer un element dans le tableaux ");
        }
    }
    
 
     
    
}
