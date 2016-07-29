package com.nayragames.o2d.component;


import com.badlogic.ashley.core.Component;

/**
 * Created by ARYAN on 12/28/2015.
 */
public class ShapeComponent implements Component {

    public enum Shape{
        RECTANGLE,CIRCLE
    }

    private Shape shape= Shape.RECTANGLE;
    private float width,height,radius;

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setSize(float width, float height){
        this.width=width;
        this.height=height;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public float getRadius() {
        return radius;
    }

    public Shape getShape() {
        return shape;
    }
}

