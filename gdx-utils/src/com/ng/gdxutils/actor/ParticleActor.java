package com.ng.gdxutils.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;

import com.ng.gdxutils.model.Position;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 25-07-2016
 */

public class ParticleActor extends Actor {

	private  ParticleEffectPool.PooledEffect effect;
	//public static Array<ParticleEffectPool.PooledEffect> effects=new Array<ParticleEffectPool.PooledEffect>();

	public float scale;
	public int type;

	public ParticleActor(ParticleEffectPool.PooledEffect effect) {

		this.effect = effect;
	}

		public static ParticleActor particle(Group parmGroup, Position origin, float width, float height, float scale, ParticleEffectPool.PooledEffect  particleEffect){

			//effects.add(particleEffect);

			ParticleActor particleActor=new ParticleActor(particleEffect);
			particleActor.setSize(width, height);
			particleActor.setPosition(origin);
			particleActor.setTouchable(Touchable.disabled);
			parmGroup.addActor(particleActor);

			//if(typeBlast==KING_PARTICLE)
				//particleEffect.start();

			return particleActor;
		}

	   @Override
	   public void draw(Batch batch, float parentAlpha) {
	      effect.draw(batch);
	   }

	   @Override
	   public void act(float delta) {
	      super.act(delta);
	      effect.setPosition(getX() + getWidth() / 2, getY() + getHeight() / 2);
	      effect.update(delta);

			   if(effect.isComplete()){

				   removeEffect();
		   }
	   }

	   public ParticleEffectPool.PooledEffect getEffect() {
	      return effect;
	   }

		public void setPosition(Position origin) {
			this.setPosition(origin.x - getWidth()/2f,origin.y-getHeight() / 2f);
			this.setOrigin(getWidth()/2, getHeight()/2f);
		}

	   public Position getPosition() {
		   Position point= Position.makePosition(getX(),getY());
		   return point;
	   }

		public void removeEffect(){

			/*for(int i=effects.size-1;i>=0;i--) {
				ParticleEffectPool.PooledEffect effect = effects.get(i);
				if(effect.isComplete()){
					effect.free();
					effects.removeIndex(i);
				}
			}*/

			effect.free();
			//effects.removeValue(effect,true);
			remove();
		}

		/*public static void resetAllEffect() {

			for(int i=effects.size-1;i>=0;i--)
				effects.get(i).free();
				effects.clear();
		}*/


	public void rotateBy(float amountInDegrees) {
		Array<ParticleEmitter> emitters = getEffect().getEmitters();
		for (int i = 0; i < emitters.size; i++) {
			ParticleEmitter.ScaledNumericValue val = emitters.get(i).getAngle();
			float amplitude = (val.getHighMax() - val.getHighMin()) / 2f;
			float h1 = amountInDegrees + amplitude;
			float h2 = amountInDegrees - amplitude;
			val.setHigh(h1, h2);
			val.setLow(amountInDegrees);
		}
	}

	public void setColors(float[] colors){
		getEffect().getEmitters().get(0).getTint().setColors(colors);
	}




	}