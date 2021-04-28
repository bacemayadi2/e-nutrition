/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.CategorieAliment;
import e.nutrition.Models.SuccessStory;
import e.nutrition.Services.RendezVousService;
import e.nutrition.Services.SuccessStoryService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class DeleteSuccessController implements Initializable {

    @FXML
    private Text txtx;
    private ListView<String> listSuccess;
    @FXML
    private Button successbtn;
    @FXML
    private TableColumn<SuccessStory , String> table_id;
    @FXML
    private TableColumn<SuccessStory , String> table_titre;
    @FXML
    private TableView<SuccessStory> table_success;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTableView();
    }    

    @FXML
    private void deleteSuccess(ActionEvent event) {
      SuccessStoryService s = new SuccessStoryService();
       SuccessStory story = table_success.getSelectionModel().getSelectedItem();
         
        
        s.deleteById(story.getId());
        refreshTableView();
        
    }
    private void refreshTableView()
    {        SuccessStoryService s = new SuccessStoryService();
        table_id.setCellValueFactory(new PropertyValueFactory("id"));
        table_titre.setCellValueFactory(new PropertyValueFactory("titre"));
        table_titre.setCellFactory( TextFieldTableCell.forTableColumn() );
        table_titre.setOnEditCommit((TableColumn.CellEditEvent<SuccessStory, String> t) -> {
            SuccessStory c;
            
            
            (c =(SuccessStory) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())
                    ).setTitre(t.getNewValue());
            s.Update(c);
        });
        table_titre.setCellValueFactory(new PropertyValueFactory("titre"));
            
        table_success.setItems(s.Display());
    }
    
}
