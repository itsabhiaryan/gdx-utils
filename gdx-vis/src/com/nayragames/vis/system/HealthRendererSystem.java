package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.kotcrab.vis.runtime.component.VisText;
import com.nayragames.vis.Constants;
import com.nayragames.vis.component.BasicComponent;
import com.nayragames.vis.component.HealthComponent;

/**
 * This system update position of TextComponent of Entity to its BasicComponent.
 *
 * Created by ARYAN on 12/9/2015.
 */

public class HealthRendererSystem extends EntityProcessingSystem {

    private static final String TAG = "[" + HealthRendererSystem.class.getSimpleName() + "]";

    ComponentMapper<BasicComponent> bm;
    ComponentMapper<VisText> tm;

    public HealthRendererSystem() {
        super(Aspect.all(HealthComponent.class,BasicComponent.class));
    }

    @Override
    protected void process(Entity e) {
        BasicComponent basic=bm.get(e);
        VisText text=tm.get(e);
        //text.setPosition(basic.getX(),basic.getY()+basic.getHeight());
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
