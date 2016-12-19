package com.ng.gdxutils.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */

public class TextureManager {

	public static Texture getPixmapTexture(Color color){
		return new Texture(PixmapBuilder.getPixmapRectangle(1, 1, color));
	}

	public static TextureRegion getTextureRegion(TextureAtlas atlas, String regionName) {
		TextureRegion textureRegion=atlas.findRegion(regionName);
		return textureRegion;
	}

	public static Texture getTexture(String fileName) {

		Texture localTexture = new Texture(Gdx.files.internal("pack/" + fileName));
		localTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		return localTexture;
	}


	public static Texture getTexture(String paramString, AssetManager paramAssetManager) {

		Texture localTexture=null;

		if (!paramAssetManager.isLoaded("pack/" + paramString, Texture.class)) {

			try {
				localTexture = (Texture) paramAssetManager.get("pack/" + paramString, Texture.class);
				localTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

			} catch (Exception localException) {
				localException.printStackTrace();
			}
		}

		return localTexture;
	}

	public static TextureAtlas getAtlas(String fileName, AssetManager paramAssetManager) {

		boolean bool = paramAssetManager.isLoaded("pack/" + fileName, TextureAtlas.class);
		TextureAtlas localTextureAtlas = null;
		if (bool) {

			localTextureAtlas = (TextureAtlas)paramAssetManager.get("pack/" + fileName, TextureAtlas.class);
		}
		return localTextureAtlas;
	}

	public static TextureRegion[] getFrames(TextureAtlas paramTextureAtlas, String paramString, int paramInt1, int paramInt2) {

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[paramInt1 * paramInt2];
		TextureRegion localTextureRegion = getTexture(paramTextureAtlas, paramString);
		TextureRegion[][] arrayOfTextureRegion1 = localTextureRegion.split(localTextureRegion.getRegionWidth() / paramInt1, localTextureRegion.getRegionHeight() / paramInt2);

		int k=0;
		for (int i = 0; i <paramInt2 ; i++)
			for (int j = 0; j <paramInt1 ; j++) {
				arrayOfTextureRegion[k]=arrayOfTextureRegion1[i][j];
				k++;
			}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFlip(TextureRegion[] textureRegion){

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[textureRegion.length];
		for(int i=0;i<textureRegion.length;i++) {
			arrayOfTextureRegion[i]=new TextureRegion(textureRegion[i]);
			arrayOfTextureRegion[i].flip(true, false);
		}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFlipX(TextureRegion[] textureRegion){

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[textureRegion.length];
		for(int i=0;i<textureRegion.length;i++) {
			arrayOfTextureRegion[i]=new TextureRegion(textureRegion[i]);
			arrayOfTextureRegion[i].flip(true, false);
		}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFlipY(TextureRegion[] textureRegion){

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[textureRegion.length];
		for(int i=0;i<textureRegion.length;i++) {
			arrayOfTextureRegion[i]=new TextureRegion(textureRegion[i]);
			arrayOfTextureRegion[i].flip(false, true);
		}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFrames(String paramString, int paramInt1, int paramInt2, AssetManager paramAssetManager) {

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[paramInt1 * paramInt2];
		Texture localTexture = getTexture(paramString, paramAssetManager);
		TextureRegion[][] arrayOfTextureRegion1 = TextureRegion.split(localTexture, localTexture.getWidth() / paramInt1, localTexture.getHeight() / paramInt2);

		int k=0;
		for (int i = 0; i <paramInt2 ; i++)
			for (int j = 0; j <paramInt1 ; j++) {
				arrayOfTextureRegion[k]=arrayOfTextureRegion1[i][j];
				k++;
			}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFrames(String fileName, String regionName, int column, int row, AssetManager paramAssetManager) {

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[column * row];
		TextureRegion localTextureRegion = getTexture(fileName, regionName, paramAssetManager);
		TextureRegion[][] arrayOfTextureRegion1 = localTextureRegion.split(localTextureRegion.getRegionWidth() / column, localTextureRegion.getRegionHeight() / row);

		int k=0;
		for (int i = 0; i <row ; i++)
			for (int j = 0; j <column ; j++) {
				arrayOfTextureRegion[k]=arrayOfTextureRegion1[i][j];
				k++;
			}

		return arrayOfTextureRegion;
	}

	public static TextureRegion[] getFrames(String path, int column, int row) {

		TextureRegion[] frames_num2=new TextureRegion[column*row];
		Texture localTexture=new Texture(path);
		TextureRegion[][] arrayTextureRegion= TextureRegion.split(localTexture,localTexture.getWidth()/column,localTexture.getHeight()/row);

		int k=0;
		for (int i = 0; i <row ; i++)
			for (int j = 0; j <column ; j++) {
				frames_num2[k]=arrayTextureRegion[i][j];
				k++;
			}

		return frames_num2;
	}

	public static TextureRegion[] getFramesByNear(String paramString, int paramInt1, int paramInt2, AssetManager paramAssetManager) {

		TextureRegion[] arrayOfTextureRegion = new TextureRegion[paramInt1 * paramInt2];
		Texture localTexture = makeByNear(paramString, paramAssetManager);
		TextureRegion[][] arrayOfTextureRegion1 = TextureRegion.split(localTexture, localTexture.getWidth() / paramInt1, localTexture.getHeight() / paramInt2);

		int k=0;
		for (int i = 0; i <paramInt2 ; i++)
			for (int j = 0; j <paramInt1 ; j++) {
				arrayOfTextureRegion[k]=arrayOfTextureRegion1[i][j];
				k++;
			}

		return arrayOfTextureRegion;
	}

	public static TextureRegion getTexture(TextureAtlas paramTextureAtlas, String paramString) {

		TextureRegion localObject = null;

			TextureAtlas.AtlasRegion localAtlasRegion = paramTextureAtlas.findRegion(paramString);
			localObject = localAtlasRegion;

		return localObject;
	}

	public static TextureRegion getTexture(String fileName, String regionName, AssetManager paramAssetManager) {

		return ((TextureAtlas)paramAssetManager.get("pack/" + fileName, TextureAtlas.class)).findRegion(regionName);
	}

	public static Texture make(String paramString){
		
		Gdx.app.error("CANDY", "file fail:" + paramString);
		Texture localTexture = new Texture(Gdx.files.internal("pack/" + paramString));
		localTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		return localTexture;
	}

	public static Texture make(String paramString, AssetManager paramAssetManager) {

		if (paramAssetManager.isLoaded("pack/" + paramString, Texture.class)) {}

		Texture localTexture ;
 		  try
			{
				localTexture = (Texture)paramAssetManager.get("pack/" + paramString, Texture.class);
				localTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
				return localTexture;
			}
			catch (Exception localException) {

				localException.printStackTrace();
				localTexture = null;
			}

		return  localTexture;

	}

	public static TextureRegion make(TextureAtlas paramTextureAtlas, String paramString) {

		TextureRegion textureRegion = null;
		if (paramTextureAtlas != null) {
			try {
					TextureAtlas.AtlasRegion localAtlasRegion = paramTextureAtlas.findRegion(paramString);
					textureRegion = localAtlasRegion;
					return textureRegion;
				}
				catch (Exception localException) {
					localException.printStackTrace();
				}
		}
		return null;
	}

	public static TextureRegion make(String paramString1, String paramString2, AssetManager paramAssetManager) {

		return ((TextureAtlas)paramAssetManager.get("pack/" + paramString1, TextureAtlas.class)).findRegion(paramString2);
	}

	public static Texture makeByNear(String paramString, AssetManager paramAssetManager) {

		Texture localTexture=null;
		if (paramAssetManager.isLoaded("pack/" + paramString, Texture.class)) {

			localTexture = (Texture) paramAssetManager.get("pack/" + paramString, Texture.class);
			localTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

		}
		return localTexture;
	}

	  /*Texture background=new Texture("obj/grass.png");
        background.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        TextureRegion trx=new TextureRegion(background,0,0,(int)(w),(int)(h));*/
		//grass.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		//grass.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);


		//TextureRegion trx=new TextureRegion(background,0,0,(int)(w),(int)(h));
		//grass.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
		//grass.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
}
