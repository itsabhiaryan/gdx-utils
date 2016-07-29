
package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.component.HealthComponent;
import com.nayragames.o2d.component.LeftRightComponent;
import com.uwsoft.editor.renderer.SceneLoader;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * System that move entity from left to right and vice versa.
 *
 * Created by ARYAN on 14-01-2016.
 */

public class LeftRightSystem extends IteratingSystem {

	private static final String TAG = "[" + LeftRightSystem.class.getSimpleName() + "]";
	ComponentMapper<LeftRightComponent> leftRightMapper;
	ComponentMapper<DimensionsComponent> spriteCm;
    ComponentMapper<TransformComponent> transformCm;
	SceneLoader sceneLoader;
	//GroupManager groupManager;
	//PlayerManager playerManager;
	
	public LeftRightSystem(Family.Builder aspect, SceneLoader sceneLoader) {
		super(aspect.get());
		sceneLoader=sceneLoader;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		LeftRightComponent leftRightComp =	leftRightMapper.get(entity);
		TransformComponent transform= transformCm.get(entity);
		DimensionsComponent sprite=spriteCm.get(entity);

		if(leftRightComp.direction== LeftRightComponent.Direction.FRONT){

			float y = transform.y;
			leftRightComp.margin -= leftRightComp.speed;
			y -= leftRightComp.speed;
			transform.y=y;
			if(leftRightComp.margin<0)
				leftRightComp.direction= LeftRightComponent.Direction.LEFT;
		}


		if(leftRightComp.direction== LeftRightComponent.Direction.LEFT) {
			float x = transform.x;
			x -= leftRightComp.speed;
			transform.x=x;

			int randomValue = MathUtils.random(1000);
			Entity e= GenericEntityBuilder.createPhysicsShape(sceneLoader,1, Color.WHITE, Size.makeSize(.25f, .25f), Position.makePosition(transform.x + sprite.width / 2, transform.y + sprite.height / 2),180, BodyDef.BodyType.DynamicBody,.5f);
			if (randomValue < 1) {

				//playerManager.setPlayer(e, Enums.Player.PLAYER.value);

				HealthComponent health=new HealthComponent();
				e.add(health);

				float percent=(health.health/health.maximumHealth)*100;
				//VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
				//textComponent.setScale(.5f, .5f);

				//e.edit().add(textComponent);
			}

			if(transform.x<-.5f)
				leftRightComp.direction= LeftRightComponent.Direction.RIGHT;
		}

		if(leftRightComp.direction== LeftRightComponent.Direction.RIGHT) {
			float x = transform.x;
			x += leftRightComp.speed;
			transform.x=x;

			if(transform.x+sprite.width>5.3f)
				leftRightComp.direction= LeftRightComponent.Direction.LEFT;
		}
	}

	@Override
	public boolean checkProcessing() {
		return !Constants.isPaused();
	}

}

