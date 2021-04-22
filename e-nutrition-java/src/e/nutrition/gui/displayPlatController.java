/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.gui;

import e.nutrition.Services.ServicePlat;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;

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
     private ImageView avatar_nutritionniste;
     @FXML
     private TextArea description;
         ServicePlat sp =new ServicePlat();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refrechplat();
    }
    
    public void refrechplat()
    {
        image.setImage(new Image(sp.Display().get(0).getTags().get(0).getUrl()));
                System.out.println(sp.Display().get(0).getTags().get(0).getUrl());
        nom_plat.setText(sp.Display().get(0).getNom());

    }
}
