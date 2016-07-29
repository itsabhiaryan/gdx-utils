package com.nayragames.o2d.script;

import com.badlogic.ashley.core.Entity;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.scripts.IScript;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * Created by ARYAN on 30-01-2016.
 */
public class ButtonScript implements IScript {

    private Entity entity;
    private TransformComponent transformComponent;
    private DimensionsComponent dimensionsComponent;


    @Override
    public void init(Entity entity) {

        this.entity=entity;
        transformComponent= ComponentRetriever.get(entity,TransformComponent.class);
        dimensionsComponent= ComponentRetriever.get(entity,DimensionsComponent.class);


    }

    @Override
    public void act(float delta) {





    }

    @Override
    public void dispose() {

    }
}
