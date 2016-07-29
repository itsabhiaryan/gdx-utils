package com.nayragames.gdxutils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class Rumble {

    private static Random random;
    private static float rumbleX;
    private static float rumbleY;
    private static float rumbleTime = 0;
    private static float currentRumbleTime = 1;
    private static float rumblePower = 0;
    private static float currentRumblePower = 0;
    private static OrthographicCamera cam;
    private static Vector2 positionAfterRumble=new Vector2();

    /** RENDER LOOP */

    public static void render(float delta) {

        if (currentRumbleTime <= rumbleTime) {
            currentRumblePower = rumblePower * ((rumbleTime - currentRumbleTime) / rumbleTime);

            rumbleX = (random.nextFloat() - 0.5f) * 2 * currentRumblePower;
            rumbleY = (random.nextFloat() - 0.5f) * 2 * currentRumblePower;

            cam.translate(-rumbleX, -rumbleY);
            currentRumbleTime += delta;
        } else {
            cam.position.x = positionAfterRumble.x;
            cam.position.y = positionAfterRumble.y;
        }
    }

    public void rumble(float power, float time) {
        rumblePower = power;
        rumbleTime = time;
        currentRumbleTime = 0;
    }

    /** When there is no rumble it just moves the char, once time and power are set then in the render loop
     *  its shakes the camera, this was a test so needs refactoring, just a quick proof of concept.
     */

    public static void setCamera(OrthographicCamera camera){
        cam=camera;
    }

    public static void setPositionAfterRumble(float x,float y){
        positionAfterRumble.set(x,y);
    }
}
