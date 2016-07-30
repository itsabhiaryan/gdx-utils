package com.ng.manager;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nayragames.gdxutils._Main;
import com.nayragames.gdxutils.model.TextureManager;
import com.nayragames.o2d.EntityManager;
import com.nayragames.o2d.GenericEntityBuilder;
import com.nayragames.o2d.ResourceLoader;
import com.nayragames.o2d.component.MovementComponent;
import com.nayragames.o2d.manager.BaseSceneManager;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * Created by Personal on 7/30/2016.
 */
public class MainSceneManager extends BaseSceneManager {

    public MainSceneManager(_Main game, SceneLoader sceneLoader) {
        super(game, sceneLoader);

        Entity ent= GenericEntityBuilder.createSprite(sceneLoader, root, "gud",game.width/2,game.height/2,game.width,game.height,0,true);
        ent.add(new MovementComponent(.1f));
        //ent.getComponent(TransformComponent.class).rotation=0;

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        unProjectVec.set(screenX, screenY, 0);
        camera.unproject(unProjectVec);

        float x=unProjectVec.x;
        float y=unProjectVec.y;

        System.out.println(sceneLoader);
        System.out.println(root);
        GenericEntityBuilder.createSprite(sceneLoader,root,"gameplay1",x,y,100,100,0,true);

        return super.touchDown(screenX, screenY, pointer, button);
    }


    @Override
    public boolean scrolled(int amount) {

        ((OrthographicCamera)camera).zoom+=amount;
        camera.update();

        return super.scrolled(amount);
    }
}
