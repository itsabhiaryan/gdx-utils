package com.ng.gdxutils.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import com.ng.gdxutils.model.Position;
import com.ng.gdxutils.model.Size;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 6/8/2015.
 *
 */
public class AnimationActor extends Actor {

    public Animation animation;
    public TextureRegion reg;
    public float stateTime;

    public float angle = 0.0F;
    //public Cube cube = null;
    float frameDuration;
    public TextureRegion[] keyFrames;
    float onceAllTime;
    public Position position;
    float scale = 1.0F;
    float scaleX = 1.0F;
    float scaleY = 1.0F;
    boolean isLighting;

    public AnimationActor(Group paramGroup, TextureRegion[] paramArrayOfTextureRegion, float frameDuration, Animation.PlayMode playMode, float scaleX, float scaleY, float angle) {

        this.keyFrames = paramArrayOfTextureRegion;
        this.frameDuration = frameDuration;
        this.onceAllTime = (frameDuration * paramArrayOfTextureRegion.length);
        TextureRegion localTextureRegion = paramArrayOfTextureRegion[0];
        Size localGSize = Size.makeSize(localTextureRegion.getRegionWidth(), localTextureRegion.getRegionHeight());
        setSize(localGSize.x, localGSize.y);
        this.animation = new Animation(frameDuration, paramArrayOfTextureRegion);
        this.animation.setPlayMode(playMode);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
        paramGroup.addActor(this);
        this.angle = angle;
    }

    public AnimationActor(Group paramGroup, TextureRegion[] paramArrayOfTextureRegion, float frameDuration, Animation.PlayMode playMode, float scaleX, float scaleY, float angle, boolean isLighting) {

        this.isLighting = isLighting;
        this.keyFrames = paramArrayOfTextureRegion;
        this.frameDuration = frameDuration;
        this.onceAllTime = (frameDuration * paramArrayOfTextureRegion.length);
        TextureRegion localTextureRegion = paramArrayOfTextureRegion[0];
        Size localGSize =Size.makeSize(localTextureRegion.getRegionWidth(), localTextureRegion.getRegionHeight());
        setSize(localGSize.x, localGSize.y);
        this.animation = new Animation(frameDuration, paramArrayOfTextureRegion);
        this.animation.setPlayMode(playMode);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
        paramGroup.addActor(this);
        this.angle = angle;
        setRotation(angle);
        setScaleX(scaleX);
        setScaleY(scaleY);
    }

    public AnimationActor(Group paramGroup, TextureRegion[] paramArrayOfTextureRegion, float frameDuration, Animation.PlayMode playMode, float scale, float angle) {

        this.keyFrames = paramArrayOfTextureRegion;
        this.frameDuration = frameDuration;
        this.onceAllTime = (frameDuration * paramArrayOfTextureRegion.length);
        TextureRegion localTextureRegion = paramArrayOfTextureRegion[0];
        Size localGSize = Size.makeSize(localTextureRegion.getRegionWidth(), localTextureRegion.getRegionHeight());
        setSize(localGSize.x, localGSize.y);
        this.animation = new Animation(frameDuration, paramArrayOfTextureRegion);
        this.animation.setPlayMode(playMode);
        this.scaleX = scale;
        this.scaleY = scale;
        setScale(scale,scale);
        setRotation(angle);
        setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
        paramGroup.addActor(this);
        this.angle = angle;
    }

    public AnimationActor(TextureRegion[] frames_num1, float frameDuration, float scale, Animation.PlayMode playMode) {

        animation=new Animation(frameDuration,frames_num1);
        animation.setPlayMode(playMode);
        this.scaleX=scale;
        this.scaleY=scale;

        TextureRegion localTextureRegion = frames_num1[0];
        Size localSize = Size.makeSize(localTextureRegion.getRegionWidth(), localTextureRegion.getRegionHeight());
        setSize(localSize.x, localSize.y);
        setOrigin(getWidth() / 2.0F, getHeight() / 2.0F);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        stateTime += delta;
        reg = animation.getKeyFrame(stateTime);
        if (animation.getPlayMode()== Animation.PlayMode.NORMAL&& animation.isAnimationFinished(stateTime))
            end();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

      /*  if(isLighting)
            batch.draw(reg, getX(), getY(), getWidth()/2f, getHeight()/2f, getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        else
            batch.draw(reg, getX(), getY(), this.scaleX*reg.getRegionWidth() / 2, this.scaleY*reg.getRegionHeight() / 2, reg.getRegionWidth() * this.scaleX, reg.getRegionHeight() * this.scaleY, 1.0F, 1.0F, this.angle);
*/


        batch.draw(reg,getX(),getY(),getWidth()/2,getHeight()/2,getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());

    }

    public void setPosition(Position origin) {
        this.setOrigin(origin.x, origin.y);
        this.setPosition(getOriginX()-getWidth()/2f,getOriginY()-getHeight()/2f);
    }

    public void end() {
        if (getParent() != null)
            remove();
    }

}
