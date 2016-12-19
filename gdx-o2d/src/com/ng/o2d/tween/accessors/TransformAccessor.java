package com.ng.o2d.tween.accessors;

import aurelienribon.tweenengine.TweenAccessor;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * A Tween Accessor for tween data member value of SpriteComponent of ECS.
 * This class must register with Tween for implementation.
 *
 * Created by ARYAN on 11/29/2015.
 */

public class TransformAccessor implements TweenAccessor<TransformComponent> {

    public static final int POS_XY = 1;
    public static final int CPOS_XY = 2;
    public static final int SCALE_XY = 3;
    public static final int ROTATION = 4;
    public static final int OPACITY = 5;
    public static final int TINT = 6;

    @Override
    public int getValues(TransformComponent target, int tweenType, float[] returnValues) {
        switch (tweenType) {
            case POS_XY:
                returnValues[0] = target.x;
                returnValues[1] = target.y;
                return 2;

            case SCALE_XY:
                returnValues[0] = target.scaleX;
                returnValues[1] = target.scaleY;
                return 2;

            case ROTATION: returnValues[0] = target.rotation; return 1;

            default: assert false; return -1;
        }
    }

    @Override
    public void setValues(TransformComponent target, int tweenType, float[] newValues) {
        switch (tweenType) {
            case POS_XY: target.x=newValues[0];
                         target.x= newValues[1];
                break;

            case SCALE_XY: target.x=newValues[0];
                           target.y=newValues[1];
                break;
            case ROTATION: target.rotation=newValues[0]; break;

            default: assert false;
        }
    }
}
