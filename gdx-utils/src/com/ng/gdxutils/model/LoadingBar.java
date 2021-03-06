package com.ng.gdxutils.model;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class LoadingBar extends Actor {

    private Animation<TextureRegion> animation;
    private TextureRegion reg;
    private float stateTime;

    public LoadingBar(Animation<TextureRegion> animation) {
        this.animation = animation;
        reg = animation.getKeyFrame(0);
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        reg = animation.getKeyFrame(stateTime);    
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(reg, getX(), getY(),getWidth(),getHeight());
    }
}
