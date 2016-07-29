package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kotcrab.vis.runtime.component.Invisible;
import com.kotcrab.vis.runtime.component.Origin;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.system.delegate.DeferredEntityProcessingSystem;
import com.kotcrab.vis.runtime.system.delegate.EntityProcessPrincipal;
import com.kotcrab.vis.runtime.system.render.RenderBatchingSystem;
import com.nayragames.vis.Constants;
import com.nayragames.vis.component.AnimationComponent;

/**
 * This system is used when we want to render any Animation.
 * Entity must have AnimationComponent.
 *
 * Created by ARYAN on 28-12-2015.
 */
public class AnimationRenderSystem extends DeferredEntityProcessingSystem {

    ComponentMapper<AnimationComponent> animMapper;
    ComponentMapper<Transform> basicMapper;
    ComponentMapper<Origin> originMapper;

    private Batch batch;
    private RenderBatchingSystem renderBatchingSystem;
    private TextureRegion textureRegion;

    public AnimationRenderSystem(EntityProcessPrincipal principal) {
        super(Aspect.all(AnimationComponent.class).exclude(Invisible.class), principal);
    }

    @Override
    protected void initialize() {
        batch=renderBatchingSystem.getBatch();
    }

    @Override
    protected void process(int e) {
        Transform transform = basicMapper.get(e);
        AnimationComponent animation = animMapper.get(e);
        Origin origin=originMapper.get(e);

        animation.stateTime += world.getDelta();
        textureRegion = animation.animation.getKeyFrame(animation.stateTime);
        batch.draw(textureRegion, transform.getX(), transform.getY(), origin.getOriginX(),origin.getOriginY(), animation.getWidth(), animation.getHeight(), transform.getScaleX(), transform.getScaleY(), transform.getRotation());
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
