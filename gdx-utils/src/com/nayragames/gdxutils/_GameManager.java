package com.nayragames.gdxutils;

import com.badlogic.gdx.Gdx;
import com.nayragames.gdxutils.services.IServices;
import com.nayragames.gdxutils.services.RateMoreServices;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 29-07-2016.
 */
public abstract class _GameManager {

    private static final String TAG = _GameManager.class.getSimpleName();
    public _Main game;
    private static boolean isPaused = false;

    public _GameManager(_Main game){
        this.game=game;
    }

    public static boolean isPaused(){
        return isPaused;
    }

    public static void pause(boolean isSoundPause){
        isPaused = true;
        //if(isSoundPause) BaseSceneManager.pause();
    }

    public void pause(){
        isPaused=true;
    }

    public void resume(){
        isPaused = false;
        //BaseSceneManager.resume();
    }

    public void setScreen (_AbstractScreen screen) {
        if (game.getScreen() != null) {
            game.getScreen().dispose();
        }
        game.setScreen(screen);
    }

    public void rateGame(){
        ((RateMoreServices)game.services).rateGame();
    }

    public abstract void saveData(int level,int score);

    public abstract void getData();

    /**
     * Exit from game. On android platform app going to be in pause so if we start pause game then
     * may be openGL object not resumed so here I use System.exit(0) for runtime termination.
     * System.exit(0) must be avoid.
     *
     */

    public void gameExit(){
        game.getScreen().dispose();
        //game.assets.manager.dispose();
        Gdx.app.exit();
        System.exit(0);
    }

}
