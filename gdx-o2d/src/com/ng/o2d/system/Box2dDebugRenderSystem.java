package com.ng.o2d.system;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.SceneLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/25/2016.
 *
 */
public class Box2dDebugRenderSystem extends EntitySystem {
    //private CameraManager cameraManager;
    //private PhysicsSystem physicsSystem;

    private Box2DDebugRenderer debugRenderer;

    Camera camera;
    World world;
    //OrthographicCamera camera;

    public Box2dDebugRenderSystem(SceneLoader sceneLoader, Viewport viewport){
        debugRenderer=new Box2DDebugRenderer();
        camera=viewport.getCamera();
        world=sceneLoader.world;
    }

    //@Override
    //protected void processSystem () {

    //}


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        debugRenderer.render(world, camera.combined);
    }
}
