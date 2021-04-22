/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.tests;

import e.nutrition.Services.ServiceCategorieAliment;
import e.nutrition.gui.AutocompleteMultiSelectionBox;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

/**
 *
 * @author bacem
 */
public class AlimentGui extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException 
    {
        /**Button btn = new Button("Sort");
        StackPane.setAlignment(btn, Pos.BOTTOM_CENTER);
        AutocompleteMultiSelectionBox selectize =new AutocompleteMultiSelectionBox();
        
            btn.setOnAction((ActionEvent event) -> {
        FXCollections.sort(selectize.getTags());
    });
                Button btn2 = new Button("add \"42\"");
    btn2.setOnAction(evt -> {
        if (!selectize.getTags().contains("42")) {
            selectize.getTags().add("42");
        }
    });*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/FXML.fxml"));
        Parent root = loader.load();
                
        Scene scene = new Scene(root);
         JMetro jMetro =new  JMetro(scene, Style.LIGHT);
        primaryStage.setTitle("Aliment");
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
