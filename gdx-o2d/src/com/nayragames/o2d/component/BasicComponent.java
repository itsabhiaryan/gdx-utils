package com.nayragames.o2d.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

import com.nayragames.gdxutils.model.Position;
import com.nayragames.gdxutils.model.Size;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12-12-2015.
 *
 */
public class BasicComponent implements Component {

    private float x, y;
    private float scaleX=1,scaleY=1;
    private float angle;
    private float width=1,height=1;
    private Rectangle rectangle;
    private float originX, originY;
    private float centerX,centerY;

    public BasicComponent(Size size, Position origin, float angle){

        setPosition(origin,size);
        setOriginX(size.x/2);
        setOriginY(size.y/2);
        setRotation(angle);
        rectangle=new Rectangle();
    }

    public BasicComponent(){
        rectangle=new Rectangle();
    }

    public void setSize(Size size){
        setWidth(size.x);
        setHeight(size.y);
    }

    public void setPosition(Position position){
        setX(position.x);
        setY(position.y);
    }

    public void setPosition(Position origin, Size size){
        setSize(size);
        setX(origin.x-size.x/2f);
        setY(origin.y-size.y/2f);
        centerX=origin.x;
        centerY=origin.y;
    }

    public void setPosition(float x,float y){
        setX(x);
        setY(y);
    }

    public void setOrigin(float x,float y){
        this.originX=x;
        this.originY=y;
    }

    public float getCenterX(){
        return centerX;
    }

    public float getCenterY(){
        return centerY;
    }

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }

    public float getRotation() {
        return angle;
    }

    public void setRotation(float angle) {
        this.angle = angle;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getBoundingRectangle(){

        rectangle.set(getX()+getWidth()/10,getY()+getHeight()/10,getWidth()-getWidth()/5,getHeight()-getHeight()/5);
        return rectangle;
    }

    public void setOriginX(float originX) {
        this.originX = originX;
    }

    public void setOriginY(float originY) {
        this.originY = originY;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public void setScale(float scaleX,float scaleY){
        setScaleX(scaleX);
        setScaleY(scaleY);
    }
}
