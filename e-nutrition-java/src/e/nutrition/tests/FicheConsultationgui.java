/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.tests;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class FicheConsultationgui extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
    
   FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddFicheConsultation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Ajouter une fiche");
      primaryStage.setScene(scene);
        primaryStage.show();
}
/**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
