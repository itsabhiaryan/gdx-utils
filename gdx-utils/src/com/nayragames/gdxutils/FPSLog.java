package com.nayragames.gdxutils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 13-10-2015.
 *
 */
public class FPSLog {

    long startTime;

    public FPSLog(){
        startTime = TimeUtils.nanoTime();
    }

    /*public void log(VisText fpsComponent) {

        if (TimeUtils.nanoTime() - startTime > 1000000000) *//* 1,000,000,000ns == one second *//*{
           // Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());

            fpsComponent.setText("FPS : "+String.valueOf(Gdx.graphics.getFramesPerSecond()));

            startTime = TimeUtils.nanoTime();
        }
    }*/

    public void log(Label label){

        if (TimeUtils.nanoTime() - startTime > 1000000000) /* 1,000,000,000ns == one second */{
            // Gdx.app.log("FPSLogger", "fps: " + Gdx.graphics.getFramesPerSecond());

            label.setText("FPS : "+String.valueOf(Gdx.graphics.getFramesPerSecond()));
            startTime = TimeUtils.nanoTime();
        }
    }
}
