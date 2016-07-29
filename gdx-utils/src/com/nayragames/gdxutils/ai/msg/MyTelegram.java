package com.nayragames.gdxutils.ai.msg;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;

/**
 * (c) Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since  14-12-2015.
 */
public class MyTelegram implements Telegraph {

	@Override
	public boolean handleMessage(Telegram msg) {
	
		System.out.println("my telegram message ");
		
		return false;
	}

}
