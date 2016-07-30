package com.ng;

import com.nayragames.gdxutils._GameManager;
import com.nayragames.gdxutils.services.IServices;

/**
 * Created by aryan9234 on 29-07-2016.
 */
public class GameManager extends _GameManager {





    public GameManager(Main game){
        super(game);
        Pref.init();




    }

    @Override
    public void saveData(int level, int score) {
         /*  int pref_level= Pref.getInteger(Pref.IntegerValue.LEVEL);
        int pref_score=Pref.getInteger(Pref.IntegerValue.SCORE);


        if(pref_level==level+1) {
            Pref.putInteger(Pref.IntegerValue.LEVEL, pref_level + 1);
            int new_score = pref_score + score;
            Pref.putInteger(Pref.IntegerValue.SCORE, new_score);
            if(new_score>10)
                game.getGoogleServices().submitScore(new_score);
        }*/
    }

    @Override
    public void getData() {

    }
}
