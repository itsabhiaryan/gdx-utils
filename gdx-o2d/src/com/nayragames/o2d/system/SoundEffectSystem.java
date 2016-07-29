package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.component.SoundComponent;

/**
 * System that is responsible for sound play by entity having #VisSound.
 *
 * Created by ARYAN on 12/9/2015.
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
        return !Constants.isPaused();
    }

}
