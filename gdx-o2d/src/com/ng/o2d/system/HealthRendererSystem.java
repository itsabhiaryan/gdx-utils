package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.BasicComponent;
import com.ng.o2d.component.HealthComponent;
import com.uwsoft.editor.renderer.components.label.LabelComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 * This system update position of TextComponent of Entity to its BasicComponent.
 *
 */

public class HealthRendererSystem extends IteratingSystem {

    private static final String TAG = "[" + HealthRendererSystem.class.getSimpleName() + "]";

    ComponentMapper<BasicComponent> bm;
    ComponentMapper<LabelComponent> tm;

    public HealthRendererSystem() {
        super(Family.all(HealthComponent.class,BasicComponent.class).get());
        bm= ComponentMapper.getFor(BasicComponent.class);
        tm= ComponentMapper.getFor(LabelComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        BasicComponent basic=bm.get(entity);
        LabelComponent text=tm.get(entity);
        //text.setPosition(basic.getX(),basic.getY()+basic.getHeight());
    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }
}
