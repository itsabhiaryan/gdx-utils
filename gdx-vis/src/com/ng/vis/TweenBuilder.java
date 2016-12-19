package com.ng.vis;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.ng.vis.component.BasicComponent;
import com.ng.vis.tween.accessors.BasicComponentAccessor;
import com.ng.vis.tween.accessors.TransformAccessor;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 30-11-2015.
 *
 * Collection of different tween used in ECS.
 *
 */
public class TweenBuilder {

    private static final String TAG =TweenBuilder.class.getSimpleName();

    public static void buttonInterface(Transform sprite, final TweenCallback tweenCallback,TweenManager tweenManager){
        Timeline.createSequence()
                .push(Tween.to(sprite, TransformAccessor.SCALE_XY,.1f).targetRelative(-.05f,-.05f))
                .push(Tween.to(sprite, TransformAccessor.SCALE_XY,.1f).targetRelative(.05f,.05f))
                .setCallback(tweenCallback)
                .start(tweenManager);
    }

    public static void applyScale(Transform transform,TweenManager tweenManager){
        Timeline.createSequence()
                .push(Tween.to(transform, TransformAccessor.SCALE_XY, .1f).target(1.25f, 1.25f))
                .push(Tween.to(transform, TransformAccessor.SCALE_XY, .1f).target(1, 1))
                .start(tweenManager);
    }

    public static void applyCollectionTween(Transform transform, TweenCallback tweenCallback,TweenManager tweenManager){

        Timeline.createSequence()
                .beginParallel()
                .beginSequence()
                .push(Tween.to(transform, TransformAccessor.SCALE_XY, .5f).target(.5f, .5f))
                .end()
                .push(Tween.to(transform, TransformAccessor.POS_XY, 1).targetRelative(-1, 2))
                .end()
                .setCallback(tweenCallback).start(tweenManager);
    }

    public static void applyThrust(BasicComponent shipBasic, TweenCallback callback, TweenManager tweenManager){

        Timeline.createSequence()
                .push(Tween.to(shipBasic, BasicComponentAccessor.POS_XY,.1f).targetRelative(0,.1f))
                .push(Tween.to(shipBasic, BasicComponentAccessor.POS_XY,.1f).targetRelative(0,-.1f))
                .setCallback(callback)
                .start(tweenManager);
    }

    public static void playerDieTween(VisSprite spriteComponent){

      /*  Timeline.createSequence()
                .push(Tween.to(spriteComponent, ASpriteComponentAccessor.OPACITY,.5f).target(0))
                .push(Tween.to(spriteComponent, ASpriteComponentAccessor.OPACITY,.5f).target(1))
                .start(_Main.tweenManager);*/
    }
}
