/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import e.nutrition.Models.Mesure;
import e.nutrition.Services.Blog_Post_Service;
import e.nutrition.Services.Mesure_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 */
public class Mesure_List_Controller implements Initializable {

    @FXML
    private JFXTextField tfsearch;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vBox;
    @FXML
    private JFXButton btnAdd;
 private Label lbla;
    Stage stage;
    Scene scene;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            // TODO
            getALLMesure();
            System.out.println(scroll.widthProperty());

        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
    }

 public void getALLMesure() throws SQLException {

            Mesure_Service service = new Mesure_Service();
        for (int i = 0; i < service.Get_All_Mesure().size(); i++) {
            System.out.println("size : " + service.Get_All_Mesure().size());
            vBox.getChildren().add(carte(service.Get_All_Mesure().get(i)));

        }
        scroll.setContent(vBox);

    }    
  public Pane carte(Mesure mesure) throws SQLException {

        String stylep = "-fx-effect: dropshadow(three-pass-box, derive(cadetblue, -20%), 10, 0, 5, 5);\n  -fx-background-color: white \n ;-fx-border-color: #b0b1b2 \n ; -fx-border-width: 1; \n -fx-border-radius: 10;  ";
        String style2 = " -fx-background-color : linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12)";
        Pane pane = new Pane();
        pane.setStyle(style2);
       
        Label nom = new Label();
        Label datedebut = new Label();
        Label datefin = new Label();
        Label prix = new Label();
        Label description = new Label();
        Label soldelabel = new Label();
        Blog_Post_Service blog_service = new Blog_Post_Service();

      
        JFXButton delete = new JFXButton();
        JFXButton edit = new JFXButton();
        JFXButton claims = new JFXButton("open course claims");

        nom.setText(("Taille : " + mesure.getTaille()+""+"CM"));
        nom.setTextFill(Color.WHITE);
        claims.setLayoutX(1250);
        claims.setLayoutY(3);
        claims.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-color: #DC143C ;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;"
        );

       
        nom.setStyle(
                ".text { -fx-font-smoothing-type: lcd; }"
        );
        nom.setLayoutX(480);
        nom.setLayoutY(5);
        //nom.setFont(Font.font(27));
        nom.setFont(Font.font("Verdana", FontWeight.BOLD, 45));

        datedebut.setLayoutX(22);
        datedebut.setLayoutY(115);
        datedebut.setFont(Font.font(21));
        datedebut.setText("In : " + mesure.getDate_mesure()) ;
        datedebut.setTextFill(Color.WHITE);

        description.setLayoutX(22);
        description.setLayoutY(80);
        description.setFont(Font.font(21));
        description.setText("Poids : " + mesure.getPoids()+""+"KG");
        description.setTextFill(Color.WHITE);

       

      

        delete.setLayoutX(850);
        delete.setLayoutY(140);
        delete.setPrefHeight(22);
        delete.setPrefWidth(200);
        delete.setText("Delete");
        delete.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-color: #90EE90 ;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;"
        );

        delete.setOnAction((event) -> {
            try {
                Mesure_Service service = new Mesure_Service();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + mesure.getId()+ " ?", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                service.Delete_Mesure(mesure.getId());
            }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        });
        edit.setLayoutX(850);
        edit.setLayoutY(70);
        edit.setPrefHeight(22);
        edit.setPrefWidth(200);
        edit.setText("Edit ");
        edit.setStyle(
                "-fx-font-size: 24px;"
                + "-fx-font-weight: bold;"
                + "-fx-background-color: #DC143C ;"
                + "-fx-border-style: solid inside;"
                + "-fx-border-width: 2;"
                + "-fx-border-insets: 5;"
                + "-fx-border-radius: 5;"
                + "-fx-border-color: blue;"
        );
         edit.setOnAction((event)->{
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditMesure.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                
                EditMesureController mesureController = fxmlLoader.getController();
                mesureController.InitSelectedMesure(mesure);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException ex) {
            }
    });
            
      
        pane.getChildren().addAll( nom, datedebut, edit, datefin, description, soldelabel, delete);

        return pane;
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddMesure.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
}
