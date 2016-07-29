package com.nayragames.gdxutils.actor;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 7/25/2016.
 */
public class LabelActor {

    Label localLabel;

    public LabelActor(Group group, String text, Label.LabelStyle labelStyle, float width, float height){

        localLabel=new Label(text, labelStyle);
        localLabel.setAlignment(Align.center);

        Container<Label> container=new Container<Label>(localLabel);
        container.setTransform(true);
        container.size(width, height);
        container.setOrigin(container.getWidth() / 2, container.getHeight() / 2);
        container.setPosition(width,height);
        container.setTouchable(Touchable.disabled);
        group.addActor(container);

    }

    public static Label makeLabelActor(Group group, String text, Label.LabelStyle labelStyle, float width, float height){

        LabelActor labelActor= new LabelActor(group,text,labelStyle,width,height);
        return labelActor.localLabel;

    }

}
