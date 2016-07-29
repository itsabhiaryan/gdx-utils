package com.nayragames.vis;

/**
 * Created by aryan9234 on 28-07-2016.
 */
public class Constants {


    private static boolean isPaused;

    public static boolean isPaused(){
        return isPaused;
    }

    public static void pause(){
        isPaused=true;
    }

    public static void resume(){
        isPaused=false;
    }



}
