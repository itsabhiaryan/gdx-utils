package com.nayragames.gdxutils.model;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 */

public class GroupBuilder {
	
	public static Group makeGroup(Group group, float startX, float startY, float width, float height, String name) {
		Group localGroup= new Group();
		localGroup.setPosition(startX, startY);
		localGroup.setSize(width, height);
		localGroup.setOrigin(localGroup.getWidth()/2f,localGroup.getHeight()/2f);
		localGroup.setName(name);
		group.addActor(localGroup);
		return localGroup;
	}
	
	public static Group makeGroup(Stage stage, float startX, float startY, float width, float height, String name) {
		Group localGroup=new Group();
		localGroup.setPosition(startX, startY);
		localGroup.setSize(width, height);
		localGroup.setName(name);
		stage.addActor(localGroup);
		return localGroup;
	}
}
