package com.ng.o2d.shaperenderer;

import box2dLight.RayHandler;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import com.ng.o2d.component.ShapeComponent;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.PolygonComponent;
import com.uwsoft.editor.renderer.data.MainItemVO;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.factory.component.ComponentFactory;
import com.uwsoft.editor.renderer.resources.IResourceRetriever;
import com.uwsoft.editor.renderer.utils.ComponentRetriever;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 27-01-2016.
 *
 */
public class ShapeComponentFactory extends ComponentFactory {

    ShapeComponent shapeComponent;

    public ShapeComponentFactory(RayHandler rayHandler, World world, IResourceRetriever rm) {
        super(rayHandler, world, rm);
    }

    @Override
    public void createComponents(Entity root, Entity entity, MainItemVO vo) {
        shapeComponent=createShapeComponent(entity);
        createCommonComponents( entity, vo, ShapeRendererType.SHAPE_RENDERER);
        createParentNodeComponent(root, entity);
        createNodeComponent(root, entity);
        //updatePolygons(entity);
    }

    private void updatePolygons(Entity entity) {
        ShapeComponent shapeComponent = ComponentRetriever.get(entity, ShapeComponent.class);
        DimensionsComponent dimensionsComponent = ComponentRetriever.get(entity, DimensionsComponent.class);

        ProjectInfoVO projectInfoVO = rm.getProjectVO();

        PolygonComponent polygonComponent = ComponentRetriever.get(entity, PolygonComponent.class);
        //if(shapeComponent.isPolygon && polygonComponent != null && polygonComponent.vertices != null) {
          //  shapeComponent.setPolygonSprite(polygonComponent, projectInfoVO.pixelToWorld);
           // dimensionsComponent.setPolygon(polygonComponent);
        //}
    }

    @Override
    protected DimensionsComponent createDimensionsComponent(Entity entity, MainItemVO vo) {

        DimensionsComponent component = new DimensionsComponent();
        component.width=10;
        component.height=10;

        entity.add(component);

        return component;
    }

    protected ShapeComponent createShapeComponent(Entity entity) {

        ShapeComponent shapeComponent=new ShapeComponent();
        entity.add(shapeComponent);

        return shapeComponent;
    }
}
