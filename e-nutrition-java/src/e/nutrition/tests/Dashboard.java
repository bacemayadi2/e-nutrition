/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.tests;

import e.nutrition.Models.ContenuMultimedia;
import e.nutrition.Services.ServiceContenuMultimedia;
import java.net.URL;
import java.sql.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author PC
 */
public class Dashboard extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
       
        
        
        
        
        
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("../gui/Dashboard.fxml");
        loader.setLocation(url);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
