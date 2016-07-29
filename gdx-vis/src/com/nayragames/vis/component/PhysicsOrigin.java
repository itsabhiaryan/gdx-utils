package com.nayragames.vis.component;

import com.artemis.Component;

/**
 * Created by ARYAN on 12/30/2015.
 */
public class PhysicsOrigin extends Component {

    public float originX, originY;

    public PhysicsOrigin(){

    }

    public PhysicsOrigin(float originX,float originY){
        this.originX=originX;
        this.originY =originY;
    }

}
