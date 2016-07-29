package com.nayragames.gdxutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

/**
 * Parent Abstract screen for all screen class.
 *
 * Created by ARYAN on 11/30/2015.
 */

public abstract class _AbstractScreen implements Screen {

    private static final String TAG = "[" + _AbstractScreen.class.getSimpleName() + "]";

    protected _Main game;

    /**
     * Is scene of ECS is enabled in implemented screen class.
     */
    protected boolean isSceneEnabled;

    /**
    * Is tween is Enabled in implemented screen class and scene of ECS too.
    */
    protected boolean isTweenEnabled;


    protected boolean isScene2dEnabled;

    public _AbstractScreen(_Main game) {
        this.game = game;
    }

    public void show(){
        isSceneEnabled=false;
        isTweenEnabled=false;
        isScene2dEnabled =false;
        //showAd(false,false);
        Gdx.app.log(TAG,"show");
    }

    @Override
    public void render(float delta) {
     //   if(isTweenEnabled&& !GameManager.isPaused()) game.tweenManager.update(delta);

        GL20 gl = Gdx.gl;
        gl.glClearColor(1, 0, 0, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL20.GL_BLEND);
        gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

      //  if(isSceneEnabled) game.sceneManager.sceneLoader.getEngine().update(Gdx.graphics.getDeltaTime());

        //Gdx.app.log(TAG,"render");
    }

    @Override
    public void resize(int width, int height) {
        //if(isSceneEnabled) SceneManager.scene.resize(width,height);
        Gdx.app.log(TAG,"resize");
        //SceneManager.sceneLoader.getRoot().getComponent(ViewPortComponent.class).viewPort.update(width,height);
        //SceneManager.sceneLoader.getRoot().getComponent(ViewPortComponent.class).viewPort.apply();
    }

    @Override
    public void pause() {

        Gdx.app.log(TAG,"pause");
      //  GameManager.pause(true);
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG,"resume");
       // GameManager.resume();
    }

    @Override
    public void dispose() {
        Gdx.app.log(TAG,"dispose");
    }

    public void showAd(boolean isTop,boolean isBottom){

        game.getAdService().showTopAd(isTop);
        game.getAdService().showBottomAd(isBottom);
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG,"hide");
    }
}
