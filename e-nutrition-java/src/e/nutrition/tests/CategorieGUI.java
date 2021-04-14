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
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
/**
 *
 * @author bacem
 */
public class CategorieGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AddCategorieAliment.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
         JMetro jMetro =new  JMetro(root, Style.LIGHT);
        primaryStage.setTitle("Categorie Aliment");
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
