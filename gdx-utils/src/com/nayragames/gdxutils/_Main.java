package com.nayragames.gdxutils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.nayragames.gdxutils.services.AdServices;
import com.nayragames.gdxutils.services.IServices;
import com.nayragames.gdxutils.services.OrientationServices;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 7/29/2016.
 *
 */
public abstract class _Main extends Game {

    public IServices services;
    public float width,height;
    public _GameManager gameManager;

    public _Main(){

    }

    public abstract _GameManager createManager();

    public _Main(IServices services){
        this();
        this.services=services;
    }

    @Override
    public void create() {
        gameManager=createManager();
        width=Gdx.graphics.getWidth();
        height=Gdx.graphics.getHeight();
    }

    protected void clearScreen () {

        GL20 gl = Gdx.gl;
        gl.glClearColor(0, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void render() {
        super.render();

    }

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
