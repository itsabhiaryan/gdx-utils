
package com.nayragames.vis.system;

import com.artemis.Aspect.Builder;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.managers.PlayerManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.kotcrab.vis.runtime.component.Transform;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.kotcrab.vis.runtime.component.VisText;
import com.nayragames.gdxutils.Position;
import com.nayragames.gdxutils.Size;
import com.nayragames.vis.Constants;
import com.nayragames.vis.Enums;
import com.nayragames.vis.GenericEntityBuilder;
import com.nayragames.vis.component.HealthComponent;
import com.nayragames.vis.component.LeftRightComponent;

/**
 * System that move entity from left to right and vice versa.
 *
 * Created by ARYAN on 14-01-2016.
 */

public class LeftRightSystem extends EntityProcessingSystem {

	private static final String TAG = "[" + LeftRightSystem.class.getSimpleName() + "]";
	ComponentMapper<LeftRightComponent> leftRightMapper;
	ComponentMapper<VisSprite> spriteCm;
    ComponentMapper<Transform> transformCm;

	GroupManager groupManager;
	PlayerManager playerManager;
	
	public LeftRightSystem(Builder aspect) {
		super(aspect);
	}

	@Override
	protected void process(Entity e) {
		
		LeftRightComponent leftRightComp =	leftRightMapper.get(e);
		Transform transform= transformCm.get(e);
        VisSprite sprite=spriteCm.get(e);

		if(leftRightComp.direction== LeftRightComponent.Direction.FRONT){

			float y = transform.getY();
			leftRightComp.margin -= leftRightComp.speed;
			y -= leftRightComp.speed;
			transform.setY(y);
			if(leftRightComp.margin<0)
				leftRightComp.direction= LeftRightComponent.Direction.LEFT;
		}


		if(leftRightComp.direction== LeftRightComponent.Direction.LEFT) {
			float x = transform.getX();
			x -= leftRightComp.speed;
			transform.setX(x);

			int randomValue = MathUtils.random(1000);
            Entity entity= GenericEntityBuilder.createPhysicsShape(world,1, Color.WHITE, Size.makeSize(.25f, .25f), Position.makePosition(transform.getX() + sprite.getWidth() / 2, transform.getY() + sprite.getHeight() / 2),180, BodyDef.BodyType.DynamicBody,.5f);
            if (randomValue < 1) {

				playerManager.setPlayer(entity, Enums.Player.PLAYER.value);

				HealthComponent health=new HealthComponent();
				entity.edit().add(health);

				float percent=(health.health/health.maximumHealth)*100;
				//VisText textComponent=new VisText(Assets.bitmapFontAsset.bitmapFont,String.valueOf((int)percent));
				//textComponent.setScale(.5f, .5f);

				//entity.edit().add(textComponent);
			}

			if(transform.getX()<-.5f)
				leftRightComp.direction= LeftRightComponent.Direction.RIGHT;
		}

		if(leftRightComp.direction== LeftRightComponent.Direction.RIGHT) {
			float x = transform.getX();
			x += leftRightComp.speed;
			transform.setX(x);

			if(transform.getX()+sprite.getWidth()>5.3f)
				leftRightComp.direction= LeftRightComponent.Direction.LEFT;
		}
	}

	@Override
	protected boolean checkProcessing() {
		return !Constants.isPaused();
	}
}

