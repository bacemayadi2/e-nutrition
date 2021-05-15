/*
 * Copyright (c) 2012, Codename One and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Codename One designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Codename One through http://www.codenameone.com/ if you
 * need additional information or have any questions.
 */

package e.nutrition.views;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.demos.grub.Util;
import com.codename1.demos.grub.interfaces.Dish;
import com.codename1.demos.grub.models.DishModel;
import com.codename1.rad.controllers.ActionSupport;
import com.codename1.rad.controllers.FormController;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.EntityList;
import com.codename1.rad.models.Property;
import com.codename1.rad.models.PropertySelector;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.rad.ui.image.RoundRectImageRenderer;
import com.codename1.ui.*;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;

import java.util.HashMap;
import java.util.Map;

import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Composition;
import e.nutrition.entities.Plat;
import e.nutrition.views.onePlatView;
import java.util.ArrayList;
import javafx.scene.Parent;

public class PlatDetailView extends AbstractEntityView{

    private Node viewNode;
    private        Plat p ;
    Container addOns ;

    private java.util.List <Composition> compostions = new ArrayList();
    private String  nom, duree, calorieportion,url;
    private int portion = 1;
    private Label quantityLabel , totalCalorieLabel,totalcalorie;
    private MultiButton addToCart;
    private Container aliments;
    Node addOnNode;
    Entity entity;
    Node viewNodeparent;
    Form previous;

    public static final ActionNode.Category ADD_TO_CART = new ActionNode.Category();

    private static EncodedImage placeHolder = EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png"), false);
    public PlatDetailView(Entity entity, Node viewNodeparent, Node addOnNode,Form previous) {
        super(entity);
         this.addOnNode=addOnNode;
         this.entity= entity;
         this.viewNodeparent=viewNodeparent;
         this.previous= previous;
         this.addOnNode =addOnNode;
         this.createform(entity, viewNodeparent, addOnNode, previous);
    }
      public void  createform(Entity entity, Node viewNodeparent, Node addOnNod,Form previous) {
        setUIID("Dish");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setScrollableY(true);
        setScrollVisible(false);
        p =(Plat) entity ;
        portion=p.getNbrportion();
        this.viewNode = viewNode;
        nom = p.getNom();
        duree =String.valueOf((int) p.getDuree());
         //                if (p.getTags().size() != 0)
         //   {
       //          url=p.getTags().get(0).getUrl();
         //        restImage = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
        //   }
        //limage = entity.findProperty(Dish.pictureUrl);
        calorieportion =String.valueOf(  p.getCalculerCalorie());
        compostions = p.getCompostions();

        Button backButton = new Button(FontImage.MATERIAL_KEYBOARD_ARROW_LEFT);
        backButton.setUIID("DishBackButton");
        backButton.addActionListener(evt -> {
            evt.consume();
         previous.showBack();
          
           // ActionSupport.dispatchEvent(new FormController.FormBackEvent(backButton));
        });
        Label headerLabel = new Label("detail plat", "DishHeaderLabel");
        Container headerCnt = BorderLayout.centerAbsolute(headerLabel).add(BorderLayout.WEST, backButton);
        headerCnt.setUIID("DishHeaderCnt");

                EncodedImage platimage = placeHolder;

        if (p.getTags().size() != 0) {
            url = p.getTags().get(0).getUrl();
            platimage = URLImage.createToStorage(platimage, url, url, URLImage.RESIZE_SCALE);
        }
        RoundRectImageRenderer renderer = new RoundRectImageRenderer(800, 500, 2);

                  Image scaledImage = platimage.scaled(800,500);

        ScaleImageLabel dishImageLabel= new ScaleImageLabel(scaledImage);
        dishImageLabel.setUIID("DishImageLabel");
        add(BoxLayout.encloseYCenter(headerCnt, dishImageLabel));
      //  add(BoxLayout.encloseYCenter(headerCnt));

        Label dishName = new Label(nom, "DishName");
        SpanLabel dishDescription = new SpanLabel("prét en "+ duree +"minute", "DishDescription");

         totalcalorie = new Label(calorieportion+"kcal", "calorie total");
        Button increaseQuantity = new Button(FontImage.MATERIAL_ADD);
        increaseQuantity.setUIID("OrderDishIncreaseButton");
        increaseQuantity.addActionListener(evt->{
            portion++;
            p.recalculatewithNewPortion(portion);

            update();
        });

        Button decreaseQuantity = new Button(FontImage.MATERIAL_REMOVE);
        decreaseQuantity.setUIID("OrderDishDecreaseButton");
        decreaseQuantity.addActionListener(evt->{
            if(portion > 1){
                portion--;
                p.recalculatewithNewPortion(portion);

                update();
            }
        });
        
        quantityLabel = new Label(String.valueOf(portion), "OrderDishQuantityLabel");
        Container quantityContainer = FlowLayout.encloseCenter(decreaseQuantity, quantityLabel, increaseQuantity);
        quantityContainer.setUIID("DishQuantityContainer");

        Container dishQuantityAndPrice = new Container(new FlowLayout(Component.CENTER));
        dishQuantityAndPrice.addAll(quantityContainer, totalcalorie);
        Container descriptionCnt = BoxLayout.encloseY(dishName, dishDescription, dishQuantityAndPrice);

        Container dishRemarksCnt = new Container(new BorderLayout(), "DishRemarksCnt");
        Container dishEtape = new Container(new BoxLayout(BoxLayout.Y_AXIS), "DishBody");

        dishEtape.setUIID("DishRemarksTextArea");
        dishRemarksCnt.add(BorderLayout.NORTH, new Label("etape de preparation", "DishRemarksHeader"));
       // dishRemarksCnt.add(BorderLayout.CENTER, dishRemarks);
       java.util.List <SpanLabel> sp =new ArrayList() ;
        
        for (int i=0 ; i< p.getEtapesDePreparation().size() ; i++)
        {
           
          sp.add(new SpanLabel(i+1+") "+p.getEtapesDePreparation().get(i).getDescription())) ;
            dishEtape.add(sp.get(i));

        }
          dishRemarksCnt.add(BorderLayout.CENTER, dishEtape);

        Container addOnsCnt = new Container(new BorderLayout(), "AddOnsCnt");
         addOns = new Container();
        addOns.setScrollableX(true);
            addOns.setLayout(new GridLayout(compostions.size()));
                    for (int i =0 ; i< compostions.size();i++)
        {
          PlatAlimentView addOn = new PlatAlimentView(compostions.get(i), addOnNode,this);
                addOns.add(addOn);
        }      
     

        addOnsCnt.add(BorderLayout.NORTH, new Label("aliments (choisir celle utlisé)", "AddOnHeader")).
                add(BorderLayout.CENTER, addOns);

        Container dishBody = new Container(new BoxLayout(BoxLayout.Y_AXIS), "DishBody");
        dishBody.add(descriptionCnt);
        dishBody.add(addOnsCnt);
        dishBody.add(dishRemarksCnt);
        add(dishBody);

        Button addToalimentConsomé = new Button("consommé plat ", "DishAddToCartText");
        totalCalorieLabel = new Label(String.valueOf(p.getSelectedAlimentsCalorie())+"Kcal/portion");

        aliments = BorderLayout.centerEastWest(null, totalCalorieLabel , addToalimentConsomé);
        aliments.setUIID("DishAddToCartCnt");
        aliments.setLeadComponent(addToalimentConsomé);
        addToalimentConsomé.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(ADD_TO_CART);
            if (action != null) {
                Map extraData = new HashMap<String, Integer>();
                extraData.put("quantity", portion);
                action.fireEvent(entity, PlatDetailView.this, extraData);
                ActionSupport.dispatchEvent(new FormController.FormBackEvent(backButton));
            }
        });
        add(BorderLayout.center(aliments));
    }

    @Override
    public void update() {
        totalcalorie.setText(String.valueOf(  p.getCalculerCalorie()+"Kcal"));
        quantityLabel.setText(String.valueOf(portion));
        totalCalorieLabel.setText(String.valueOf(p.getSelectedAlimentsCalorie())+"Kcal/portion");
                            for (int i =0 ; i< compostions.size();i++)
        {
                addOns.getComponentAt(i).remove();
                addOns.addComponent(i, new PlatAlimentView(compostions.get(i), addOnNode,this));
        }      
        //Display.getInstance().getCurrent().setTransitionOutAnimator(CommonTransitions.createEmpty());
        
        this.revalidateWithAnimationSafety();
        //recreate the view with
        
    }
    

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return viewNode;
    }
}
