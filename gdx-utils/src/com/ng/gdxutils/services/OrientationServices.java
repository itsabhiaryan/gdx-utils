package com.ng.gdxutils.services;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public interface OrientationServices extends IServices {

    enum Orientation {
        FULL_SENSOR(0),
        LANDSCAPE(1),
        PORTRAIT(2);

        public int value;
        Orientation(int value){
            this.value=value;
        }
    }

    void setOrientation(Orientation orientation);
}
