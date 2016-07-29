package com.nayragames.gdxutils.model;

import com.badlogic.gdx.math.Vector2;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 22-02-2016.
 *
 */
public class Scale extends Vector2 {

    public Scale(float x, float y) {
        super(x,y);
    }

    public static Scale makeUnScale(){
        return new Scale(1,1);
    }

    public static Scale makeScale(float x, float y) {
        return new Scale(x, y);
    }

}
