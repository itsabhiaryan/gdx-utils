package com.ng.vis.component;

import com.artemis.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/30/2015.
 *
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
