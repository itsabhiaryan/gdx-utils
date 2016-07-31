package com.nayragames.gdxutils;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.nayragames.gdxutils.tween.accessor.CameraAccessor;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-07-2016.
 *
 */
public class TweenBuilder {

    public static void sceneZoomOut(OrthographicCamera camera, TweenManager tweenManager){
        camera.zoom=0;
        Tween.to(camera, CameraAccessor.ZOOM,1).target(1).start(tweenManager);
    }

    public static void sceneZoomIn(OrthographicCamera camera,TweenManager tweenManager){
        camera.zoom=1;
        Tween.to(camera, CameraAccessor.ZOOM,1).target(0).start(tweenManager);
    }

    public static void zoomPageTransation(final Array<Sprite> hide, final Array<Sprite> show, final OrthographicCamera camera, final TweenManager tweenManager){

        Timeline.createSequence()
                .push(Tween.to(camera, CameraAccessor.ZOOM,1).target(0)
                        .setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                for(Sprite sprite:hide)
                                    sprite.setAlpha(0);
                                for (Sprite sprite:show)
                                    sprite.setAlpha(1);
                            }
                        }).setCallback(new TweenCallback() {
                            @Override
                            public void onEvent(int i, BaseTween<?> baseTween) {
                                Tween.to(camera, CameraAccessor.ZOOM,1).target(1).start(tweenManager);
                            }
                        }).start(tweenManager));
    }

}
