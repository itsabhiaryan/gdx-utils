package com.ng.manager;

import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.ng.gdxutils._Main;
import com.ng.gdxutils.model.Position;
import com.ng.gdxutils.model.Size;
import com.ng.vis.manager.BaseSceneManager;
import com.ng.vis.physics.PhysicsFactory;
//import PhysicsFactory;


/**
 * Created by aryan9234 on 31-07-2016.
 */

public class HomeSceneManager extends BaseSceneManager {

    public HomeSceneManager(_Main game) {
        super(game);
    }

    @Override
    public void afterSceneInit() {
        super.afterSceneInit();

      //  EntityFactory.createShape(world,0, Color.BLUE, Size.makeSize(100,100), Position.makePosition(50,50),0);
  //      PhysicsFactory.createPhysicsShape(world,0,Color.BLUE,Size.makeSize(1,1),Position.makePosition(3,3),0, BodyDef.BodyType.DynamicBody,1);
        PhysicsFactory.createPhysicsBoundary(world,4.8f,8);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        unProjectVec.set(screenX, screenY, 0);
        cameraManager.getCamera().unproject(unProjectVec);

        float x=unProjectVec.x;
        float y=unProjectVec.y;

        PhysicsFactory.createPhysicsShape(world,0,Color.BLUE,Size.makeSize(.5f,.5f),Position.makePosition(x,y),0, BodyDef.BodyType.DynamicBody,1);

      //  EntityFactory.createShape(world,0, Color.BLUE, Size.makeSize(1,1), Position.makePosition(x,y,true),0);

        return super.touchDown(screenX, screenY, pointer, button);
    }
}
