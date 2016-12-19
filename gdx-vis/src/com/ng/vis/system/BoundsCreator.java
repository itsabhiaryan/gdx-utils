package com.ng.vis.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.kotcrab.vis.runtime.component.VisSprite;
import com.ng.gdxutils._GameManager;
import com.ng.vis.component.AnimationComponent;
import com.ng.vis.component.Bounds;
import com.ng.vis.component.ShapeComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 01-08-2016
 *
 * This system create and add Bounds component for entity that have any of these component VisSprite,AnimationComponent,ShapeComponent.
 *
 */

public class BoundsCreator extends BaseEntitySystem {
	private ComponentMapper<Bounds> boundsCm;

	public BoundsCreator() {
		super(Aspect.one(VisSprite.class, AnimationComponent.class, ShapeComponent.class));
	}

	@Override
	public void inserted (IntBag entities) {
		int[] data = entities.getData();
		for (int i = 0; i < entities.size(); i++) {
			int entityId = data[i];
			boundsCm.create(entityId);
		}
	}

	@Override
	protected void processSystem () {

	}

	@Override
	protected boolean checkProcessing() {
		return !_GameManager.isPaused();
	}
}
