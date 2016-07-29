package com.nayragames.o2d.component;


import com.badlogic.ashley.core.Component;

/**
 * Created by ARYAN on 12/9/2015.
 */

public class ExpireComponent implements Component {

    public enum ExpireType {
        MAGNET,SHIELD,ANIMATION,PARTICLE,OTHER
    }

    public float delay;
    public ExpireType expireType = ExpireType.OTHER;

    public ExpireComponent(){
        this(1, ExpireType.OTHER);
    }

    public ExpireComponent(float delay, ExpireType type){
        this.delay=delay;
        this.expireType =type;
    }
}
