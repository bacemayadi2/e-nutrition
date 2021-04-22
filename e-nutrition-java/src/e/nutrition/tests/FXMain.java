/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package e.nutrition.tests;

import e.nutrition.Models.SuccessStory;
import e.nutrition.Services.SuccessStoryService;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Abdelhamid
 */
public class FXMain extends Application {
    
    
    private static final String ADD_RENDEZ_VOUS_GUI = "../gui/AddRendezVous.fxml";
    private static final String ADD_COMMENT_GUI = "../gui/AddComment.fxml";
    private static final String ADD_Success_GUI = "../gui/AddSuccess.fxml";
    private static final String DISPLAY_Success_GUI = "../gui/DisplaySuccess.fxml";
    private static final String DELETE_RENDEZ_VOUS_GUI = "../gui/DeleteRDV.fxml";
    
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(ADD_RENDEZ_VOUS_GUI);
        loader.setLocation(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add Rendez-vous");
        primaryStage.setScene(scene);
        primaryStage.show();
        
       /* FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(ADD_COMMENT_GUI);
        loader.setLocation(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add comment");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        /*FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(ADD_Success_GUI);
        loader.setLocation(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Add success story");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        
        /*FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource(DELETE_RENDEZ_VOUS_GUI);
        loader.setLocation(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Delete Rendez vous");    
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
