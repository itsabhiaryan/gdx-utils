package com.nayragames.gdxutils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.nayragames.gdxutils.services.AdServices;
import com.nayragames.gdxutils.services.IServices;
import com.nayragames.gdxutils.services.OrientationServices;

/**
 * Created by Personal on 7/29/2016.
 */
public abstract class _Main extends Game {

    public IServices services;
    public float width,height;
    public _GameManager gameManager;

    public _Main(){


    }

    public _Main(IServices services){
        this();
        this.services=services;
    }

    @Override
    public void create() {
        width=Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight();
    }

    /*@Override
    public void render() {
        super.render();

        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );


    }*/

    public AdServices getAdService(){
        return (AdServices)services;
    }

    public OrientationServices getOrientationService(){
        return (OrientationServices)services;
    }

    public void setOrientation(OrientationServices.Orientation orientation){
        ((OrientationServices)services).setOrientation(orientation);
    }

}
