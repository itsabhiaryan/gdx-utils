package com.nayragames.gdxutils.ai.formation;

import com.badlogic.gdx.ai.fma.FormationPattern;
import com.badlogic.gdx.ai.utils.Location;
import com.badlogic.gdx.math.Vector2;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since  14-12-2015.
 *
 */
public class DragonFormationPattern implements FormationPattern<Vector2> {

    private int numberOfSlots;
    private float memberRadius;
    private float center;

    public DragonFormationPattern(float memberRadius,float center){
        this.memberRadius=memberRadius;
        this.center=center;
    }

    public DragonFormationPattern(float memberRadius){
        this.memberRadius=memberRadius;
    }

    @Override
    public void setNumberOfSlots(int numberOfSlots) {
        this.numberOfSlots=numberOfSlots;
    }

    @Override
    public Location<Vector2> calculateSlotLocation(Location<Vector2> outLocation, int slotNumber) {

        if(center==0)
            outLocation.getPosition().set(memberRadius*2.55f*slotNumber-memberRadius*1.8f,0);
        else
            outLocation.getPosition().set(memberRadius*1.85f*slotNumber-memberRadius*1.5f,0);

        /*if(slotNumber==0)
            outLocation.getPosition().set(0,0);
        else
        outLocation.getPosition().set(memberRadius*2.55f*slotNumber-memberRadius*1.8f,0);*/

        return null;
    }

    @Override
    public boolean supportsSlots(int slotCount) {
        return true;
    }
}
