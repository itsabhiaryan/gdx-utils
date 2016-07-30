package com.nayragames.o2d;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nayragames.gdxutils.model.TextureManager;
import com.uwsoft.editor.renderer.resources.FontSizePair;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 1/29/2016.
 *
 * Inherit this class if you wanna add resource to runtime.
 */
public class ResourceLoader extends ResourceManager {

    protected float w,h;
    public static final String WHITE_TEXTURE="white";
    public static final String LEADER_BOARD="leaderboard";
    public static final String TAP_HERE ="tap";
    public static final String FAILED_PARTICLE="fail",COMPLETE_PARTICLE="success";
    public static FontSizePair fontSizePair_0,fontSizePair_1,fontSizePair_2;
    public static Color colors[];

    public ResourceLoader(float w, float h){
        this.w=w;
        this.h=h;
    }

    @Override
    public void initAllResources() {
        super.initAllResources();

        int fontSize_0=(int)w/5;
        int fontSize_1=(int)w/10;
        int fontSize_2=(int)w/12;

      /*  loadFont(fontSizePair_0=new FontSizePair(Fonts.GAME_FONT.value,fontSize_0));
        loadFont(fontSizePair_1=new FontSizePair(Fonts.GAME_FONT.value,fontSize_1));
        loadFont(fontSizePair_2=new FontSizePair(Fonts.GAME_FONT.value,fontSize_2));
*/
        //SoundEffects.loadMusic();
        loadParticles();
        loadTexture();

        colors=new Color[]{Color.FIREBRICK, Color.FOREST, Color.MAGENTA , Color.CORAL, Color.VIOLET, Color.GOLDENROD, Color.LIME, Color.MAROON, Color.OLIVE, Color.PURPLE, Color.TEAL, Color.ROYAL, Color.SCARLET, Color.SKY};
    }


    public void addSprite(String name, TextureRegion textureRegion){
        mainPack.addRegion(name,textureRegion);
    }

    public void addParticle(String name, ParticleEffect effect){
        particleEffects.put(name,effect);
    }

    public TextureAtlas getPack(){
        return mainPack;
    }

    protected void loadParticles(){

       /* ParticleEffect particleEffect1=new ParticleEffect();
        particleEffect1.load(Gdx.files.internal(particleEffectsPath + File.separator + Particles.COMPLETE.value), Gdx.files.internal(particleEffectsPath));
        particleEffects.put(COMPLETE_PARTICLE,particleEffect1);

        ParticleEffect particleEffect3=new ParticleEffect();
        particleEffect3.load(Gdx.files.internal(particleEffectsPath + File.separator + Particles.FAILED.value), Gdx.files.internal(particleEffectsPath));
        particleEffects.put(FAILED_PARTICLE,particleEffect3);*/
    }

    protected void loadTexture(){
        mainPack.addRegion(WHITE_TEXTURE,new TextureRegion(TextureManager.getPixmapTexture(Color.WHITE)));
      //  mainPack.addRegion(LEADER_BOARD,new TextureRegion(new Texture(Images.LEADER_BOARD.value)));
       // mainPack.addRegion(TAP_HERE,new TextureRegion(new Texture(Images.TAP_HERE.value)));
    }
}
