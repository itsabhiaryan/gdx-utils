package com.nayragames.gdxutils.visui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.widget.*;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/4/2016.
 */

public class VisUIFactory {

    public enum SliderType{
        SOUND,MUSIC
    }

    private static final String TAG = "[" + VisUIFactory.class.getSimpleName() + "]";

    public static VisLabel createLabel(String labelText, ClickListener clickListener){

        VisLabel visLabel=new VisLabel(labelText);
        visLabel.addListener(clickListener);
        return visLabel;
    }

    public static VisLabel createLabel(String labelText, Color color, float scale){

        VisLabel visLabel=new VisLabel(labelText);
        visLabel.scaleBy(scale);
        visLabel.setColor(color);

        return visLabel;
    }

    public static VisSlider createSlider(final SliderType type){

        final VisSlider slider = new VisSlider(0, 1, .1f, true);

        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                if(type== SliderType.SOUND){

                //    Pref.putFloat(Pref.FloatValue.SOUND_VOLUME,slider.getValue());
                 //   Pref.sound=slider.getValue();
               //     Gdx.app.log(TAG,"Value of sound"+Pref.sound);
                }
                else if(type== SliderType.MUSIC){

                  //  Pref.putFloat(Pref.FloatValue.MUSIC_VOLUME,slider.getValue());
                   // Pref.music=slider.getValue();
                 //   Gdx.app.log(TAG,"Value of music"+Pref.music);
                }
            }
        });

        slider.setValue(.1f);
        return slider;
    }

    public static CollapsibleWidget createCollapsibleSlider(final SliderType type){

        VisTable soundTable=new VisTable();
        soundTable.add(VisUIFactory.createSlider(type));

        CollapsibleWidget collapsibleWidget= new CollapsibleWidget(soundTable);
        collapsibleWidget.setCollapsed(true);
        return collapsibleWidget;
    }

    public static VisTextButton createTextButton(Group group, String text, float width, float height, float x, float y, EventListener eventListener, String name){

        VisTextButton visTextButton=createTextButton(group,text,width,height,x,y,eventListener);
        visTextButton.setName(name);
        return visTextButton;
    }

    public static VisTextButton createTextButton(Group group, String text, float width, float height, float x, float y, EventListener eventListener){

        VisTextButton visTextButton=new VisTextButton(text);
        visTextButton.setSize(width,height);
        visTextButton.setPosition(x,y);
        //visTextButton.setFocusBorderEnabled(false);
        //visTextButton.setBackground(VisUI.getSkin().getDrawable("button-blue"));
        group.addActor(visTextButton);
        visTextButton.addListener(eventListener);
        return visTextButton;
    }

    public static VisTextButton createTextButton(Group group, String text, float width, float height, float x, float y){

        VisTextButton visTextButton=new VisTextButton(text);
        visTextButton.setSize(width,height);
        visTextButton.setPosition(x,y);
        //visTextButton.setFocusBorderEnabled(false);
        //visTextButton.setBackground(VisUI.getSkin().getDrawable("button-blue"));
        group.addActor(visTextButton);
        return visTextButton;
    }

/*    private void addVisTextButton(VisTable table, String title, final _AbstractScreen screen) {

        VisTextButton btn = new VisTextButton(title);
        btn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
              //  GameManager.setScreen(screen);
            }
        });

        table.add(btn);
        table.row();
    }*/

    public static VisTextButton addVisTextButton(String buttonName, final int gameType) {

        final VisTextButton button = new VisTextButton(buttonName);
        //button.setBounds(game.w / 2, game.h / 2, game.w * .3f, game.h * .2f);
        //button.setWidth(game.w * .5f);
        //button.setHeight(game.h * .5f);

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ///GameManager.setScreen(new GameScreen(game));
            }
        });
        return button;
    }

    public static void addCollapsibleWidget() {
        VisTable table = new VisTable();
        final CollapsibleWidget collapsibleWidget = new CollapsibleWidget(table);
    }

    public static void addProgresssBar(Table table){

        VisProgressBar visProgressBar=new VisProgressBar(0, 100, 1, false);
        visProgressBar.setValue(50);
        visProgressBar.setAnimateDuration(1);

        table.add(visProgressBar);
    }
}
