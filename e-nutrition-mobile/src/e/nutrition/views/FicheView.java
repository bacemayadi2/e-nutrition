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
import e.nutrition.entities.FicheConsultation;

/**
 *
 * @author SIRINE
 */
public class FicheView extends AbstractEntityView
{

    String taille, poids, symptome, creation_date, apetit,description, user;
    Node viewNode;
    String url;
    
    private static  EncodedImage placeHolder = EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png"), false);

    public FicheView(Node viewNode, Entity entity) 
    {
        super(entity);
        this.viewNode = viewNode;
        FicheConsultation fiche =(FicheConsultation)entity;
        setLayout(new BorderLayout());
        setUIID("RestaurantPreview");
        this.viewNode = viewNode;
//creation_date = String.valueOf(fiche.getCreation_date());
       
        poids = String.valueOf(fiche.getPoids()); 
         taille = String.valueOf(fiche.getTaille()); 
          symptome = String.valueOf(fiche.getSymptome()); 
            apetit = String.valueOf(fiche.getApetit()); 
       
      
        EncodedImage restImage=placeHolder;
        if (fiche.getTags().size() != 0)
        {
                 url=fiche.getTags().get(0).getUrl();
            restImage = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
        }
//        try {
//            if (challenge.getTags().size() != 0)
//            restImage = Image.createImage(challenge.getTags().get(0).getUrl()); //entity.createImageToStorage(pictureProp, placeHolder);
//        } catch (IOException ex) {
//            System.out.println("error");
//        }
            
//            EncodedImage restImage = placeHolder;
//       
//            if (challenge.getTags().size() != 0)
//                 url=challenge.getTags().get(0).getUrl();
//            restImage = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
//                 //   Image.createImage(p.getTags().get(0).getUrl()); //entity.createImageToStorage(pictureProp, placeHolder);
//        


        ScaleImageButton restImageButton = new ScaleImageButton(restImage);
        restImageButton.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED);

        restImageButton.addActionListener(evt -> {
            evt.consume();
            ActionNode action = viewNode.getInheritedAction(HomeView.ENTER_REST);
            if (action != null) {
                action.fireEvent(fiche, FicheView.this);
            }
        });

        setLeadComponent(restImageButton);

        Label restNameLabel = new Label(taille, "RestPreviewNameLabel");
        Label restCategoryLabel = new Label(poids, "RestPreviewCategoryLabel");

        Container restTopView = new Container(new LayeredLayout());
        restTopView.add(restImageButton);

        Container nameAndCategoryCnt = new Container(new BorderLayout());
        nameAndCategoryCnt.setUIID("RestaurantPreviewDetailsCnt");
        Container restDetails = BoxLayout.encloseY(restNameLabel, restCategoryLabel);
        nameAndCategoryCnt.add(BorderLayout.SOUTH, restDetails);
        nameAndCategoryCnt.setUIID("BottomShadowContainer");
        restTopView.add(nameAndCategoryCnt);
        
        Label estimatedDeliveryTimeLabel = new Label(" " + poids , "RestPreviewDeliveryTime");
        estimatedDeliveryTimeLabel.setIcon(getGlobalResources().getImage("delivery-time-icon.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label calories = new Label(" " + poids + "nb partic", "RestPreviewRating");
        calories.setIcon(getGlobalResources().getImage("rating-icon.png").scaled(convertToPixels(4), convertToPixels(4)));
        Label nbrportion = new Label(" " + taille , "RestPreviewDistance");
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
