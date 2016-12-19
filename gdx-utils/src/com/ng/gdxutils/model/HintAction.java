package com.ng.gdxutils.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 5/14/2016.
 *
 */
public class HintAction extends TemporalAction {

    private float startX, startY;
    float hitX,hitY,firstX,firstY,secondX,secondY;

    @Override
    protected void begin() {
            startX=actor.getX();
            startY=actor.getY();

        //com.badlogic.gdx.scenes.scene2d.actions.Actions
    }

    @Override
    protected void update(float percent) {

       // target.setPosition(startX + (endX - startX) * percent, startY + (endY - startY) * percent, alignment);
        //float totalDistance=(hitX-firstX)*(hitX-firstX)+()

        Gdx.app.log("TAG","Value of update"+percent);
        target.setPosition(startX+(firstX-startX)*percent,startY+(firstX-startY)*percent);
    }

    @Override
    public void reset () {
        super.reset();
        firstX=0;
        firstY=0;
        hitX=0;
        hitY=0;
        secondX=0;
        secondY=0;
    }

    public void setPoint(float hitX,float hitY,float firstX,float firstY,float secondX,float secondY){
        this.hitX=hitX;
        this.hitY=hitY;
        this.firstX=firstX;
        this.firstY=firstY;
        this.secondX=secondX;
        this.secondY=secondY;
    }

    public static HintAction obtain(float originX,float originY,float firstX,float firstY,float secondX,float secondY,float duration){

        Pool<HintAction> pool= Pools.get(HintAction.class);
        HintAction action=pool.obtain();
        action.setDuration(duration);
        action.setPoint(originX,originY,firstX,firstY,secondX,secondY);
        action.setPool(pool);
        return action;
    }

}
