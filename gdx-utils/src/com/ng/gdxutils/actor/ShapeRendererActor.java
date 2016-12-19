package com.ng.gdxutils.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class ShapeRendererActor extends Group {

    ShapeRenderer shapeRenderer;
    private boolean isDrawLine,isDrawArc;
    float x1,y1,x2,y2;
    private boolean isDrawRect;
    private float rectWidth;

    ShapeRenderer.ShapeType shapeType= ShapeRenderer.ShapeType.Line;
    private float startDegree,endDegree;

    public ShapeRendererActor(){
        shapeRenderer=new ShapeRenderer();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        batch.end();

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setTransformMatrix(batch.getTransformMatrix());
        shapeRenderer.translate(getX(), getY(), 0);

        shapeRenderer.begin(shapeType);
        Gdx.gl20.glLineWidth(10);

        shapeRenderer.setColor(1, 1f, 1f, 1f);
        if (isDrawLine)
            shapeRenderer.line(getX() + getWidth() / 2 - getWidth() / 2 * (float) Math.cos(getRotation()), getY() + getHeight() / 2 - getHeight() / 2 * (float) Math.sin(getRotation()), getX() + getWidth() / 2 + getWidth() / 2 * (float) Math.cos(getRotation()), getY() + getHeight() / 2f + getHeight() / 2 * (float) Math.sin(getRotation()));
        else if (isDrawRect)
            shapeRenderer.rectLine(getX() + getWidth() / 2 - getWidth() / 2 * (float) Math.cos(getRotation()), getY() + getHeight() / 2 - getHeight() / 2 * (float) Math.sin(getRotation()), getX() + getWidth() / 2 + getWidth() / 2 * (float) Math.cos(getRotation()), getY() + getHeight() / 2f + getHeight() / 2 * (float) Math.sin(getRotation()), rectWidth);
        else if (isDrawArc)
            shapeRenderer.arc(getX()+getWidth()/2,getY()+getHeight()/2f,getWidth()/2, startDegree, endDegree);

        shapeRenderer.end();
        batch.begin();
    }

    public void drawLine(float x1,float y1,float x2,float y2,ShapeRenderer.ShapeType shapeType){

        this.shapeType = shapeType;
        setSize(x2 - x1, y2 - y1);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setPosition(x1, y1);
        isDrawLine=true;

        double theta=Math.atan2(y2-y1,x2-x1);
        double angle = Math.toDegrees(theta);
        if (angle < 0) {
            angle += 360;
        }

        setRotation(((float) angle));
    }

    public void drawRect(float x1,float y1,float x2,float y2,float width,ShapeRenderer.ShapeType shapeType){

        this.shapeType=shapeType;
        setSize(x2 - x1, y2 - y1);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setPosition(x1, y1);
        isDrawRect=true;

        double theta=Math.atan2(y2-y1,x2-x1);

        double angle = Math.toDegrees(theta);
        if (angle < 0) {
            angle += 360;
        }

        rectWidth=width;
        setRotation(((float) angle));
    }

    public  void drawArc(float x,float y,float radius,float startDegree,float endDegree,ShapeRenderer.ShapeType shapeType){
        this.shapeType=shapeType;

        setSize(2*radius,2*radius);
        setOrigin(radius, radius);
        setPosition(x - radius, y - radius);
        isDrawArc=true;
        this.startDegree=startDegree;
        this.endDegree=endDegree;
    }

   /* public void drawLine(float x1,float y1,float x2,float y2){

        setSize(x2 - x1, y2 - y1);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setPosition(x1, y1);
        isDrawLine=true;

        double theta=Math.atan2(y2-y1,x2-x1);

        double angle = Math.toDegrees(theta);
        if (angle < 0) {
            angle += 360;
        }

        setRotation(((float) angle));
    }*/

    @Override
    public void act(float delta) {
        super.act(delta);
    }

   /* ShapeRendererActor shapeRenderer=new ShapeRendererActor();
    //shapeRenderer.drawRect(100,100,200,200);
    shapeRenderer.drawLine(100, 100, 200, 200, ShapeRenderer.ShapeType.Line);
    group.addActor(shapeRenderer);

    //shapeRenderer.addAction(Actions.moveBy(200,0,2));
    //shapeRenderer.setRotation(0);
    shapeRenderer.addAction(Actions.forever(Actions.rotateBy(45, 2)));*/

}
