package com.nayragames.o2d.component;


import com.badlogic.ashley.core.Component;

/**
 * Created by ARYAN on 12/30/2015.
 */
public class PhysicsOrigin implements Component {

    public float originX, originY;

    public PhysicsOrigin(){

    }

    public PhysicsOrigin(float originX, float originY){
        this.originX=originX;
        this.originY =originY;
    }

}
