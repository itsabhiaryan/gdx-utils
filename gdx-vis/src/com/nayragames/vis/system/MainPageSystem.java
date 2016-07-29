package com.nayragames.vis.system;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.kotcrab.vis.runtime.component.VisText;
import com.nayragames.vis.Constants;

/**
 * System that help for MainPage entity deletion when his body sleep.
 *
 * Created by ARYAN on 14-01-2016.
 */
public class MainPageSystem extends BaseSystem {

    PhysicsManager physicsManager;
    ComponentMapper<VisText> visTextMapper;

    @Override
    protected void processSystem() {

        for (int i = 0; i < physicsManager.entities.size(); i++) {

          if(!visTextMapper.has(world.getEntity(physicsManager.entities.get(i))))
            if (!physicsManager.checkAwake(physicsManager.entities.get(i)))
                getWorld().getEntity(physicsManager.entities.get(i)).deleteFromWorld();
        }
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
