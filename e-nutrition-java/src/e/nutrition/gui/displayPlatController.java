/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Models.Nutritionniste;
import e.nutrition.Models.Plat;
import e.nutrition.Services.ServiceNutritionniste;
import e.nutrition.Services.ServicePlat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


/**
 *
 * @author bacem
 */
public class displayPlatController implements Initializable{
    
     @FXML
    private ImageView image;
     @FXML
     private Label nbportion,calorie,nom_nutritionniste,nom_plat;
     @FXML
     private Circle avatar_nutritionniste;
     @FXML
     private TextArea description;
         ServicePlat sp =new ServicePlat();
                  ServiceNutritionniste sN= new ServiceNutritionniste();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refrechplat();
    }
    
    public void refrechplat()
    {
        Plat plat=sp.find(69).get(0);
        image.setImage(new Image(plat.getTags().get(0).getUrl()));
                System.out.println(plat.getTags().get(0).getUrl());
        nom_plat.setText(sp.Display().get(0).getNom());
        Nutritionniste nutritioniste= sN.getById(plat.getUser());
        System.out.println(plat.getUser() +"by " + nutritioniste );
        if (nutritioniste.getPhotoDeProfile() != null)
        {
        avatar_nutritionniste.setStroke(Color.SEAGREEN);
        avatar_nutritionniste.setFill(new ImagePattern(new Image(nutritioniste.getPhotoDeProfile().getUrl())));
            System.out.println(nutritioniste.getPhotoDeProfile().getUrl());
        avatar_nutritionniste.setEffect(new DropShadow(+25d , 0d, +2d, Color.DARKSEAGREEN));
        }
      
        

    }
}