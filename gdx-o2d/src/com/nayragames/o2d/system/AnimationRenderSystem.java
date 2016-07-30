package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nayragames.o2d.component.AnimationComponent;
import com.uwsoft.editor.renderer.components.TextureRegionComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-12-2015.
 *
 * This system is used when we want to render any Animation.
 * Entity must have AnimationComponent.
 */
public class AnimationRenderSystem extends IteratingSystem {

    ComponentMapper<AnimationComponent> animMapper;
    ComponentMapper<TransformComponent> basicMapper;
    ComponentMapper<TextureRegionComponent> textureMapper;

    private Batch batch;
    private TextureRegion textureRegion;

    public AnimationRenderSystem() {
        super(Family.all(AnimationComponent.class).get());

        animMapper= ComponentMapper.getFor(AnimationComponent.class);
        basicMapper= ComponentMapper.getFor(TransformComponent.class);
        textureMapper= ComponentMapper.getFor(TextureRegionComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        TransformComponent transform = basicMapper.get(entity);
        AnimationComponent animation = animMapper.get(entity);
        TextureRegionComponent texture=textureMapper.get(entity);

        animation.stateTime += deltaTime;
        texture.region = animation.animation.getKeyFrame(animation.stateTime);
        //batch.draw(textureRegion, transform.getX(), transform.getY(), origin.getOriginX(),origin.getOriginY(), animation.getWidth(), animation.getHeight(), transform.getScaleX(), transform.getScaleY(), transform.getRotation());
    }
}
