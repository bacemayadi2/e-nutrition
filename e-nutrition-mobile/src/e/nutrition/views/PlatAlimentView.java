

package e.nutrition.views;

import com.codename1.components.ScaleImageLabel;
import com.codename1.demos.grub.Util;
import com.codename1.demos.grub.interfaces.DishAddOn;
import com.codename1.demos.grub.views.DishAddOnView;
import static com.codename1.demos.grub.views.DishAddOnView.ADD_ON_CLICKED;
import com.codename1.rad.models.Entity;
import com.codename1.rad.models.Property;
import com.codename1.rad.models.PropertySelector;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.Node;
import com.codename1.rad.ui.AbstractEntityView;
import com.codename1.rad.ui.image.RoundRectImageRenderer;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;

import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Composition;

public class PlatAlimentView extends AbstractEntityView {

    public static final ActionNode.Category ADD_ON_CLICKED = new ActionNode.Category();

    private Node viewNode;
    private String nom, poid,urlimage ;
        boolean  isSelected;

    Button nombutton;
    Label poidLabel;
    Composition c ;
    private static EncodedImage placeHolder = EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png"), false);

    public PlatAlimentView(Entity entity, Node node,PlatDetailView platDetailview) {
        super(entity);
        viewNode = node;
        this.setUIID("DishAddOn");
        setLayout(new BorderLayout());
         c = (Composition) entity;
        nom =c.getAliment().getNom();
        isSelected=c.getAliment().isSelected();
  
        poid = String.valueOf(Math.round(c.getPoid()));
        EncodedImage restImage = placeHolder;


    //   RoundRectImageRenderer renderer = new RoundRectImageRenderer(200, 200, 2);

             if (c.getAliment().getTags().size() != 0)
            {
                 urlimage=c.getAliment().getTags().get(0).getUrl();
                 restImage = URLImage.createToStorage(restImage, urlimage , urlimage,URLImage.RESIZE_SCALE);
            }        
                   //  Image addOnImage = renderer.createImage(imagePropertySelector);
            EncodedImage scaledImage = restImage.scaledEncoded(200, 200);
             ScaleImageLabel addOnImageLabel = new ScaleImageLabel(scaledImage);

        nombutton = new Button(nom, "DishAddOnName");
        poidLabel = new Label(poid + "grame" , "DishAddOnPrice");
              nombutton.addActionListener(evt->{
            evt.consume();
   
             c.getAliment().setSelected(!c.getAliment().isSelected());
             update();
            platDetailview.update();

            
        });
 
        setLeadComponent(nombutton);

        add(BorderLayout.NORTH, addOnImageLabel);
        add(BorderLayout.CENTER, nombutton);
        add(BorderLayout.SOUTH, poidLabel);

        update();
    }

    @Override
    public void update() {
        if (isSelected){
            setUIID("DishAddOnPressed");
            nombutton.setUIID("DishAddOnNamePressed");
            poidLabel.setUIID("DishAddOnPricePressed");
        }else{
            setUIID("DishAddOn");
            nombutton.setUIID("DishAddOnName");
            poidLabel.setUIID("DishAddOnPrice");
        }

        revalidateWithAnimationSafety();

    }

    @Override
    public void commit() {

    }

    @Override
    public Node getViewNode() {
        return null;
    }


}
