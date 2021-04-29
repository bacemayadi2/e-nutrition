/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Medicament;
import e.nutrition.Models.Patient;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceMedicament;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class FicheDetailController implements Initializable {

    @FXML
    private FlowPane ficheContainer;
    @FXML
    private FlowPane FlowPaneSport;
    @FXML
    private Label label_id;
   
    ServiceMedicament sm = new ServiceMedicament();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
               try {
            aff();
        } catch (SQLException ex) {
            Logger.getLogger(FicheFrontController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }
        });
       

          
    }    
    
    void aff() throws SQLException {
                ServiceFicheConsultation ser = new ServiceFicheConsultation();
              
        ObservableList<FicheConsultation> listComp = FXCollections.observableArrayList(ser.DisplayAllPatientDetail(Integer.valueOf(label_id.getText())));

        System.out.println("We're right here for now ");
        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        System.out.println(listComp.size());
        for (int i = 0; i <1; i++) {

            Separator h = new Separator(Orientation.VERTICAL);
            h.setPrefWidth(20);
            h.setPrefHeight(24);
            h.setVisible(false);
           as.add(h);

            VBox VBoxComp = new VBox();
             VBoxComp.setSpacing(5);
        VBoxComp.setStyle("-fx-background-color : #FFFFFF;");    
    VBoxComp.setStyle("-fx-border-color : #EAE9FC;");
            VBoxComp.setAlignment(Pos.CENTER);
            VBoxComp.setPrefHeight(500);
            VBoxComp.setPrefWidth(495);
            HBox panne = new HBox();
              panne.setSpacing(5);
             panne.setStyle("-fx-background-color : #FFFFFF;");
          
           
         //   VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(-10);
            panne.setPrefWidth(90);
                    Circle c = new Circle(90);

//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
     
        c.setStroke(Color.SEAGREEN);
            System.out.println(listComp.get(i).getId());
             System.out.println(listComp.get(i).getTags());
        c.setFill(new ImagePattern(new Image(listComp.get(i).getTags().get(0).getUrl())));
        c.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));

       

            VBoxComp.getChildren().add(c);



            Label taille = new Label("Taille/m : " + listComp.get(i).getTaille());
            Label category2 = new Label("poids/kg: " + listComp.get(i).getPoids());
            Label participant = new Label("Date: " + listComp.get(i).getCreation_date());
              Label sym = new Label("symptome: " + listComp.get(i).getSymptome());
              Label apetit = new Label("apetit: " + listComp.get(i).getApetit());
                Label description = new Label("description: " + listComp.get(i).getDescription());
      
              
  
            VBoxComp.getChildren().add(taille);
            VBoxComp.getChildren().add(category2);
            VBoxComp.getChildren().add(participant);
            VBoxComp.getChildren().add(sym);
            
           
              List<Medicament> lista =  sm.DisplayByFiche(Integer.valueOf(label_id.getText()));
              
                      for (Medicament aux : lista)
                {
                    Label nommed =new Label("Nom med :"+aux.getNom());
                    Label quantite =new Label("Quantité :"+aux.getQuantite());
                      Label duree =new Label("Duree :"+aux.getDuree());
            VBoxComp.getChildren().add(nommed);
            VBoxComp.getChildren().add(quantite);
                }


            vbx.add(VBoxComp);
            ficheContainer.getChildren().add(vbx.get(i));

            ficheContainer.getChildren().add(as.get(i));
            

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 2) % 3) == 1) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(1010);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                ficheContainer.getChildren().add(sepHoriz);

            }
            
              int idFiche = listComp.get(i).getId();
            FicheConsultation f = ser.FindById(idFiche);
          Button btnDetail = new Button("retour");
              panne.getChildren().add(btnDetail);
            VBoxComp.getChildren().add(panne);
            System.out.println(listComp.get(i));
            btnDetail.setStyle("-fx-background-color : 	#eae9fc;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    System.out.println(f);
                    taille.setText(String.valueOf(f.getTaille()));
                   // category.setText(s1.getCategorie());
                    apetit.setText(f.getApetit());
                  //  date.setValue(s1.getDate_start().toLocalDate());
                                      try {
                FXMLLoader Loader = new FXMLLoader();
                Loader.setLocation(getClass().getResource("Dashboard.fxml"));
                Parent parent = Loader.load();
                Scene scene = new Scene(parent);
                Stage window = (Stage) ficheContainer.getScene().getWindow();
                window.setScene(scene);
                window.show();

                            } catch (IOException ex) {
                        Logger.getLogger(FicheFrontController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //nbr.setText(String.valueOf(s1.getNbr_participants()));
                   // nameImg.setText(s1.getImage());
                   // ImagePath = s1.getImage();
               

                }
            });
        
            
            
        }
      
        
        
    }
      public void imageFiche( String url)
    {
       
               
        

    }

    void setid(int id) {
        label_id.setText(String.valueOf(id));
        System.out.println(label_id);
    }

}

