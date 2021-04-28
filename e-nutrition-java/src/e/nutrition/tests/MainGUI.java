package e.nutrition.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Login.fxml"));

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddChallenge.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/GestionUsers.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/GestionPatients.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/GestionDoctors.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../gui/GestionSecretaires.fxml"));
//        primaryStage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(root);
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
