package com.nayragames.vis.tween.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.kotcrab.vis.runtime.component.Transform;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 11/29/2015.
 *
 * A Tween Accessor for tween data member value of SpriteComponent of ECS.
 * This class must register with Tween for implementation.
 *
 */

public class TransformAccessor implements TweenAccessor<Transform> {

    public static final int POS_XY = 1;
    public static final int SCALE_XY = 3;
    public static final int ROTATION = 4;

    @Override
    public int getValues(Transform target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POS_XY:
                returnValues[0] = target.getX();
                returnValues[1] = target.getY();
                return 2;

            case SCALE_XY:
                returnValues[0] = target.getScaleX();
                returnValues[1] = target.getScaleY();
                return 2;

            case ROTATION: returnValues[0] = target.getRotation(); return 1;

            default: assert false; return -1;
        }

    }

    @Override
    public void setValues(Transform target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POS_XY: target.setPosition(newValues[0], newValues[1]); break;
            case SCALE_XY: target.setScale(newValues[0], newValues[1]); break;
            case ROTATION: target.setRotation(newValues[0]); break;

            default: assert false;
        }
    }
}
