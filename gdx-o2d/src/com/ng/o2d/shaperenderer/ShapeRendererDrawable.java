package com.ng.o2d.shaperenderer;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.ng.o2d.component.ShapeComponent;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TintComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.systems.render.logic.Drawable;

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

public class ShapeRendererDrawable implements Drawable {

    private ComponentMapper<ShapeComponent> shapeCm;
    private ComponentMapper<TransformComponent> transformCm;
    private ComponentMapper<TintComponent> tintCm;
    private ComponentMapper<DimensionsComponent> dimensionCm;
    private ShapeRenderer shapeRenderer;
    Camera camera;
    Viewport viewPort;

    public ShapeRendererDrawable(Viewport viewPort) {
        this.viewPort=viewPort;
        this.camera=viewPort.getCamera();

        shapeCm=ComponentMapper.getFor(ShapeComponent.class);

        shapeRenderer=new ShapeRenderer();
        transformCm=ComponentMapper.getFor(TransformComponent.class);
        tintCm=ComponentMapper.getFor(TintComponent.class);
        dimensionCm=ComponentMapper.getFor(DimensionsComponent.class);
    }

    @Override
    public void draw(Batch batch, Entity entity, float parentAlpha) {

        TransformComponent transform = transformCm.get(entity);
        ShapeComponent shape = shapeCm.get(entity);
        DimensionsComponent dimension =dimensionCm.get(entity);
        Color color=tintCm.get(entity).color;

        batch.end();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(color);
        if(shape.getShape()== ShapeComponent.Shape.RECTANGLE)
            shapeRenderer.rect(transform.x, transform.y,transform.originX,transform.originY, dimension.width, dimension.height,transform.scaleX,transform.scaleY,transform.rotation);
        else if(shape.getShape()== ShapeComponent.Shape.CIRCLE)
            shapeRenderer.circle(transform.x,transform.y,shape.getRadius(),100);

        shapeRenderer.end();
        batch.begin();
    }
}
