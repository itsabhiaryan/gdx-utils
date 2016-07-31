package com.nayragames.gdxutils.tween.accessor;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;

/**
 * A Tween Accessor for tween data member value of VisSplitPane of VisUI.
 * This class must register with Tween for implementation.
 *
 * Created by ARYAN on 12/6/2015.
 */

public class SplitPaneAccessor implements TweenAccessor<SplitPane> {

    public static final int SPLIT_AMOUNT = 1;
    private static final String TAG = "[" + SplitPaneAccessor.class.getSimpleName() + "]";

    @Override
    public int getValues(SplitPane target, int tweenType, float[] returnValues) {

        switch (tweenType) {
            case SPLIT_AMOUNT:
                returnValues[0] = target.getSplit();
               // Gdx.app.log(TAG,"get Value of split amount"+returnValues[0]);
                return 1;

            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(SplitPane target, int tweenType, float[] newValues) {
       // Gdx.app.log(TAG,"setter value"+target.getSplit()+"And type"+tweenType+"new value"+newValues[0]);
        switch (tweenType) {
            case SPLIT_AMOUNT:
                target.setSplitAmount(newValues[0]);
                //Gdx.app.log(TAG,"set Value of split amount"+newValues[0]);

                break;

            default:
                assert false;
        }
    }
}
