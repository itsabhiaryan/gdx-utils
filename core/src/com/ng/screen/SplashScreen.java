package com.ng.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nayragames.gdxutils._AbstractScreen;
import com.nayragames.gdxutils._Main;
import com.nayragames.gdxutils.b2d.PhysicsHelper;
import com.nayragames.gdxutils.model.TextureManager;

/**
 * Created by aryan9234 on 29-07-2016.
 */
public class SplashScreen extends _AbstractScreen {


    SpriteBatch batch;
    Sprite sprite;

    public SplashScreen(_Main game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();

        batch=new SpriteBatch();
        sprite=new Sprite(TextureManager.getPixmapTexture(Color.BLUE));
        sprite.setSize(100,100);
        sprite.setPosition(100,100);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        batch.end();

        //PhysicsHelper.update();


    }
}
