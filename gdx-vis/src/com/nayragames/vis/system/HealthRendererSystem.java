package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.kotcrab.vis.runtime.component.VisText;
import com.nayragames.gdxutils._GameManager;
import com.nayragames.vis.component.BasicComponent;
import com.nayragames.vis.component.HealthComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 *
 * This system update position of TextComponent of Entity to its BasicComponent.
 *
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
        return !_GameManager.isPaused();
    }
}
