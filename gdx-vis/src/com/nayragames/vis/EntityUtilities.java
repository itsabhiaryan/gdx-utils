package com.nayragames.vis;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;
import com.badlogic.gdx.Gdx;
import com.nayragames.gdxutils.b2d.BodyEditorLoader;

/**
 * Utilities method for ECS.
 *
 * Created by ARYAN on 02-12-2015.
 */
public class EntityUtilities {

    private static final String TAG = "[" + EntityUtilities.class.getSimpleName() + "]";

    public static void printAllComponent(Entity entity) {

        Bag<Component> components=new Bag<Component>();
	    entity.getComponents(components);

        for(Component component:components)
            System.out.println(component);
    }

    public static BodyEditorLoader createLoader(String filePath){
        //loader= EntityUtilities.createLoader("json/gear.json");
        return new BodyEditorLoader(Gdx.files.internal("json/gear.json"));
    }
}
