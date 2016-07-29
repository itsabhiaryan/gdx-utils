package com.nayragames.gdxutils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

/**
 * Created by Personal on 14-01-2016.
 */
public class UIFactory {


    public static ScrollPane getScrollPane(Group group, float width, float height, float x, float y){

        ScrollPane scrollPane = new ScrollPane(group);
        scrollPane.setSize(width, height);
        scrollPane.setPosition(x, y);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setSmoothScrolling(true);
        scrollPane.setTransform(true);
        scrollPane.setClamp(true);
        scrollPane.setOverscroll(false, false);

        return scrollPane;
    }

    public static ImageButton getImageButton(Group group, TextureRegion texture, float width, float height, float x, float y, EventListener listener){

        Sprite sprite1 = new Sprite(texture);
        ImageButton imageButton = new ImageButton(new SpriteDrawable(sprite1));
        imageButton.setSize(width,height);
        imageButton.setPosition(x, y);
        imageButton.addListener(listener);
        group.addActor(imageButton);

        return imageButton;
    }

}
