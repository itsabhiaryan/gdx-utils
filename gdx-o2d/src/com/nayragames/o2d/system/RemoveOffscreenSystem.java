package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.gdxutils._GameManager;
import com.nayragames.o2d.component.BasicComponent;
import com.nayragames.o2d.component.CollisionComponent;
import com.uwsoft.editor.renderer.components.LayerMapComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 09-12-2015.
 *
 * System that remove entity which is going to offscreen if that entity is in player layer.
 *
 */

public class RemoveOffscreenSystem extends IteratingSystem {

    ComponentMapper<LayerMapComponent> lm;
    ComponentMapper<BasicComponent> bm;

    public RemoveOffscreenSystem() {
        super(Family.all(CollisionComponent.class,BasicComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

       /* BasicComponent basic=bm.get(entity);
        LayerMapComponent layer=lm.get(entity);

        if(layer.layerId== Enums.Layer.PLAYER_LAYER.value)
            if(basic.getY()+basic.getHeight()<0||basic.getY()>8||basic.getX()<-10||basic.getX()>14.8f)
                entity.deleteFromWorld();

        if(layer.layerId== Enums.Layer.NP_LAYER.value)
            if(basic.getY()<0||basic.getX()<-10||basic.getX()>14.8f)
                entity.deleteFromWorld();*/
    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }


}
