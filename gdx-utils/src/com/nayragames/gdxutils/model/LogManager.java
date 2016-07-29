package com.nayragames.gdxutils.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public class LogManager {

    static float mbData = 1048576.0F;
    static long startTime;
    public static void initTime(){
        startTime = TimeUtils.nanoTime();
    }


    public static void error(String paramString) {

        Gdx.app.error("OBJECT", paramString);
    }

    public static void heapSize(String paramString) {

        error(paramString + "JavaHeap:" + (float) Gdx.app.getJavaHeap() / mbData);
        error(paramString + "NativeHeap:" + (float) Gdx.app.getNativeHeap() / mbData);
        error("\n\n\n");
    }

}
