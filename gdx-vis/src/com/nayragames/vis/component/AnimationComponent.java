package com.nayragames.vis.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


/**
 * Created by ARYAN on 26-11-2015.
 */
public class AnimationComponent extends Component {

    public Animation animation;
    public float stateTime;
    private float width,height;

    public AnimationComponent(TextureRegion[] paramArrayOfTextureRegion, float frameDuration, Animation.PlayMode playMode){

        animation=new Animation(frameDuration,paramArrayOfTextureRegion);
        animation.setPlayMode(playMode);
        stateTime= 0;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setSize(float width,float height){
        this.width=width;
        this.height=height;
    }
}
