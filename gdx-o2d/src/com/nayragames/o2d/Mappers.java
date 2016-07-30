package com.nayragames.o2d;

import com.badlogic.ashley.core.ComponentMapper;
import com.nayragames.o2d.component.MovementComponent;
import com.uwsoft.editor.renderer.components.DimensionsComponent;
import com.uwsoft.editor.renderer.components.TransformComponent;
import com.uwsoft.editor.renderer.components.ZIndexComponent;
import com.uwsoft.editor.renderer.components.particle.ParticleComponent;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 25-01-2016.
 */
public class Mappers {

    public static ComponentMapper<TransformComponent> tCM= ComponentMapper.getFor(TransformComponent.class);
    public static ComponentMapper<DimensionsComponent> dCM= ComponentMapper.getFor(DimensionsComponent.class);
    public static ComponentMapper<MovementComponent> mCM=ComponentMapper.getFor(MovementComponent.class);
    public static ComponentMapper<ParticleComponent> pCM= ComponentMapper.getFor(ParticleComponent.class);
    public static ComponentMapper<ZIndexComponent> zCm= ComponentMapper.getFor(ZIndexComponent.class);


}
