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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
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
    private ImageView imageavant;
    @FXML
    private ImageView imageapres;
    @FXML
    private Label titresuccess;
    @FXML
    private Label descsuccess;


    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        refrechSuccess();
    
            }

    private void refrechSuccess() {
        SuccessStoryService sp = new SuccessStoryService();
        System.out.println(sp.Display().get(0).getTags().get(0).getUrl());
                
        imageavant.setImage(new Image(sp.Display().get(0).getTags().get(0).getUrl()));
        //imageapres.setImage(new Image(sp.Display().get(0).getTags().get(1).getUrl()));
        titresuccess.setText(sp.Display().get(1).getTitre());
        descsuccess.setText(sp.Display().get(1).getText());
        
    }
    }
        

    
   
            
            
        
    
    

