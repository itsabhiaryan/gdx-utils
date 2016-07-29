package com.nayragames.o2d.system;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.nayragames.o2d.Constants;
import com.nayragames.o2d.component.Bounds;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;

/**
 * This system updates Bounds component of entity that have any of these component VisSprite,AnimationComponent,ShapeComponent.
 *
 * @author ARYAN */

public class BoundsUpdater extends IteratingSystem {

	private ComponentMapper<DimensionsComponent> dimensionCm= ComponentMapper.getFor(DimensionsComponent.class);
	private ComponentMapper<Bounds> boundsCm= ComponentMapper.getFor(Bounds.class);
	private ComponentMapper<TransformComponent> transformCm= ComponentMapper.getFor(TransformComponent.class);


	private BoundsCalculator calculator = new BoundsCalculator();

	float width,height;

	public BoundsUpdater() {
		super(Family.all( Bounds.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		//System.out.println("Process Entity");

		TransformComponent transform = transformCm.get(entity);
		DimensionsComponent dimensionsComponent=dimensionCm.get(entity);

		width=dimensionsComponent.width;
		height=dimensionsComponent.height;

		Bounds bounds = boundsCm.get(entity);
		calculator.updateBounds(bounds.bounds, transform);

	}

	//Based on libGDX Sprite class
	private class BoundsCalculator {
		private static final int X1 = 0;
		private static final int X2 = 1;
		private static final int X3 = 2;
		private static final int X4 = 3;
		private static final int Y1 = 4;
		private static final int Y2 = 5;
		private static final int Y3 = 6;
		private static final int Y4 = 7;

		private float[] vertices = new float[8];

		//private VisSprite sprite;
		private TransformComponent transform;

		public void updateBounds (Rectangle target, TransformComponent transform) {
			this.transform = transform;

			updateVertices();

			float minx = vertices[X1];
			float miny = vertices[Y1];
			float maxx = vertices[X1];
			float maxy = vertices[Y1];

			minx = minx > vertices[X2] ? vertices[X2] : minx;
			minx = minx > vertices[X3] ? vertices[X3] : minx;
			minx = minx > vertices[X4] ? vertices[X4] : minx;

			maxx = maxx < vertices[X2] ? vertices[X2] : maxx;
			maxx = maxx < vertices[X3] ? vertices[X3] : maxx;
			maxx = maxx < vertices[X4] ? vertices[X4] : maxx;

			miny = miny > vertices[Y2] ? vertices[Y2] : miny;
			miny = miny > vertices[Y3] ? vertices[Y3] : miny;
			miny = miny > vertices[Y4] ? vertices[Y4] : miny;

			maxy = maxy < vertices[Y2] ? vertices[Y2] : maxy;
			maxy = maxy < vertices[Y3] ? vertices[Y3] : maxy;
			maxy = maxy < vertices[Y4] ? vertices[Y4] : maxy;

			target.x = minx;
			target.y = miny;
			target.width = maxx - minx;
			target.height = maxy - miny;
		}

		private void updateVertices () {
			float[] vertices = this.vertices;
			float localX = - transform.originX;
			float localY = -transform.originY;
			float localX2 = localX + width;
			float localY2 = localY + height;
			float worldOriginX = transform.x - localX;
			float worldOriginY = transform.y - localY;
			if (transform.scaleX != 1 || transform.scaleY != 1) {
				localX *= transform.scaleX;
				localY *= transform.scaleY;
				localX2 *= transform.scaleX;
				localY2 *= transform.scaleY;
			}
			if (transform.rotation != 0) {
				final float cos = MathUtils.cosDeg(transform.rotation);
				final float sin = MathUtils.sinDeg(transform.rotation);
				final float localXCos = localX * cos;
				final float localXSin = localX * sin;
				final float localYCos = localY * cos;
				final float localYSin = localY * sin;
				final float localX2Cos = localX2 * cos;
				final float localX2Sin = localX2 * sin;
				final float localY2Cos = localY2 * cos;
				final float localY2Sin = localY2 * sin;

				final float x1 = localXCos - localYSin + worldOriginX;
				final float y1 = localYCos + localXSin + worldOriginY;
				vertices[X1] = x1;
				vertices[Y1] = y1;

				final float x2 = localXCos - localY2Sin + worldOriginX;
				final float y2 = localY2Cos + localXSin + worldOriginY;
				vertices[X2] = x2;
				vertices[Y2] = y2;

				final float x3 = localX2Cos - localY2Sin + worldOriginX;
				final float y3 = localY2Cos + localX2Sin + worldOriginY;
				vertices[X3] = x3;
				vertices[Y3] = y3;

				vertices[X4] = x1 + (x3 - x2);
				vertices[Y4] = y3 - (y2 - y1);
			} else {
				final float x1 = localX + worldOriginX;
				final float y1 = localY + worldOriginY;
				final float x2 = localX2 + worldOriginX;
				final float y2 = localY2 + worldOriginY;

				vertices[X1] = x1;
				vertices[Y1] = y1;

				vertices[X2] = x1;
				vertices[Y2] = y2;

				vertices[X3] = x2;
				vertices[Y3] = y2;

				vertices[X4] = x2;
				vertices[Y4] = y1;
			}
		}
	}

	@Override
	public boolean checkProcessing() {
		return !Constants.isPaused();
	}
}
