package com.nayragames.gdxutils.tween.accessor;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Transform;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/30/2015.
 */
public class BodyAccessor implements TweenAccessor<Body> {

    public static final int POS_XY = 1;
    public static final int ROTATION = 2;

    @Override
    public int getValues(Body target, int tweenType, float[] returnValues) {

        switch (tweenType) {
            case POS_XY:
                returnValues[0] = target.getTransform().getPosition().x;
                returnValues[1] = target.getTransform().getPosition().y;
                return 2;

            case ROTATION:
                returnValues[0] = target.getTransform().getRotation();
                return 1;

            default: assert false; return -1;
        }
    }

    @Override
    public void setValues(Body target, int tweenType, float[] newValues) {
        Transform transform=target.getTransform();
        switch (tweenType) {

            case POS_XY:
                target.setTransform(newValues[0],newValues[1],transform.getRotation());

                break;

            case ROTATION:

                target.setTransform(transform.getPosition().x,transform.getPosition().y,newValues[0]);

                break;

            default: assert false;
        }
    }
}
