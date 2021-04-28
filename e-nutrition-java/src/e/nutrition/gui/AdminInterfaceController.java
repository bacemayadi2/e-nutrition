/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Services.ServiceFicheConsultation;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class AdminInterfaceController implements Initializable {

    @FXML
    private FlowPane FlowPaneSport;
    @FXML
    private TextField name;
    @FXML
    private TextField category;
    @FXML
    private TextField type;
    @FXML
    private TextField nbr;
    @FXML
    private DatePicker date;
    @FXML
    private Button uploadButton;
    @FXML
    private ImageView imageView;
    @FXML
    private Label nameImg;
    @FXML
    private Button add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            affichageUS();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AdminInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    
  private void affichageUS() throws SQLException {

           ServiceFicheConsultation sf = new ServiceFicheConsultation();

        ObservableList<FicheConsultation> listComp = FXCollections.observableArrayList(sf.Display());

        System.out.println("We're right here for now ");
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        for (int i = 0; i < listComp.size(); i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(20);
            h.setPrefHeight(24);
            h.setVisible(false);
            as.add(h);

            VBox VBoxComp = new VBox();

            VBoxComp.setSpacing(7);
            VBoxComp.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxComp.setStyle("-fx-border-color : #EAE9FC;");
            VBoxComp.setAlignment(Pos.CENTER);
            VBoxComp.setPrefHeight(265);
            VBoxComp.setPrefWidth(270);
            HBox panne = new HBox();
            panne.setSpacing(5);
            panne.setStyle("-fx-background-color : #FFFFFF;");
            //VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(80);

            Circle c = new Circle(90);

//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
          

            VBoxComp.getChildren().add(c);
    
}
}}