package com.ng.gdxutils.ai.formation;

import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/13/2015.
 *
 */
public class PlayerChildFormationPattern implements FormationPattern<Vector2> {

    private int numberOfSlots;
    private float memberRadius;

    public PlayerChildFormationPattern(float memberRadius){
        this.memberRadius=memberRadius;
    }

    @Override
    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots=numberOfSlots;
    }

    @Override
    public Location<Vector2> calculateSlotLocation(Location<Vector2> outLocation, int slotNumber) {
        outLocation.getPosition().set(-memberRadius/2+slotNumber*memberRadius*2,0);
        return null;
    }

    @Override
    public boolean supportsSlots(int slotCount) {
        return true;
    }
}
