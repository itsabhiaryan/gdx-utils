/*
package com.nayragames.gdxutils.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisTextButton;

*/
/**
 * Created by ARYAN on 12/6/2015.
 *//*

public class VisUIManager {

    private static final String TAG = "[" + VisUIManager.class.getSimpleName() + "]";
    public static boolean isVisUIEnabled;
    public static TextButton loginFb,shareFb;
    public static Label fbName;

    public static void enableVisUI() {
        VisUI.load(VisUI.SkinScale.X2);
        isVisUIEnabled = true;
    }

    public static Array<SplitPane> addSplitPane(Table stage, final AbstractScreen screen) {

        final float w = game.w;
        final float h = game.h;
        float scroll_height = h * 1.35f;
        float scroll_width= w*.4f;

        VisTextButton.VisTextButtonStyle visTextButtonStyle= VisUI.getSkin().get(VisTextButton.VisTextButtonStyle.class);
        visTextButtonStyle.font=Assets.bitmapFontAsset.uiFont;

        Group leftScrollG = GroupBuilder.makeGroup(0,0,w,scroll_height);
        ScrollPane leftScrollPane= UIFactory.getScrollPane(leftScrollG,w,h,0,0);
        ImageBuilder.makeImage(leftScrollG,new Texture(PixmapBuilder.getPixmapRectangle(1, 1, Color.GRAY)),Size.makeSize(scroll_width,scroll_height), Position.makePosition(w*.2f,scroll_height/2f));

        LabelBuilder.makeLabel(leftScrollG, "settings", Color.BLACK, Align.left, w * .2f, h * .1f, w * .1f, scroll_height - h * .05f);
        LabelBuilder.makeLabel(leftScrollG, "audio", Color.GOLDENROD, Align.left, w * .2f, h * .1f, w * .1f, scroll_height - h * .15f);

        final Label sound_on=LabelBuilder.makeLabel(leftScrollG,"on", Color.WHITE, Align.center,w*.2f, h*.1f,w*.275f,scroll_height-h*.25f);
        final Label sound_off=LabelBuilder.makeLabel(leftScrollG,"off", Color.RED, Align.center,w*.2f, h*.1f,w*.275f,scroll_height-h*.25f);

        boolean sound_enable=Pref.getBoolean(Pref.BooleanValue.SOUND_ENABLED);
        sound_on.setVisible(sound_enable);
        sound_off.setVisible(!sound_enable);

        LabelBuilder.makeLabel(leftScrollG, "sound", Color.WHITE, Align.center, w * .2f, h * .1f, w * .1f, scroll_height - h * .25f,
        new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                boolean sound_enable=Pref.getBoolean(Pref.BooleanValue.SOUND_ENABLED);
                sound_on.setVisible(!sound_enable);
                sound_off.setVisible(sound_enable);
                Pref.putBoolean(Pref.BooleanValue.SOUND_ENABLED,!sound_enable);
            }
        });

        final Label music_on=LabelBuilder.makeLabel(leftScrollG,"on", Color.WHITE, Align.center,w*.2f, h*.1f,w*.275f,scroll_height-h*.35f);
        final Label music_off=LabelBuilder.makeLabel(leftScrollG,"off", Color.RED, Align.center,w*.2f, h*.1f,w*.275f,scroll_height-h*.35f);

        boolean music_enable=Pref.getBoolean(Pref.BooleanValue.MUSIC_ENABLED);
        music_on.setVisible(music_enable);
        music_off.setVisible(!music_enable);

        LabelBuilder.makeLabel(leftScrollG, "music", Color.WHITE, Align.center, w * .2f, h * .1f, w * .1f, scroll_height - h * .35f,
        new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                boolean music_enable=Pref.getBoolean(Pref.BooleanValue.MUSIC_ENABLED);
                music_on.setVisible(!music_enable);
                music_off.setVisible(music_enable);
                BaseSceneManager.musicAction(!music_enable);
                Pref.putBoolean(Pref.BooleanValue.MUSIC_ENABLED,!music_enable);
            }
        });

        ImageBuilder.makeImage(leftScrollG,TextureManager.getPixmapTexture(Color.BLACK),Size.makeSize(w * .325f, h * .01f),Position.makePosition(w * .01f+w * .325f/2f, scroll_height - h * .42f+h * .01f/2f));
        LabelBuilder.makeLabel(leftScrollG,"facebook", Color.GOLDENROD, Align.left,w*.2f, h*.1f,w*.1f,scroll_height-h*.475f);

        fbName=  LabelBuilder.makeLabel(leftScrollG, Pref.getString(Pref.StringValue.USER_NAME), Color.WHITE, Align.center, w * .2f, h * .1f, w * .175f, scroll_height - h * .575f,
        new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(game.fb.gdxFacebook.isSignedIn()) {
                    fbName.setVisible(false);
                    loginFb.setVisible(true);
                    game.fb.logout();
                }
                return super.touchDown(event, x, y, pointer, button);
            }
        });

       loginFb= VisUIFactory.createTextButton(leftScrollG,"Login",w * .2f, h * .1f,w * .075f, scroll_height - h * .625f,new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                super.tap(event, x, y, count, button);
                game.fb.loginWithPublishPermissions();
                //game.fb.loginWithReadPermissions();
            }
        });

       boolean isSigned=game.fb.gdxFacebook.isSignedIn();
       fbName.setVisible(isSigned);
       loginFb.setVisible(!isSigned);

       shareFb= VisUIFactory.createTextButton(leftScrollG,"share score",w*.275f, h*.1f,w*.035f,scroll_height-h*.75f,new ActorGestureListener(){
           @Override
           public void tap(InputEvent event, float x, float y, int count, int button) {
               if(game.fb.gdxFacebook.isSignedIn()) game.fb.postToUserWall();
               super.tap(event, x, y, count, button);
           }
       });

       ImageBuilder.makeImage(leftScrollG,TextureManager.getPixmapTexture(Color.BLACK),Size.makeSize(w * .325f, h * .01f),Position.makePosition(w * .01f+w*.325f/2f, scroll_height - h * .8f+h*.01f/2f));
       LabelBuilder.makeLabel(leftScrollG, "google play", Color.GOLDENROD, Align.left, w * .2f, h * .1f, w * .1f, scroll_height - h * .85f);
       LabelBuilder.makeLabel(leftScrollG, "leaderboard", Color.WHITE, Align.center, w * .2f, h * .1f, w * .175f, scroll_height - h * .95f);

       ImageBuilder.makeImage(leftScrollG,TextureManager.getPixmapTexture(Color.BLACK),Size.makeSize(w * .325f,h * .01f),Position.makePosition(w * .01f+w*.325f/2f, scroll_height - h * 1.025f+h*.01f/2f));

       LabelBuilder.makeLabel(leftScrollG, "support", Color.GOLDENROD, Align.left, w * .2f, h * .1f, w * .1f, scroll_height - h * 1.1f);
       LabelBuilder.makeLabel(leftScrollG, "rate this app", Color.WHITE, Align.center, w * .2f, h * .1f, w * .175f, scroll_height - h * 1.2f,
       new ActorGestureListener(){
           @Override
           public void tap(InputEvent event, float x, float y, int count, int button) {
               super.tap(event, x, y, count, button);
               game.rateURL();
           }
       });

       LabelBuilder.makeLabel(leftScrollG, "more games", Color.WHITE, Align.center, w * .2f, h * .1f, w * .175f, scroll_height - h *1.3f,
       new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                game.moreGamesURL();
                super.tap(event, x, y, count, button);
           }
       });

       Sprite sprite = new Sprite(TextureManager.getPixmapTexture(Color.BLACK));
       sprite.setSize(w * .0001f, h);

       Table centerTable = new Table();
       final SplitPane leftSplitPane = new SplitPane(leftScrollPane, centerTable, false, new SplitPane.SplitPaneStyle(new SpriteDrawable(sprite)));
       leftSplitPane.setSplitAmount(0);
       leftSplitPane.setMaxSplitAmount(.35f);
       stage.add(leftSplitPane).fill().expand();

       Group centerG = new Group();
       float scroll_height_right = h * 1.25f;

       Group rightScrollG=GroupBuilder.makeGroup(0,0,w,scroll_height_right);
       ScrollPane rightScrollPane=UIFactory.getScrollPane(rightScrollG,w,h,0,0);

       ImageBuilder.makeImage(rightScrollG,TextureManager.getPixmapTexture(Color.GRAY),Size.makeSize(w * .4f, scroll_height_right),Position.makePosition(w*.4f/2f,scroll_height_right/2f));
       LabelBuilder.makeLabel(rightScrollG, "Score : "+String.valueOf(Pref.getInteger(Pref.IntegerValue.SCORE)), Color.BLACK, Align.left, w * .2f, h * .1f,w * .1f, scroll_height_right - h * .05f);
       LabelBuilder.makeLabel(rightScrollG, "Deaths : "+String.valueOf(Pref.getInteger(Pref.IntegerValue.DEATH)), Color.BLACK, Align.left, w * .2f, h * .1f, w * .1f, scroll_height_right - h * .15f);
       addButtons(rightScrollG,w,h,scroll_height_right);

       final SplitPane rightSplitPane = new SplitPane(centerG, rightScrollPane, false, new SplitPane.SplitPaneStyle(new SpriteDrawable(sprite)));
       rightSplitPane.setSplitAmount(1);
       centerTable.add(rightSplitPane).fill().expand();

       UIFactory.getImageButton(centerG,Assets.imageAsset.setting,h * .125f,w * .075f,w * .1f, h * .05f,
       new ActorGestureListener(){
               @Override
               public void tap(InputEvent event, float x, float y, int count, int button) {
                    if(leftSplitPane.getSplit()==0) ((MainScreen) screen).leftPane();
                    else ((MainScreen) screen).rightPane();
                    super.tap(event, x, y, count, button);
                   }
               }
       );

       UIFactory.getImageButton(centerG,Assets.imageAsset.stage,w * .075f,h * .125f,w * .9f - w * .075f, h * .05f,
       new ActorGestureListener(){
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                ((MainScreen) screen).rightPane();
                super.tap(event, x, y, count, button);
            }}
       );

       Array<SplitPane> visSplitPanes = new Array<SplitPane>();
       visSplitPanes.add(leftSplitPane);
       visSplitPanes.add(rightSplitPane);
       return visSplitPanes;
    }

    private static void addButtons(Group group, float w, float h, float scroll_height_right) {

        int stage = Pref.getInteger(Pref.IntegerValue.STAGE);
        int timeline = Pref.getInteger(Pref.IntegerValue.TIMELINE);
        String name[] = new String[]{"1st day", "2nd day", "3rd day"};

        for (int i = 0; i < name.length; i++) {
            final TextButton first= VisUIFactory.createTextButton(group,name[i],w * .275f, h * .1f,w * .01f, scroll_height_right - h * .325f - i * h * .275f);
            first.setName(String.valueOf(i));
            first.addListener(new ActorGestureListener(){
                @Override
                public void tap(InputEvent event, float x, float y, int count, int button) {
                    super.tap(event, x, y, count, button);
                    if (!first.isDisabled()) {
                        GameSceneManager.stage = Integer.valueOf(first.getName());
                        GameSceneManager.timeline = 0;
                        GameManager.setScreen(new GameScreen(game));
                    }
                }
            });

            for (int j = 0; j < 5; j++) {

                final TextButton firstButton = VisUIFactory.createTextButton(group," " + (j + 1) + " ",w * .05f, w * .05f,w * .01f + j * w * .07f, scroll_height_right - h * .45f - i * h * .275f);
                firstButton.setName(String.valueOf(i) + String.valueOf(j));
                if (stage == i) {
                    if (j > timeline) firstButton.setDisabled(true);
                } else if (stage < i) {
                    first.setDisabled(true);
                    firstButton.setDisabled(true);
                }

                firstButton.addListener(new ActorGestureListener(){
                                            @Override
                                            public void tap(InputEvent event, float x, float y, int count, int button) {
                                                super.tap(event, x, y, count, button);
                                                if (!firstButton.isDisabled()) {
                                                    char charType[] = firstButton.getName().toCharArray();
                                                    GameSceneManager.stage = charType[0] - 48;
                                                    GameSceneManager.timeline = charType[1] - 48;
                                                    GameManager.setScreen(new GameScreen(game));
                                                }
                                            }
                                        }
                );
            }
        }
    }
}
*/
