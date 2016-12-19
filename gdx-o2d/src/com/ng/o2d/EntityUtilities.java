package com.ng.o2d;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.ng.gdxutils.b2d.BodyEditorLoader;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-12-2015.
 *
 * Utilities method for ECS.
 */
public class EntityUtilities {

    private static final String TAG = "[" + EntityUtilities.class.getSimpleName() + "]";

    public static void printAllComponent(Entity entity) {

        ImmutableArray<Component> components=entity.getComponents();
        for(Component component:components)
            System.out.println(component);
    }

    public static BodyEditorLoader createLoader(String filePath){
        //loader= EntityUtilities.createLoader("json/gear.json");
        return new BodyEditorLoader(Gdx.files.internal("json/gear.json"));
    }
}
