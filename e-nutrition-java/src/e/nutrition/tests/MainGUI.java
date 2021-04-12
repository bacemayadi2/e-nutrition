package e.nutrition.tests;

import java.io.IOException;
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
 * @author ALADIN
 */
public class MainGUI extends Application 
{
    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddChallenge.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Créer un défi");
        primaryStage.setScene(scene);
        primaryStage.show();
        
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddNutritionniste.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Créer compte nutritionniste");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
