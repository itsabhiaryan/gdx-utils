package com.ng.vis;

import com.artemis.Component;
import com.artemis.Entity;
import com.artemis.utils.Bag;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 02-12-2015.
 *
 * Utilities method for ECS.
 *
 */
public class EntityUtilities {

    private static final String TAG = EntityUtilities.class.getSimpleName();

    public static void printAllComponent(Entity entity) {

        Bag<Component> components=new Bag<Component>();
	    entity.getComponents(components);

        for(Component component:components)
            System.out.println(component);
    }


}
