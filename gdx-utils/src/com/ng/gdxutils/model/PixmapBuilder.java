package com.ng.gdxutils.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/10/2016.
 */
public class PixmapBuilder {

    /** draw circle with particular radius */
    public static Pixmap getPixmapCircle(int radius, Color color, boolean isFilled) {
        Pixmap pixmap=new Pixmap(2*radius+1, 2*radius+1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        if(isFilled)
            pixmap.fillCircle(radius, radius, radius);
        else
            pixmap.drawCircle(radius, radius, radius);
        pixmap.drawLine(radius, radius, 2*radius, radius);
        Pixmap.setFilter(Pixmap.Filter.NearestNeighbour);
        return pixmap;
    }

    /** draw traingle with particular side */
    public static Pixmap getPixmapTraingle(int side, Color color) {
        Pixmap pixmap=new Pixmap(side, side, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillTriangle(0, 0, 0,side,side,(int)(side/2f));
        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    /** draw square with particular side  */
    public static Pixmap getPixmapSquare(int side, Color color) {
        Pixmap pixmap=new Pixmap(side, side, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    public static Pixmap getPixmapRectangle(int width, int height, Color color){
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());

        return pixmap;
    }

    /** draw rounded rectangle width particular parameter */
    public static Pixmap getPixmapRoundedRectangle(int width, int height, int radius, Color color) {
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,radius, pixmap.getWidth(), pixmap.getHeight()-2*radius);
        pixmap.fillRectangle(radius, 0, pixmap.getWidth()-2*radius, pixmap.getHeight());
        pixmap.fillCircle(radius, radius, radius);
        pixmap.fillCircle(radius, pixmap.getHeight()-radius, radius);
        pixmap.fillCircle(pixmap.getWidth()-radius, radius, radius);
        pixmap.fillCircle(pixmap.getWidth()-radius, pixmap.getHeight()-radius, radius);
        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    /** draw pause Button  */
    public static Pixmap getPixmapPauseButton(int side, Color color) {
        Pixmap pixmap=new Pixmap(side, side, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, (int)(side/3f), side);
        pixmap.fillRectangle(side-(int)(side/3f), 0, (int)(side/3f), side);
        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    /** draw Button */
    public static Pixmap getPixmapButton(int width, int height, int border, Color color) {
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        pixmap.setColor(Color.YELLOW);
        pixmap.drawRectangle(border, border, width-2*border, height-2*border);

        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    /** draw loading rounded rectangle */
    public static Pixmap getPixmapLoading(int width, int height, int radius, Color color) {
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,radius, pixmap.getWidth(), pixmap.getHeight()-2*radius);
        pixmap.fillRectangle(radius, 0, pixmap.getWidth()-2*radius, pixmap.getHeight());
        pixmap.fillCircle(radius, radius, radius);
        pixmap.fillCircle(radius, pixmap.getHeight()-radius, radius);
        pixmap.fillCircle(pixmap.getWidth()-radius, radius, radius);
        pixmap.fillCircle(pixmap.getWidth()-radius, pixmap.getHeight()-radius, radius);

        pixmap.drawPixmap(getPixmapRoundedRectangle((int)(width-height*.1f),(int)(height*.9f),(int)(radius*.9f), Color.BLACK), (int)(height*.05f), (int)(height*.05f));

        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    /**draw loading rounded rectangle with two bottom traingle */
    public static Pixmap getPixmapLoadingWidthTraingle(int width, int height, int radius, Color color) {
        Pixmap pixmap=new Pixmap(width, height+(int)(height*.4f), Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.drawPixmap(getPixmapLoading(width, (int)(height), radius, color), 0, 0);
        pixmap.drawPixmap(getPixmapTraingle((int)(height*.4f), color), (int)(width*.6f), height-(int)(height*.25f));
        pixmap.drawPixmap(getPixmapTraingle((int)(height*.4f), color), (int)(width*.7f), height-(int)(height*.25f));

        Pixmap.setFilter(Pixmap.Filter.BiLinear);
        return pixmap;
    }

    public static Pixmap getPixmapHome(float size, Color color) {
        Pixmap pixmap=new Pixmap((int)size,(int)size, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.drawPixmap(getPixmapRoundedRectangle((int)size, (int)size, (int)(size*.2f), color), 0, 0);

        pixmap.setColor(Color.BLACK);
        pixmap.fillRectangle((int)(size*.35f), (int)(size*.7f), (int)(size*.3f), (int)(size*.3f));

        pixmap.drawLine(0, (int)(size*.5f), (int)(size*.5f), 0);
        pixmap.drawLine((int)(size*.5f),0, (int)(size), (int)(size*.5f));

        pixmap.drawLine(0, (int)(size*.6f), (int)(size*.5f),(int)(size*.1f) );
        pixmap.drawLine((int)(size*.5f),(int)(size*.1f), (int)(size), (int)(size*.6f));

        return pixmap;
    }
}
