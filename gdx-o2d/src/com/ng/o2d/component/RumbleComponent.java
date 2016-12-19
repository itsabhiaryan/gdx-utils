package com.ng.o2d.component;

import com.badlogic.ashley.core.Component;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 14-12-2015.
 */
public class RumbleComponent implements Component {

    public float rumbleX;
    public float rumbleY;
    public float rumbleTime = 0;
    public float currentRumbleTime = 1;
    public float rumblePower = 0;
    public float currentRumblePower = 0;

}
