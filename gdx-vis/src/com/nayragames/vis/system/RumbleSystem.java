package com.nayragames.vis.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.nayragames.vis.Constants;
import com.nayragames.vis.component.BasicComponent;
import com.nayragames.vis.component.RumbleComponent;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.Random;

/**
 * Created by ARYAN on 14-12-2015.
 */

public class RumbleSystem extends EntityProcessingSystem {

    ComponentMapper<RumbleComponent> rumbleMapper;
    ComponentMapper<BasicComponent> basicMapper;
    public static Random random=new Random();

    public RumbleSystem(Aspect.Builder aspect) {
        super(aspect);
    }

    @Override
    protected void process(Entity e) {

        RumbleComponent rumble=rumbleMapper.get(e);
        BasicComponent basicComponent=basicMapper.get(e);

        if (rumble.currentRumbleTime <= rumble.rumbleTime) {
            rumble.currentRumblePower = rumble.rumblePower * ((rumble.rumbleTime - rumble.currentRumbleTime) / rumble.rumbleTime);

            rumble.rumbleX = (random.nextFloat() - 0.5f) * 2 * rumble.currentRumblePower;
            rumble.rumbleY = (random.nextFloat() - 0.5f) * 2 * rumble.currentRumblePower;

            basicComponent.setPosition(-rumble.rumbleX,-rumble.rumbleY);
            rumble.currentRumbleTime += world.getDelta();
        } else {

           /* this.cam.position.x = this.hero.getX();
            this.cam.position.y = this.hero.getY();*/
        }
    }

    @Override
    protected boolean checkProcessing() {
        return !Constants.isPaused();
    }
}
