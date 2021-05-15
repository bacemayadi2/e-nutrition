package e.nutrition.views.Proportion;

import com.codename1.components.ScaleImageLabel;
import com.codename1.demos.grub.interfaces.FoodCategory;
import com.codename1.demos.grub.interfaces.Restaurant;
import com.codename1.demos.grub.models.AccountModel;
import com.codename1.demos.grub.models.FoodCategoryModel;
import com.codename1.rad.controllers.ActionSupport;
import com.codename1.rad.controllers.FormController;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.EntityList;
import com.codename1.rad.models.Property;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.ui.*;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.*;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

import static com.codename1.ui.CN.*;
import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Proportion;
import e.nutrition.services.ServiceProportion;
import e.nutrition.utils.Statics;
import java.util.ArrayList;

public class ProportionView extends AbstractEntityView{

    private Node viewNode;
    private Property nameProp, pictureUrlProp, categoryProp, ratingProp, menuProp, orderProp, estimatedDeliveryTimeProp, deliveryFeeProp, distanceProp;
    private  ArrayList<Proportion> proportions = new ArrayList();
    private Container restInfo;
    private Container dishesContainer ;
    private static EncodedImage placeHolder =  EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png").scaled(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayWidth() / 2), false);
    private float totalCalories=0,totalLipides=0,totalGlucides=0,totalProteins=0;

    public static final ActionNode.Category SHOW_ORDER = new ActionNode.Category();
    public static final ActionNode.Category ADD_TO_FAVORITE = new ActionNode.Category();
    public static final ActionNode.Category REMOVE_FAVORITE = new ActionNode.Category();

    public void CalculerTotalCalorie()
    {
        for (int i=0;i<proportions.size();i++)
        {
            totalCalories+=proportions.get(i).getCalorie();
            totalLipides+=proportions.get(i).getLipides();
            totalGlucides+=proportions.get(i).getGlucides();
            totalProteins+=proportions.get(i).getProteines();
        }
       
    }  
    
    public ProportionView(Entity appEntity, Node viewNode) {
        super(appEntity);
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setScrollableY(true);
        setScrollVisible(false);

        proportions =ServiceProportion.getInstance().getProportion(71);

        createForm();
        update() ;
    }

    public void createForm(){
       restInfo = new Container(new LayeredLayout());

        Button backButton = new Button(FontImage.MATERIAL_KEYBOARD_ARROW_LEFT);
        backButton.setUIID("RestBackButton");
        backButton.addActionListener(evt -> {
            evt.consume();
            ActionSupport.dispatchEvent(new FormController.FormBackEvent(backButton));
        });

      /*  Button cart = new Button(FontImage.MATERIAL_SHOPPING_CART, "RestaurantActionButton");
        cart.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(SHOW_ORDER);
            if (action != null) {
                action.fireEvent(rest, ProportionView.this);
            }
        });*/

     /*   Style likeStyle = UIManager.getInstance().getComponentStyle("RestaurantActionButton");
        CheckBox like = CheckBox.createToggle(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE_BORDER, likeStyle));
        like.setSelected(((AccountModel)account).isFavorite(rest));
        like.setUIID("RestaurantActionButton");
        like.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, likeStyle));
        AccountModel accountModel = (AccountModel) account;
        accountModel.isFavorite(rest);*/
/*
        like.addActionListener(evt -> {
            evt.consume();
            if(like.isSelected()){
                ActionNode action = mainWindowNode.getInheritedAction(ADD_TO_FAVORITE);
                if (action != null) {
                    action.fireEvent(rest, ProportionView.this);
                }
            }else{
                ActionNode action = mainWindowNode.getInheritedAction(REMOVE_FAVORITE);
                if (action != null) {
                    action.fireEvent(rest, ProportionView.this);
                }
            }
        });
*/
        EncodedImage restImage = placeHolder;

        String url;
        url=Statics.BASE_URL+"/multimedia/calorie.jpg";
        Image imageCalorie;
                 imageCalorie = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
        ScaleImageLabel restaurantImageLabel = new ScaleImageLabel(imageCalorie);
        restaurantImageLabel.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        restaurantImageLabel.setUIID("RestImage");
        Container emptyCnt = new Container(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(getDisplayWidth(), getDisplayHeight() / 10);
            }
        };

        Container shadowContainer = new Container();
        shadowContainer.setUIID("ShadowContainer");

        emptyCnt.setUIID("emptyRestaurantCnt");
        Container imageContainer = BorderLayout.center(LayeredLayout.encloseIn(restaurantImageLabel, shadowContainer));
        imageContainer.add(BorderLayout.SOUTH, emptyCnt);
        restInfo.add(imageContainer);

        this.CalculerTotalCalorie();
        Label totalclaorielabel = new Label(String.valueOf(Math.round(totalCalories))+" kcal");
        totalclaorielabel.setUIID("RestaurantCategory");
        

    
        Label restName = new Label("aliment manger aujourd'huit" ); 
        restName.setUIID("RestaurantNameHeader");
        Label glucide = new Label(" " +Math.round(totalGlucides) + " G", "RestPreviewDeliveryTime");
        glucide.setIcon(getGlobalResources().getImage("glucide.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label protein = new Label(" " + Math.round(totalProteins) + "G", "RestPreviewRating");
        protein.setIcon(getGlobalResources().getImage("protein.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label lipide = new Label(" " + Math.round(totalLipides) + "G", "RestPreviewDistance");
        lipide.setIcon(getGlobalResources().getImage("lipide.png").scaled(convertToPixels(4), convertToPixels(4)));
        
        Container restDetails = new Container(new BoxLayout(BoxLayout.Y_AXIS), "RestDetails");
        Container nutritfValue = new Container(new FlowLayout(Component.CENTER), "RestTimeRatingCnt");
        nutritfValue.addAll(glucide, protein, lipide);
        restDetails.addAll(restName, totalclaorielabel,nutritfValue);
        restInfo.add(BorderLayout.south(restDetails));
    //    restInfo.add(BorderLayout.centerCenterEastWest(null, FlowLayout.encloseRight(like, cart), BorderLayout.north(backButton)));
        add(restInfo);

        Tabs menuContainer = new Tabs(Component.TOP);
        menuContainer.getTabsContainer().setUIID("RestTabContainer");
        menuContainer.setTabUIID("RestTab");
        menuContainer.add(BorderLayout.CENTER,createProportionsView());
      /*  if (getEntity().get(menuProp) instanceof EntityList) {
            EntityList<Entity> categoryList = (EntityList) (getEntity().get(menuProp));
            for (Entity category : categoryList) {
                FoodCategoryModel fc = ((FoodCategoryModel) category);
                if (fc.get(FoodCategory.dishes) instanceof EntityList){
                    EntityList dishesList = (EntityList)fc.get(FoodCategory.dishes);
                    menuContainer.addTab(fc.getText(FoodCategory.name), createCategoryView(dishesList));
                }
            }
        }*/
        add(menuContainer);   
    }
    
    @Override
    public void update() {
        System.out.println(dishesContainer.getComponentCount());
                 for (int i=0 ;i < proportions.size();i++) {
                     
            dishesContainer.getComponentAt(0).remove();
        }
                 proportions.clear();
    proportions=ServiceProportion.getInstance().getProportion(71);
            for (int i=0 ;i <proportions.size();i++) {
            OneProportionView p = new OneProportionView(proportions.get(i), viewNode);
            dishesContainer.addComponent(i,p);
        }
            CalculerTotalCalorie();
                this.revalidateWithAnimationSafety();

    
    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return null;
    }

    private Component createProportionsView() {
        dishesContainer = new Container();
        dishesContainer.setUIID("MenuContainer");

        int numOdDishes = proportions.size();
        int rows = (numOdDishes % 3 == 0) ? numOdDishes / 3 : numOdDishes / 3 + 1;
        int landscapeRows = (numOdDishes % 6 == 0) ? numOdDishes / 6 : numOdDishes / 6 + 1;
        if (CN.isTablet()){
            dishesContainer.setLayout(new GridLayout(landscapeRows, 6));
        }else{
            dishesContainer.setLayout(new GridLayout(rows, 3, landscapeRows, 6));
        }
        System.out.println("heeeerrrrr r rr"+proportions.size());
        for (int i=0 ;i<proportions.size();i++) {
            OneProportionView p = new OneProportionView(proportions.get(i), viewNode);
            dishesContainer.add(p);
        }
        return dishesContainer;
    }
}