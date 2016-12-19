package com.ng.vis.system;

import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.kotcrab.vis.runtime.component.VisText;
import com.ng.gdxutils._GameManager;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 14-01-2016.
 *
 * System that help for MainPage entity deletion when his body sleep.
 *
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
        return !_GameManager.isPaused();
    }
}
