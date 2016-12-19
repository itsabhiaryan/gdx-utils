package com.ng.o2d.shaperenderer;

import box2dLight.RayHandler;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.uwsoft.editor.renderer.commons.IExternalItemType;
import com.uwsoft.editor.renderer.factory.component.ComponentFactory;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.systems.render.logic.Drawable;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 27-01-2016.
 *
 */
public class ShapeRendererType implements IExternalItemType {

    public static final int SHAPE_RENDERER = 20;
    Viewport viewport;
    RayHandler rayHandler;
    World world;
    IResourceRetriever rm;

    public ShapeRendererType (Viewport viewport){
        this.viewport=viewport;
    }

    @Override
    public int getTypeId() {
        return SHAPE_RENDERER;
    }

    @Override
    public Drawable getDrawable() {
        return new ShapeRendererDrawable(viewport);
    }

    @Override
    public IteratingSystem getSystem() {
        return new ShapeRendererSystem();
    }

    @Override
    public ComponentFactory getComponentFactory() {
        return new ShapeComponentFactory(rayHandler,world,rm);
    }

    @Override
    public void injectMappers() {

    }

    @Override
    public void injectDependencies(RayHandler rayHandler, World world, IResourceRetriever rm) {
        this.rayHandler=rayHandler;
        this.world=world;
        this.rm=rm;
    }
}
