package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.kotcrab.vis.runtime.component.VisSound;
import com.nayragames.vis.Constants;

/**
 * System that is responsible for sound play by entity having #VisSound.
 *
 * Created by ARYAN on 12/9/2015.
 */

public class SoundEffectSystem extends EntityProcessingSystem {

    ComponentMapper<VisSound> soundMapper;

    public SoundEffectSystem() {
        super(Aspect.all(VisSound.class));
    }

    @Override
    protected void process(Entity e) {

        VisSound soundComponent=soundMapper.get(e);
        soundComponent.play();
        e.edit().remove(VisSound.class);
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
