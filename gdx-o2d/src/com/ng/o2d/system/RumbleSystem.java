package com.ng.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.ng.gdxutils._GameManager;
import com.ng.o2d.component.BasicComponent;
import com.ng.o2d.component.RumbleComponent;

import java.util.Random;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 14-12-2015.
 *
 */

public class RumbleSystem extends IteratingSystem {

    ComponentMapper<RumbleComponent> rumbleMapper;
    ComponentMapper<BasicComponent> basicMapper;
    public static Random random=new Random();

    public RumbleSystem(Family.Builder aspect) {
        super(aspect.get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        RumbleComponent rumble=rumbleMapper.get(entity);
        BasicComponent basicComponent=basicMapper.get(entity);

        if (rumble.currentRumbleTime <= rumble.rumbleTime) {
            rumble.currentRumblePower = rumble.rumblePower * ((rumble.rumbleTime - rumble.currentRumbleTime) / rumble.rumbleTime);

            rumble.rumbleX = (random.nextFloat() - 0.5f) * 2 * rumble.currentRumblePower;
            rumble.rumbleY = (random.nextFloat() - 0.5f) * 2 * rumble.currentRumblePower;

            basicComponent.setPosition(-rumble.rumbleX,-rumble.rumbleY);
            rumble.currentRumbleTime += deltaTime;
        } else {

           /* this.cam.position.x = this.hero.getX();
            this.cam.position.y = this.hero.getY();*/
        }
    }

    @Override
    public boolean checkProcessing() {
        return !_GameManager.isPaused();
    }


}
