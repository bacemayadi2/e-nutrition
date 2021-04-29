/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.Composition;
import e.nutrition.Models.EtapeDePreparation;
import e.nutrition.Models.FicheConsultation;
import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Plat;
import e.nutrition.Services.ServiceFicheConsultation;
import e.nutrition.Services.ServiceNutritionniste;
import e.nutrition.Services.ServicePlat;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author bacem
 */
public class frontplats implements Initializable {

    @FXML
    private FlowPane FlowPaneSport;
    @FXML
    private FlowPane platContainer;
                      ServiceNutritionniste sN= new ServiceNutritionniste();
    @FXML
    private Pane home;
    @FXML
    private Pane specifique;
    @FXML
    private ImageView image;
    @FXML
    private Label nbportion;
    @FXML
    private Label calorie;
    @FXML
    private Label nom_nutritionniste;
    @FXML
    private Label description;
    @FXML
    private Circle avatar_nutritionniste;
    @FXML
    private Label nom;
    @FXML
    private ScrollPane flowpanealiment;
    @FXML
    private FlowPane aliment;
    @FXML
    private FlowPane flowpanetape;
    @FXML
    private FlowPane etape;
    @FXML
    private Label proteine;
    @FXML
    private Label glucide;
    @FXML
    private Label lipide;
    @FXML
    private Label kcal;
    @FXML
    private Label kcal_portion;
    @FXML
    private Label portion2;



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        try {
            aff();
        } catch (SQLException ex) {
            Logger.getLogger(FicheFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }    
    
    void aff() throws SQLException {
                                specifique.setVisible(false);
                                home.setVisible(true);

                ServicePlat ser = new ServicePlat();
              
        ObservableList<Plat> listComp = FXCollections.observableArrayList(ser.Display());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<VBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        System.out.println(listComp.size());
        for (int i = 0; i <listComp.size(); i++) {

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
            VBoxComp.setPrefHeight(265);
            VBoxComp.setPrefWidth(270);
            HBox panne = new HBox();
              panne.setSpacing(5);
             panne.setStyle("-fx-background-color : #FFFFFF;");
          
           
         //   VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.CENTER);
            panne.setPrefHeight(30);
            panne.setPrefWidth(80);
                    Circle c = new Circle(60);

                      c.setStroke(Color.SEAGREEN);
        c.setFill(new ImagePattern(new Image(listComp.get(i).getTags().get(0).getUrl())));
        c.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));
//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
     
        c.setStroke(Color.SEAGREEN);
        c.setFill(new ImagePattern(new Image(listComp.get(i).getTags().get(0).getUrl())));
        c.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));

         

            VBoxComp.getChildren().add(c);



            Label nom = new Label( listComp.get(i).getNom());
            Label calorie = new Label("calorie: " + listComp.get(i).getCalculerCalorie()+"kcal");
                    Nutritionniste nutritioniste= sN.getById(listComp.get(i).getUser());

            Label by = new Label("by : " +nutritioniste.getNom() );
              Circle c2 = new Circle(20);
                      c2.setStroke(Color.SEAGREEN);
        c2.setFill(new ImagePattern(new Image(nutritioniste.getPhotoDeProfile().getUrl())));
        c2.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));

  
            VBoxComp.getChildren().add(nom);
            VBoxComp.getChildren().add(calorie);
            VBoxComp.getChildren().add(by);
              VBoxComp.getChildren().add(c2);


            vbx.add(VBoxComp);
            platContainer.getChildren().add(vbx.get(i));

            platContainer.getChildren().add(as.get(i));
            

            if (i == 0) {
                System.out.println("i=0 llllll");
            } else if (((i + 2) % 3) == 1) {
                Separator sepHoriz = new Separator(Orientation.HORIZONTAL);
                sepHoriz.setPrefWidth(1010);
                sepHoriz.setPrefHeight(30);
                sepHoriz.setVisible(false);
                platContainer.getChildren().add(sepHoriz);

            }
            
           //   int idplat = listComp.get(i).getId();
            Plat p = listComp.get(i);
          Button btnDetail = new Button("voir plus");
              panne.getChildren().add(btnDetail);
            VBoxComp.getChildren().add(panne);
            System.out.println(listComp.get(i));
            btnDetail.setStyle("-fx-background-color : 	#eae9fc;");
            btnDetail.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

               Pane newLoadedPane;
                    try {
                        newLoadedPane = FXMLLoader.load(getClass().getResource("displayplat.fxml"));
                        home.setVisible(false);
                        specifique.setVisible(true);
                        refrechplat(p);
                                
                    } catch (IOException ex) {
                        Logger.getLogger(frontplats.class.getName()).log(Level.SEVERE, null, ex);
                    }
             
               

                }
            });
        
            
            
        }
      
        
        
    }
     public void refrechplat(Plat p)
    {
        Plat plat=p;
        image.setImage(new Image(plat.getTags().get(0).getUrl()));
                System.out.println(plat.getTags().get(0).getUrl());
        Nutritionniste nutritioniste= sN.getById(plat.getUser());
        System.out.println(plat.getUser() +"by " + nutritioniste );
        if (nutritioniste.getPhotoDeProfile() != null)
        {
        avatar_nutritionniste.setStroke(Color.SEAGREEN);
        avatar_nutritionniste.setFill(new ImagePattern(new Image(nutritioniste.getPhotoDeProfile().getUrl())));
            System.out.println(nutritioniste.getPhotoDeProfile().getUrl());
        avatar_nutritionniste.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));
        }
        nbportion.setText(String.valueOf(p.getNbrportion()));
        calorie.setText(String.format("%.2f",p.getCalculerCalorie()));
        description.setText(p.getDescription());
        nom.setText(p.getNom());
        portion2.setText(String.valueOf(p.getNbrportion()));
        proteine.setText(String.format("%.2f",p.getProteines()));
        glucide.setText(String.format("%.2f",p.getGlucides()));
        lipide.setText(String.format("%.2f",p.getLipidies()));
        kcal.setText(String.format("%.2f",p.getCalculerCalorie()));
        kcal_portion.setText(String.format("%.2f",(p.getCalculerCalorie()/p.getNbrportion())));
      refrechalimentlist(p);
      refrechetapelist(p);
        


    }
     
     public void refrechalimentlist(Plat p)
     {
         // aliment 
         ObservableList<Composition> listComp = FXCollections.observableArrayList(p.getCompostions());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<HBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        System.out.println(listComp.size());
        for (int i = 0; i <listComp.size(); i++) {

            Separator h = new Separator(Orientation.HORIZONTAL);
            h.setPrefWidth(10);
            h.setPrefHeight(10);
            h.setVisible(false);
           as.add(h);

            HBox hBoxComp = new HBox();
             hBoxComp.setSpacing(0);
        hBoxComp.setStyle("-fx-background-color : #FFFFFF;");    
    hBoxComp.setStyle("-fx-border-color : #EAE9FC;");
            hBoxComp.setAlignment(Pos.TOP_CENTER);
            hBoxComp.setPrefHeight(30);
            hBoxComp.setPrefWidth(900);
            HBox panne = new HBox();
              panne.setSpacing(1);
             panne.setStyle("-fx-background-color : #FFFFFF;");
          
           
         //   VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.TOP_LEFT);
            panne.setPrefHeight(30);
            panne.setPrefWidth(80);
                   
//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
     


            Label nom = new Label( listComp.get(i).getAliment().getNom()+"                ");
            Label quantité = new Label("quantité: " + listComp.get(i).getPoid()+"                ");
            Label calorie =new Label("calorie" + listComp.get(i).calculerCalorieParpoid()+"                ");
  
            hBoxComp.getChildren().add(nom);
            hBoxComp.getChildren().add(quantité);
            hBoxComp.getChildren().add(calorie);


            vbx.add(hBoxComp);
            aliment.getChildren().add(vbx.get(i));

            aliment.getChildren().add(as.get(i));
            
            
     }
    
}
     public void refrechetapelist(Plat p)
     {
         // aliment 
         ObservableList<EtapeDePreparation> listetape = FXCollections.observableArrayList(p.getEtapesDePreparation());

        ArrayList<Separator> as = new ArrayList<>();
        ArrayList<HBox> vbx = new ArrayList<>();
        ArrayList<HBox> btnP = new ArrayList<>();

        System.out.println(listetape.size());
        for (int i = 0; i <listetape.size(); i++) {

            Separator h = new Separator(Orientation.HORIZONTAL);
            h.setPrefWidth(10);
            h.setPrefHeight(10);
            h.setVisible(false);
           as.add(h);

            HBox hBoxComp = new HBox();
             hBoxComp.setSpacing(0);
        hBoxComp.setStyle("-fx-background-color : #FFFFFF;");    
    hBoxComp.setStyle("-fx-border-color : #EAE9FC;");
            hBoxComp.setAlignment(Pos.TOP_LEFT);
            hBoxComp.setPrefHeight(50);
            hBoxComp.setPrefWidth(1060);
            HBox panne = new HBox();
              panne.setSpacing(1);
             panne.setStyle("-fx-background-color : #FFFFFF;");
          
           
         //   VBoxComp.setStyle("-fx-border-color : #2B48E8;");
            panne.setAlignment(Pos.TOP_LEFT);
            panne.setPrefHeight(30);
            panne.setPrefWidth(50);
                   
//                ImageView img = new ImageView(new Image(new FileInputStream("C:\\Users\\loume78\\Desktop\\Mon_cv\\loume.png")));
//                img.setFitHeight(10);
//                img.setFitWidth(20);
     

        
            Label nom = new Label(i+")"+ listetape.get(i).getDescription()+"                ");
            nom.setWrapText(true);
           
            hBoxComp.getChildren().add(nom);
  


            vbx.add(hBoxComp);
            etape.getChildren().add(vbx.get(i));

            etape.getChildren().add(as.get(i));
            
            
     }
    
}
}
