package com.codename1.demos.grub.views;

import com.codename1.demos.grub.interfaces.MainWindow;
import com.codename1.rad.models.Entity;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.ui.Component;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;

import static com.codename1.ui.CN.convertToPixels;
import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.views.AllChallengesView;
import e.nutrition.views.ChallengeView;
import e.nutrition.views.PlatsView;

public class MainWindowView extends AbstractEntityView
{

    FavoriteRestaurantsView favoriteView;
    MainWindowOrdersView ordersView;
    HomeView homeView;
    Tabs mainWindowContainer;
    PlatsView platView;
    AllChallengesView challengeView;
    
    private static final int TABS_ICON_SIZE = convertToPixels(4);
    private static final int TABS_ICON_SELECTED_SIZE = convertToPixels(6);

    public MainWindowView(Entity mainWindowEntity, Node profileNode, Node homeViewNode, Node appNode) {
        super(mainWindowEntity);
        setLayout(new BorderLayout());
        setUIID("MainWindow");
        mainWindowContainer = new Tabs();
        mainWindowContainer.setTabPlacement(Component.BOTTOM);
        mainWindowContainer.getTabsContainer().setUIID("MainWindowTabsContainer");
        mainWindowContainer.setUIID("MainWindowTabsCnt");
        mainWindowContainer.setTabUIID("MainWindowTab");
        mainWindowContainer.getTabsContainer().setSafeArea(false);

        
//        SignInView signInView = new SignInView(mainWindowEntity, homeViewNode);
//        mainWindowContainer.addTab("HOME",
//                                    getGlobalResources().getImage("main-window-favorite.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
//                                    getGlobalResources().getImage("main-window-favorite-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
//                                    signInView);

        homeView = new HomeView(mainWindowEntity, homeViewNode);
        mainWindowContainer.addTab("HOME",
                                    getGlobalResources().getImage("main-window-favorite.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-favorite-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    homeView);
        
          platView = new PlatsView(mainWindowEntity, homeViewNode);

           mainWindowContainer.addTab("plat",
                                    getGlobalResources().getImage("main-window-favorite.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-favorite-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    platView);
    
           
        
          
            challengeView = new AllChallengesView(mainWindowEntity, homeViewNode);

           mainWindowContainer.addTab("DEFIS",
                                    getGlobalResources().getImage("main-window-favorite.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-favorite-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    challengeView);
           
           
        favoriteView = new FavoriteRestaurantsView((Entity)mainWindowEntity.get(MainWindow.profile), homeViewNode);
        mainWindowContainer.addTab("FAVORITE",
                                    getGlobalResources().getImage("main-window-home.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-home-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    favoriteView);

        ordersView = new MainWindowOrdersView(mainWindowEntity.getEntity(MainWindow.profile), homeViewNode);
        mainWindowContainer.addTab("ORDERS",
                                    getGlobalResources().getImage("main-window-orders.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-orders-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    ordersView);

        mainWindowContainer.addTab("PROFILE",
                                    getGlobalResources().getImage("main-window-profile.png").scaled(TABS_ICON_SIZE, TABS_ICON_SIZE),
                                    getGlobalResources().getImage("main-window-profile-selected.png").scaled(TABS_ICON_SELECTED_SIZE, TABS_ICON_SELECTED_SIZE),
                                    new ProfileView(mainWindowEntity.getEntity(MainWindow.profile), profileNode, appNode));
      
        add(BorderLayout.CENTER, mainWindowContainer);
        
    }

    @Override
    public void update() {

    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return null;
    }

    public void addFavorite(Entity rest){
        favoriteView.addFavorite(rest);
    }

    public void removeFavorite(Entity rest){
        favoriteView.removeFavorite(rest);
    }

    public void addCompletedOrder(Entity completedOrder){
        ordersView.addCompletedOrder(completedOrder);
    }

    public void updateDefaultAddressView(){
        homeView.update();
    }

    public void moveToOrders(){
        mainWindowContainer.setSelectedIndex(2);
    }
}
