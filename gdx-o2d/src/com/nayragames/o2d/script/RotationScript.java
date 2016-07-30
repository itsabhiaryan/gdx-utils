package com.nayragames.o2d.script;

import com.badlogic.ashley.core.Entity;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/30/2016.
 *
 */
public class RotationScript implements IScript {

    TransformComponent transformComponent;

    @Override
    public void init(Entity entity) {
        transformComponent=entity.getComponent(TransformComponent.class);
    }

    @Override
    public void act(float delta) {

        transformComponent.rotation++;
    }

    @Override
    public void dispose() {

    }
}
