package com.ng.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ng.gdxutils._AbstractScreen;
import com.ng.gdxutils._Main;
import com.ng.gdxutils.model.TextureManager;

/**
 * Created by aryan9234 on 29-07-2016.
 */
public class SplashScreen extends _AbstractScreen {

    SpriteBatch batch;
    Sprite sprite;
//    SceneManager sceneManager;

    public SplashScreen(_Main game) {
        super(game);
//        sceneManager=((Main)game).sM;
    }

    @Override
    public void show() {
        super.show();

/*
        sceneManager.loadScene("menu").addSystems(
                     new MainSceneManager(game,sceneManager.sceneLoader),
                     new BoundsUpdater(),new CircularMotionSystem(),new MovementSystem());
*/

        batch=new SpriteBatch();
        sprite=new Sprite(TextureManager.getPixmapTexture(Color.BLUE));
        sprite.setSize(100,100);
        sprite.setPosition(100,100);
    }

   @Override
    public void render(float delta) {
        super.render(delta);

       /*Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();*/

        //PhysicsHelper.update();
    }
}
