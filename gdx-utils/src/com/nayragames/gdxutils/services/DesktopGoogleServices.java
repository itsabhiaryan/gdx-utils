package com.nayragames.gdxutils.services;

import com.badlogic.gdx.Gdx;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */

public abstract class DesktopGoogleServices implements RateMoreServices, com.nayragames.gdxutils.services.AdServices, com.nayragames.gdxutils.services.GoogleGameServices, com.nayragames.gdxutils.services.OrientationServices {
	
	private static final String TAG=DesktopGoogleServices.class.getSimpleName();
	
	@Override
	public void signIn() {
		Gdx.app.log(TAG, "DesktopGoogleServies: signIn()");
	}

	@Override
	public void signOut() {
		Gdx.app.log(TAG, "DesktopGoogleServies: signOut()");
	}

	@Override
	public abstract void rateGame() ;

	@Override
	public void submitScore(long score) {
		Gdx.app.log(TAG, "DesktopGoogleServies: submitScore(" + score + ")");
	}

	@Override
	public void showScores() {
		Gdx.app.log(TAG, "DesktopGoogleServies: showScores()");
	}

	@Override
	public boolean isSignedIn() {
		Gdx.app.log(TAG, "DesktopGoogleServies: isSignedIn()");
		return false;
	}

	@Override
	public void start() {
		Gdx.app.log(TAG, "DesktopGoogleServies: start()");
	}

	@Override
	public abstract void moreGames() ;
		//Gdx.net.openURI(Constants.PublisherIDLink.GOOGLE.defaultValue);
		//Gdx.app.log(TAG, "DesktopGoogleServies: moreGames()");
	//}
	
	@Override
	public void showTopAd(boolean isShow) {

		//Gdx.app.log(TAG, "DesktopGoogleServies: showTopAd()");
	}

	@Override
	public void showBottomAd(boolean isShow) {

		//Gdx.app.log(TAG, "DesktopGoogleServies: showBottomAd()");
	}

	@Override
	public void setOrientation(Orientation orientation) {

	}
}