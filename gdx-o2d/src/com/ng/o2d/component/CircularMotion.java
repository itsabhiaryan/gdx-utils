package com.ng.o2d.component;


import com.badlogic.ashley.core.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 04-01-2016.
 */

public class CircularMotion implements Component {

    public enum MotionType{
        CLOCKWISE,ANTICLOCKWISE;
    }

    public float centerX;
    public float centerY;
    public float radius;
    public float speed;
    public float angle;
    public float originX;
    public float originY;
    public MotionType motionType= MotionType.CLOCKWISE;

    public CircularMotion(float centerX, float centerY, float radius){
        this(centerX,centerY,radius,1, MotionType.CLOCKWISE);
    }

    public CircularMotion(float centerX, float centerY, float radius, float speed, MotionType motionType){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
        this.speed=speed;
        this.motionType=motionType;
    }
}