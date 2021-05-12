/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.nutrition.views;

import com.codename1.components.ScaleImageButton;
import com.codename1.demos.grub.views.HomeView;
import com.codename1.rad.models.Entity;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import static com.codename1.ui.CN.convertToPixels;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Plat;
import java.io.IOException;

/*

package com.codename1.demos.grub.views;

import com.codename1.components.ScaleImageButton;
import com.codename1.demos.grub.interfaces.Restaurant;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.Property;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;

import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.util.Resources.getGlobalResources;
*/
/**
 *
 * @author bacem
 */
public class onePlatView extends AbstractEntityView {

    String namePlat, calorie, nbrPortion, duree, pictureProp, user;
    Node viewNode;
    String url;

    private static EncodedImage placeHolder = EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png"), false);

    public onePlatView(Entity entity, Node viewNode){
               super(entity);
        this.viewNode = viewNode;
               
        Plat p =(Plat) entity;
        setLayout(new BorderLayout());
        setUIID("RestaurantPreview");
        this.viewNode = viewNode;

        namePlat = p.getNom(); 
        calorie = String.valueOf(p.getCalculerCalorie());
        nbrPortion = String.valueOf(p.getNbrportion());
        duree = String.valueOf(p.getDuree());
        user = ("salah");

  

        EncodedImage restImage = placeHolder;
       
            if (p.getTags().size() != 0)
            {
                 url=p.getTags().get(0).getUrl();
                 restImage = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
            }
                 //   Image.createImage(p.getTags().get(0).getUrl()); //entity.createImageToStorage(pictureProp, placeHolder);
        

        ScaleImageButton restImageButton = new ScaleImageButton(restImage);
        restImageButton.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        restImageButton.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(HomeView.ENTER_REST);
            if (action != null) {
                action.fireEvent(p, onePlatView.this);
            }
        });

        setLeadComponent(restImageButton);

        Label restNameLabel = new Label(namePlat, "RestPreviewNameLabel");
        Label restCategoryLabel = new Label(calorie, "RestPreviewCategoryLabel");

        Container restTopView = new Container(new LayeredLayout());
        restTopView.add(restImageButton);

        Container nameAndCategoryCnt = new Container(new BorderLayout());
        nameAndCategoryCnt.setUIID("RestaurantPreviewDetailsCnt");
        Container restDetails = BoxLayout.encloseY(restNameLabel, restCategoryLabel);
        nameAndCategoryCnt.add(BorderLayout.SOUTH, restDetails);
        nameAndCategoryCnt.setUIID("BottomShadowContainer");
        restTopView.add(nameAndCategoryCnt);
        
        Label estimatedDeliveryTimeLabel = new Label(" " + duree + " mins", "RestPreviewDeliveryTime");
        estimatedDeliveryTimeLabel.setIcon(getGlobalResources().getImage("delivery-time-icon.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label calories = new Label(" " + calorie + "Kcal", "RestPreviewRating");
        calories.setIcon(getGlobalResources().getImage("rating-icon.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label nbrportion = new Label(" " + nbrPortion + "portion", "RestPreviewDistance");
        nbrportion.setIcon(getGlobalResources().getImage("distance-icon.png").scaled(convertToPixels(4), convertToPixels(4)));

        add(BorderLayout.SOUTH, FlowLayout.encloseCenter(estimatedDeliveryTimeLabel, calories, nbrportion));
        add(BorderLayout.CENTER, restTopView);

    }

    @Override
    public void update() {

    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return viewNode;
    }
}


