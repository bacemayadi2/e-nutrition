/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import e.nutrition.Models.BlogPost;
import e.nutrition.Services.Blog_Post_Service;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
 * @author Ayoub
 */
public class List_Blog_Posts_Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vBox;
    private Label lbla;
    Stage stage;
    Scene scene;
    @FXML
    private JFXTextField tfsearch;
    @FXML
    private JFXButton btnAdd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            // TODO
            getALLBlogAndPosts();
            System.out.println(scroll.widthProperty());

        } catch (Exception ex) {
             System.out.println(ex.getMessage());
        }
    }

 public void getALLBlogAndPosts() throws SQLException {

        Blog_Post_Service blog_service = new Blog_Post_Service();
        for (int i = 0; i < blog_service.Get_All_Blog_And_Posts().size(); i++) {
            System.out.println("size : " + blog_service.Get_All_Blog_And_Posts().size());
            vBox.getChildren().add(carte(blog_service.Get_All_Blog_And_Posts().get(i)));

        }
        scroll.setContent(vBox);

    }    
  public Pane carte(BlogPost blog) throws SQLException {

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

        Rating rating = new Rating();
        //rating.setMax((int)blog_service.GETRate(blog.getId()));
        rating.setRating((double) blog_service.GETRate(blog.getId()));
        rating.setPartialRating(true);
        JFXButton delete = new JFXButton();
        JFXButton edit = new JFXButton();
        JFXButton claims = new JFXButton("open course claims");

        nom.setText(blog.getTitle());
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
        datedebut.setText("In : " + blog.getDate()) ;
        datedebut.setTextFill(Color.WHITE);

        description.setLayoutX(22);
        description.setLayoutY(80);
        description.setFont(Font.font(21));
        description.setText("Body : " + blog.getBody());
        description.setTextFill(Color.WHITE);

       

        rating.setLayoutX(22);
        rating.setLayoutY(160);

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
                Blog_Post_Service service = new Blog_Post_Service();
                Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + blog.getTitle() + " ?", ButtonType.YES, ButtonType.CANCEL);
                alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                service.Delete_Blog_Post(blog.getId());
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
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditBlog.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                
                EditBlogController blogController = fxmlLoader.getController();
                blogController.InitSelectedBlog(blog);
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException ex) {
            }
    });
            
      
        //rating
        rating.setOnMouseClicked(event1 -> {

            float s = (float) rating.getRating();

            Blog_Post_Service service = new Blog_Post_Service();

            float r = 0;

            try {
                System.out.println("id:" + blog.getId());
                service.setRating(blog.getId(), s);
                rating.setDisable(false);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

        });
        pane.getChildren().addAll( nom, datedebut, rating, edit, datefin, description, prix, soldelabel, delete);

        return pane;
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddBlog.fxml"));
        
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
   
   
    
}
