package com.nayragames.o2d;


import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.nayragames.gdxutils.b2d.BodyEditorLoader;

/**
 *
 *
 * Utilities method for ECS.
 *
 * Created by ARYAN on 02-12-2015.
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
