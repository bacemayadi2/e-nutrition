/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package e.nutrition.gui;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.SuccessStory;
import e.nutrition.Models.tags.TagNourriture;
import e.nutrition.Models.tags.TagSuccessStory;
import e.nutrition.Services.ServiceTag;
import e.nutrition.Services.SuccessStoryService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdelhamid
 */

public class AddSuccessController implements Initializable {
    
    @FXML
    private Button ssavant;
    @FXML
    private Button ssapres;
    @FXML
    private Button ssvideo;
    @FXML
    private TextField sstitle;
    @FXML
    private TextField avantfile;
    @FXML
    private TextField apresfile;
    @FXML
    private TextField videofile;
    @FXML
    private TextArea ssdescription;
    
    /**
     * Initializes the controller class.
     */
    private List <File> selectedFiles = new ArrayList();
    ServiceTag sTag = new ServiceTag();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void openFileChooser(ActionEvent event) {
        Scene scene = ssavant.getScene();
        Stage primaryStage = (Stage) scene.getWindow();
        FileChooser fileChooser = new FileChooser();
        ssavant.setOnAction(e -> {
            selectedFiles.add( fileChooser.showOpenDialog(primaryStage));
            avantfile.setText(selectedFiles.get(0).getPath());
        });
        ssapres.setOnAction(e -> {
            selectedFiles.add(fileChooser.showOpenDialog(primaryStage));
            apresfile.setText(selectedFiles.get(1).getPath());
        });
        ssvideo.setOnAction(e -> {
            selectedFiles.add( fileChooser.showOpenDialog(primaryStage));
            videofile.setText(selectedFiles.get(2).getPath());
        });
    }
    
    @FXML
    private void addSuccessStory(ActionEvent event) {
        
        //Success Story
        SuccessStory successStory = new SuccessStory();
        successStory.setTitre(sstitle.getText());
        successStory.setText(ssdescription.getText());
        successStory.setDateCreation(new Date());
        
        SuccessStoryService successStoryService = new SuccessStoryService();
        
        
        // tag starting 
        selectedFiles.forEach((file)-> {
        ContenuMultimedia contenuMultimedia=new ContenuMultimedia("",file);
        TagSuccessStory tagsuccess =new TagSuccessStory(contenuMultimedia);
        contenuMultimedia.sendFileToHTTP(file);
        
               });
        successStoryService.Add(successStory);
        
        
               
    }

    
}
