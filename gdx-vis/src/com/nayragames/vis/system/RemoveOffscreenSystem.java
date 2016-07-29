package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Wire;
import com.artemis.systems.EntityProcessingSystem;
import com.kotcrab.vis.runtime.component.Layer;
import com.nayragames.vis.Constants;
import com.nayragames.vis.Enums;
import com.nayragames.vis.component.BasicComponent;
import com.nayragames.vis.component.CollisionComponent;


/**
 * System that remove entity which is going to offscreen if that entity is in player layer.
 *
 * Created by ARYAN on 09-12-2015.
 */

@Wire
public class RemoveOffscreenSystem extends EntityProcessingSystem {

    ComponentMapper<Layer> lm;
    ComponentMapper<BasicComponent> bm;

    public RemoveOffscreenSystem() {
        super(Aspect.all(CollisionComponent.class,BasicComponent.class));
    }

    @Override
    protected void process(Entity e) {

        BasicComponent basic=bm.get(e);
        Layer layer=lm.get(e);

        if(layer.layerId== Enums.Layer.PLAYER_LAYER.value)
            if(basic.getY()+basic.getHeight()<0||basic.getY()>8||basic.getX()<-10||basic.getX()>14.8f)
                e.deleteFromWorld();

        if(layer.layerId== Enums.Layer.NP_LAYER.value)
            if(basic.getY()<0||basic.getX()<-10||basic.getX()>14.8f)
                e.deleteFromWorld();
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
