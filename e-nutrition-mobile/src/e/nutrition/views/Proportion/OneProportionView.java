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

package e.nutrition.views.Proportion;

import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.demos.grub.Util;
import com.codename1.demos.grub.interfaces.Dish;
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
import com.codename1.ui.layouts.BorderLayout;

import static com.codename1.ui.ComponentSelector.$;
import com.codename1.ui.URLImage;
import static com.codename1.ui.util.Resources.getGlobalResources;
import e.nutrition.entities.Proportion;
import e.nutrition.services.ServiceProportion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class OneProportionView<T extends Entity> extends AbstractEntityView<T> {

    private Node viewNode;
    DateFormat dateFormat = new SimpleDateFormat("Ehh:mm");
    String url;

    Proportion p;

    public static final ActionNode.Category DISH_CLICKED = new ActionNode.Category();

    private static EncodedImage placeHolder = EncodedImage.createFromImage(getGlobalResources().getImage("placeholder.png"), false);

    public OneProportionView(T entity, Node viewNode) {
        super(entity);
        p= (Proportion) entity;
        setUIID("DishPreview");
        setLayout(new BorderLayout());

        this.viewNode = viewNode;
        createform();
      
    }
    
    public void  createform()
    {
          EncodedImage restImage = placeHolder;

        if (p.getAliment().getTags().size() != 0)
            {
                 url=p.getAliment().getTags().get(0).getUrl();
                 restImage = URLImage.createToStorage(restImage, url , url,URLImage.RESIZE_SCALE);
            }
    
        Image scaledImage = restImage.scaled(150,150);
        ScaleImageLabel dishImageLabel = new ScaleImageLabel(scaledImage);

        Button lead = new Button();
        lead.setVisible(false);
        add(BorderLayout.SOUTH, lead);
        setLeadComponent(lead);

        MultiButton aliment = new MultiButton(p.getAliment().getNom());
        aliment.setTextLine2(dateFormat.format(p.getDateConsommation()));
        aliment.setTextLine3(String.valueOf(Math.round(p.getPoid()))+" grame");

        aliment.setTextLine4(String.valueOf(Math.round(p.getCalorie()))+"Kcal");
        aliment.setUIID("DishPreviewInfo");
        aliment.setUIIDLine1("DishPreviewName");
        aliment.setUIIDLine2("DishPreviewDescription");
        aliment.setUIIDLine3("DishPreviewDescription");

        aliment.setUIIDLine4("DishPreviewPrice");

        add(BorderLayout.NORTH, dishImageLabel);
        add(BorderLayout.CENTER, aliment);

        $(lead, aliment).addActionListener(evt -> {
            evt.consume();
        //    ActionNode action = viewNode.getInheritedAction(DISH_CLICKED);

        });
        
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
