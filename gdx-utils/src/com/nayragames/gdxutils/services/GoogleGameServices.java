package com.nayragames.gdxutils.services;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */
public interface GoogleGameServices extends IServices{

    void signIn();
    void signOut();

    void submitScore(long score);
    void showScores();

    boolean isSignedIn();
    void start();

}
