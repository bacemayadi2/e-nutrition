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

import com.codename1.components.ScaleImageLabel;
import com.codename1.demos.grub.interfaces.Address;
import com.codename1.demos.grub.interfaces.Filter;
import com.codename1.demos.grub.interfaces.MainWindow;
import com.codename1.demos.grub.interfaces.Restaurant;
import com.codename1.demos.grub.models.AccountModel;
import com.codename1.demos.grub.models.FilterModel;
import com.codename1.demos.grub.views.RestaurantPreview;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.EntityList;
import com.codename1.rad.models.Property;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;

import java.util.Calendar;
import java.util.List;

import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.CN.isTablet;
import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Plat;
import e.nutrition.services.ServicePlat;
import java.util.ArrayList;
import java.util.Iterator;

public class PlatView extends AbstractEntityView {

    Node viewNode;
    Property  accountProp, filterProp;
    Entity appEntity;
    Container deliverToCnt;
    Label deliverToLabel;
    ServicePlat sP =new ServicePlat();
    ArrayList<Plat> plat =sP.getAllPlats();

    public static final ActionNode.Category POPULAR_EXPLORE = new ActionNode.Category();
    public static final ActionNode.Category RECOMMENDED_EXPLORE = new ActionNode.Category();
    public static final ActionNode.Category ENTER_REST = new ActionNode.Category();
    public static final ActionNode.Category ENTER_FILTER = new ActionNode.Category();
    public static final ActionNode.Category ENTER_SEARCH = new ActionNode.Category();

    public PlatView(Entity appEntity, Node viewNode) {
        super(appEntity);
        this.viewNode = viewNode;
        this.appEntity = appEntity;
        setUIID("plat");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setScrollableY(true);
        setScrollVisible(false);

        accountProp = appEntity.findProperty(MainWindow.profile);
        filterProp = appEntity.findProperty(MainWindow.filter);

        Container topViewImage = new Container(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(Display.getInstance().getDisplayWidth(), (int) (Display.getInstance().getDisplayHeight() / 2.5));
            }
        };
        topViewImage.setUIID("HomeTopViewImage");
        Image homeTopViewImage;
        String welcomeText;
        int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (currHour >= 0 && currHour <= 12){
            homeTopViewImage = getGlobalResources().getImage("morning.png");
            welcomeText = "Good Morning";
        }else if(currHour < 18){
            homeTopViewImage = getGlobalResources().getImage("afternoon.png");
            welcomeText = "Good Afternoon";
        }else{
            homeTopViewImage = getGlobalResources().getImage("evening.png");
            welcomeText = "Good Evening";
        }
        ScaleImageLabel topViewLabel = new ScaleImageLabel(homeTopViewImage){
            @Override
            public Dimension getPreferredSize() {
                return topViewImage.getPreferredSize();
            }
        };
        topViewLabel.setUIID("HomeTopViewImage");
        topViewLabel.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        topViewImage.add(BorderLayout.CENTER, topViewLabel);

        Container emptyGreyContainer = new Container() {
            @Override
            public Dimension getPreferredSize() {
                if (isTablet()){
                    return new Dimension(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 35);
                }else{
                    return new Dimension(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight() / 15);
                }
            }
        };
        emptyGreyContainer.setUIID("EmptyGreyContainer");

        Container topView = new Container(new LayeredLayout());
        topView.add(BoxLayout.encloseY(topViewImage, emptyGreyContainer));

        Image filterButtonImage = getGlobalResources().getImage("filter-button.png").scaled(convertToPixels(5), convertToPixels(5));
        Button filterButton = new Button(filterButtonImage, "HomeFilterButton") {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(convertToPixels(8), convertToPixels(8));
            }
        };
        filterButton.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(PlatView.ENTER_FILTER);
            if (action != null) {
                action.fireEvent(appEntity, PlatView.this);
            }
        });

        TextField searchField = new TextField() {
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.setWidth(Display.getInstance().getDisplayWidth() / 4 * 3);
                return dim;
            }
        };

        searchField.setUIID("HomeSearchField");
        searchField.setHint("Search ...");
        searchField.getHintLabel().setUIID("HomeSearchFieldHint");
        searchField.addActionListener(evt -> {
            evt.consume();
            Entity filter = appEntity.getEntity(filterProp);
            if (filter instanceof FilterModel){
                ((FilterModel) filter).setFreeText(searchField.getText());
                ActionNode action = viewNode.getInheritedAction(PlatView.ENTER_SEARCH);
                if (action != null) {
                    action.fireEvent(appEntity, PlatView.this);
                }
            }
        });

        Container topViewLabelsLayer = new Container(new BorderLayout());
        Container searchCnt = FlowLayout.encloseCenter(searchField, filterButton);
        searchCnt.setUIID("HomeSearchCnt");
        topViewLabelsLayer.add(BorderLayout.SOUTH, searchCnt);
        topView.add(topViewLabelsLayer);

        AccountModel accountModel = (AccountModel) appEntity.getEntity(accountProp);
        deliverToLabel = new Label("", "HomeDeliverToLabel");
        deliverToCnt = BoxLayout.encloseY(new Button("DELIVER TO", "HomeDeliverToHeaderButton"), deliverToLabel);
        deliverToCnt.setUIID("HomeDeliverToCnt");
        deliverToCnt.setVisible(false);
        Entity defaultAddress = accountModel.getDefaultAddress();
        if (defaultAddress != null){
            String city = defaultAddress.getText(Address.city);
            String street = defaultAddress.getText(Address.street);
            deliverToLabel.setText(city + ", " + street);
            deliverToCnt.setVisible(true);
        }

        Container topViewLabelsCnt = BorderLayout.north(BoxLayout.encloseY(deliverToCnt, new Label(welcomeText, "HomeWelcomeTextFirstLine"),
                new Label("Codename One!", "HomeWelcomeTextSecondLine")));

        topView.add(topViewLabelsCnt);
        add(topView);

       

        
      

        Container allRestaurantsCnt = new Container(new BorderLayout());
        allRestaurantsCnt.setUIID("AllPlat");
        Label allRestaurantsLabel = new Label("All plat", "CategoryHeader");
        allRestaurantsCnt.add(BorderLayout.NORTH, allRestaurantsLabel);
        allRestaurantsCnt.add(BorderLayout.CENTER, createAllRestaurantsCnt(plat));
        add(allRestaurantsCnt);

        Button backToTopButton = new Button("BACK TO TOP", "BackToTopButton");
        backToTopButton.addActionListener(evt -> {
            scrollComponentToVisible(topView);
        });
        add(backToTopButton);

    }

    @Override
    public void update() {
        AccountModel account = (AccountModel) getEntity().getEntity(MainWindow.profile);
        Entity defaultAddress = account.getDefaultAddress();
        if(defaultAddress != null){
            String city = defaultAddress.getText(Address.city);
            String street = defaultAddress.getText(Address.street);
            deliverToLabel.setText(city + ", " + street);
            deliverToCnt.setVisible(true);
        }
    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return viewNode;
    }

    private Container createCategoryButton(String name, String icon, ActionListener action) {
        Container categoryButton = new Container(new BorderLayout());
        categoryButton.setUIID("HomeCategoryButton");

        Image categoryButtonImage = getGlobalResources().getImage(icon);
        ScaleImageLabel categoryIcon = new ScaleImageLabel(categoryButtonImage) {
            @Override
            public Dimension getPreferredSize() {
                if(CN.isTablet()){
                    return new Dimension(convertToPixels(8), convertToPixels(8));
                }else{
                    return new Dimension(convertToPixels(6), convertToPixels(6));
                }
            }
        };
        categoryIcon.setUIID("CategoryIconLabel");
        Container iconWrapper = BorderLayout.centerCenter(categoryIcon);
        iconWrapper.setUIID("CategoryIconWrapper");

        Button categoryName = new Button(name, "CategoryNameLabel");
        categoryName.addActionListener(action);
        categoryButton.add(BorderLayout.CENTER, iconWrapper);
        categoryButton.add(BorderLayout.SOUTH, categoryName);
        categoryButton.setLeadComponent(categoryName);
        return categoryButton;
    }

    private Container createPopularCnt(EntityList<Entity> restaurants) {
        Container popularCnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        popularCnt.setScrollableX(true);
        for (Entity rest : restaurants) {
            popularCnt.add(new RestaurantPreview(rest, viewNode));
        }
        return popularCnt;
    }

    private Container createRecommendedCnt(EntityList<Entity> restaurants) {
        Container recommendedCnt = new Container(new BoxLayout(BoxLayout.X_AXIS));
        recommendedCnt.setScrollableX(true);
        for (Entity rest : restaurants) {
            recommendedCnt.add(new RestaurantPreview(rest, viewNode));
        }
        return recommendedCnt;
    }

    private Container createAllRestaurantsCnt(ArrayList<Plat> plats) {
        final int restsCount = plats.size();
        final int landscapeRows = restsCount % 2 == 0 ? restsCount / 2 : restsCount / 2 + 1;
        Container allRestsCnt;
        if (isTablet()){
            allRestsCnt = new Container(new GridLayout(landscapeRows, 2));
        }else{
            allRestsCnt = new Container(new GridLayout(restsCount, 1, landscapeRows, 2));
        }

        for (int i =0 ; i< plats.size();i++)
         {
            
                        allRestsCnt.add(new AllPlatView((Entity) plats.get(i), viewNode));

        }
 
        return allRestsCnt;

    }

    private void updateFilter(int category){
        Entity filter = appEntity.getEntity(filterProp);
        if(filter.get(Filter.categories) instanceof List){
            List categories = (List)filter.get(Filter.categories);
            categories.clear();
            categories.add(category);
        }
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bacem
 */

