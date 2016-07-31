package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kotcrab.vis.runtime.component.Invisible;
import com.kotcrab.vis.runtime.component.Origin;
import com.kotcrab.vis.runtime.component.Tint;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.system.CameraManager;
import com.kotcrab.vis.runtime.system.delegate.DeferredEntityProcessingSystem;
import com.kotcrab.vis.runtime.system.delegate.EntityProcessPrincipal;
import com.kotcrab.vis.runtime.system.render.RenderBatchingSystem;
import com.kotcrab.vis.runtime.util.AfterSceneInit;
import com.nayragames.gdxutils._GameManager;
import com.nayragames.vis.component.ShapeComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-12-2015.
 *
 * System that is responsible for rendering shape.
 * Entity must required have ShapeComponent.
 *
 */

public class ShapeRendererSystem extends DeferredEntityProcessingSystem implements AfterSceneInit {

    private ComponentMapper<ShapeComponent> shapeCm;
    private ComponentMapper<Transform> transformCm;
    private ComponentMapper<Origin> originCm;
    private ComponentMapper<Tint> tintCm;
    public static ShapeRenderer shapeRenderer;

    private RenderBatchingSystem renderBatchingSystem;
    CameraManager cameraManager;
    private Batch batch;
    public static boolean isRendererAttached;

    public ShapeRendererSystem(EntityProcessPrincipal principal) {
        super(Aspect.all(ShapeComponent.class).exclude(Invisible.class), principal);
        isRendererAttached=true;
    }

    @Override
    protected void initialize() {
        batch=renderBatchingSystem.getBatch();
    }

    @Override
    public void afterSceneInit() {
        shapeRenderer=new ShapeRenderer();
    }

    @Override
    protected void process(int e) {

        Transform transform = transformCm.get(e);
        ShapeComponent shape = shapeCm.get(e);
        Origin origin=originCm.get(e);
        Color color=tintCm.get(e).getTint();

        batch.end();

        shapeRenderer.setProjectionMatrix(cameraManager.getCombined());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        if(shape.getShape().ordinal()==ShapeComponent.Shape.RECTANGLE.ordinal())
            shapeRenderer.rect(transform.getX(), transform.getY(),origin.getOriginX(),origin.getOriginY(), shape.getWidth(), shape.getHeight(),transform.getScaleX(),transform.getScaleY(),transform.getRotation());
        else if(shape.getShape().ordinal()==ShapeComponent.Shape.CIRCLE.ordinal())
               shapeRenderer.circle(transform.getX()+origin.getOriginX(),transform.getY()+origin.getOriginY(),shape.getRadius(),100);

        shapeRenderer.end();
        batch.begin();
    }

    @Override
    protected void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    protected boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
