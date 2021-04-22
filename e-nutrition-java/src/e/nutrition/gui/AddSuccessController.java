/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package e.nutrition.gui;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Models.SuccessStory;
import e.nutrition.Models.Tag;
import e.nutrition.Models.TagSuccessStory;
import e.nutrition.Services.ContenuMultimediaService;
import e.nutrition.Services.SuccessStoryService;
import e.nutrition.Services.TagService;
import e.nutrition.Services.TagSuccessStoryService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            avantfile.setText(selectedFile.getPath());
        });
        ssapres.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            apresfile.setText(selectedFile.getPath());
        });
        ssvideo.setOnAction(e -> {
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            videofile.setText(selectedFile.getPath());
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
        int successStoryId = successStoryService.addAndGet(successStory);
        successStory.setId(successStoryId);
        
        addContenuMultimediaToStory(apresfile, successStory);        
        addContenuMultimediaToStory(avantfile, successStory);        
        addContenuMultimediaToStory(videofile, successStory);

    }

    private void addContenuMultimediaToStory(TextField file, SuccessStory successStory) {
        //Contenu Multimédia
        TagSuccessStory tagSuccessStory = new TagSuccessStory();
        Tag tag = new Tag();
        ContenuMultimedia contenuMultimedia = new ContenuMultimedia() ;
        contenuMultimedia.setDtype("contenumultimedia");
        contenuMultimedia.setNomFile(file.getText());
        contenuMultimedia.setUpdatedAt(new Date());
        ContenuMultimediaService contenuMultimediaService = new ContenuMultimediaService();
        Integer multimediaId = contenuMultimediaService.addAndGetId(contenuMultimedia);
        contenuMultimedia.setId(multimediaId);
        tag.setContenuMultimediaId(contenuMultimedia);
        tag.setDtype("contenumultimedia");
        TagService tagService = new TagService();
        tag.setId(tagService.addAndGet(tag));
        tagSuccessStory.setId(tag.getId());
        tagSuccessStory.setTag(tag);
        tagSuccessStory.setSuccessStoryId(successStory);
        TagSuccessStoryService tagSuccessStoryService = new TagSuccessStoryService();
        tagSuccessStoryService.addAndGet(tagSuccessStory);
    }
    
}
