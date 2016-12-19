package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.SoundComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/9/2015.
 * System that is responsible for sound play by entity having #VisSound.
 *
 */

public class SoundEffectSystem extends IteratingSystem {

    ComponentMapper<SoundComponent> soundMapper;

    public SoundEffectSystem() {
        super(Family.all(SoundComponent.class).get());
        soundMapper= ComponentMapper.getFor(SoundComponent.class);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        SoundComponent soundComponent=soundMapper.get(entity);
        //soundComponent.play();
        entity.remove(SoundComponent.class);
    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }

}
