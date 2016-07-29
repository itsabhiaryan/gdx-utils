package com.nayragames.o2d;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 28-07-2016.
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
