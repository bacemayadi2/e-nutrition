package e.nutrition.gui;
import e.nutrition.Models.RendezVous;
import e.nutrition.Models.SuccessStory;
import e.nutrition.Services.RendezVousService;
import e.nutrition.Services.SuccessStoryService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import static java.util.Arrays.stream;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * FXML Controller class
 *
 * @author Abdelhamid
 */

public class DisplaySuccessController implements Initializable {

    @FXML
    private Text txtx;
    @FXML
    private ListView<String> listSuccess;
    @FXML
    private Button successbtn;


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        SuccessStoryService successStoryService = new SuccessStoryService();
        List<SuccessStory> stories = successStoryService.Display();
        for(SuccessStory succ : stories){
            listSuccess.getItems().add(succ.toString());
        }
        
        
        
        for(SuccessStory story : stories){
            txtx.setText(story.getTitre());
           
                
           
      
           /* try {
                if (!story.getTags().isEmpty()){
                InputStream stream1 , stream2 = null;
                //creating the image object
                stream1 = new FileInputStream(story.getTags().get(0).getUrl());
                Image image1 = new Image("file:"+stream1);
                System.out.println(story.getTags().get(0).getUrl());
                stream2 = new FileInputStream(story.getTags().get(1).getUrl());
                Image image2 = new Image("file:"+stream2);
                //Creating the image view
                ImageView imageView1 = new ImageView(image1);
                //Setting the image view parameters
                imageView1.setX(250);
                imageView1.setY(250);
                imageView1.setFitWidth(200);
                imageView1.setPreserveRatio(true);
                ImageView imageView2 = new ImageView(image2);
                //Setting the image view parameters
                imageView2.setX(500);
                imageView2.setY(250);
                imageView2.setFitWidth(350);
                imageView2.setPreserveRatio(true);
                //Setting the view port
                Rectangle2D rect2 = new Rectangle2D(45, 30, 250, 250);
                imageView2.setViewport(rect2);
                
               
                    //Setting the Scene object
                    Parent root = txtx.getScene().getRoot();
                    root.getChildrenUnmodifiable().add(imageView1);
                    root.getChildrenUnmodifiable().add(imageView2);
                stream1.close();
                stream2.close();
                }
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DisplaySuccessController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DisplaySuccessController.class.getName()).log(Level.SEVERE, null, ex);
            } */
            }
        }

    @FXML
    private void deleteSuccess(ActionEvent event) {
         int selected = listSuccess.getSelectionModel().getSelectedIndex();
        listSuccess.getItems().remove(selected);
        String selectedItem = listSuccess.getSelectionModel().getSelectedItem();
        Integer indexToDelete = Integer.valueOf(selectedItem.substring(17, 19));
        RendezVousService rendezVousService = new RendezVousService();
        rendezVousService.deleteById(selected);
    }
   }
            
            
        
    
    

