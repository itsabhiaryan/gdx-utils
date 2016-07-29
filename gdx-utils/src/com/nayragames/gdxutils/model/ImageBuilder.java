package com.nayragames.gdxutils.model;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 * Builder class that have method which return image.
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageBuilder {
	
	public static Image makeImage(Group group, Texture texture, Size size, Position origin, String name) {

		texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		Image localImage=new Image(texture);
		float width=size.x;
		float height=size.y;
		localImage.setSize(width, height);
		localImage.setPosition(origin.x-width/2.0F, origin.y-height/2.0F);
		localImage.setOrigin(width/2.0F, height/2.0F);
		group.addActor(localImage);
		return localImage;
	}
	
	
	public static Image makeImage(Group group, AtlasRegion atlasRegion, Size size, Position origin, String name) {

		Image localImage=new Image(atlasRegion);
		float width=size.x;
		float height=size.y;
		localImage.setSize(width, height);
		localImage.setPosition(origin.x-width/2.0F, origin.y-height/2.0F);
		localImage.setOrigin(width/2.0F, height/2.0F);
		localImage.setName(name);
		group.addActor(localImage);
		return localImage;
	}

	public static Image makeImage(Group paramGroup, TextureRegion paramTextureRegion, Size paramGSize, Position paramPosition) {

		Image localImage = new Image(paramTextureRegion);
		localImage.setSize(paramGSize.x, paramGSize.y);

		float f2 = paramGSize.y;
		float f1 = paramGSize.x;
		localImage.setPosition(paramPosition.x - f1 / 2.0F, paramPosition.y - f2 / 2.0F);
		localImage.setOrigin(f1 / 2.0F, f2 / 2.0F);

		paramGroup.addActor(localImage);
		return localImage;
	}

	public static Image makeImage(Group paramGroup, TextureRegion paramTextureRegion, Size paramGSize, Position paramPosition, String name) {

		Image localImage=makeImage(paramGroup,paramTextureRegion,paramGSize,paramPosition);
		localImage.setName(name);

		return localImage;
	}

	public static Image makeImage(Group group, Texture texture,float width,float height,float x,float y ,int align){

		Image localImage = new Image(texture);
		localImage.setSize(width,height);
		localImage.setOrigin(width/2f,height/2f);
		localImage.setPosition(x,y,align);
		group.addActor(localImage);
		return localImage;
	}

	public static Image makeImage(Group group,TextureRegion textureRegion,float width,float height,float x,float y){

		Image localImage = new Image(textureRegion);
		localImage.setSize(width,height);
		localImage.setOrigin(width/2f,height/2f);
		localImage.setPosition(x-width/2f,y-height/2f);
		group.addActor(localImage);
		return localImage;


	}

}
