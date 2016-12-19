package com.ng.gdxutils.visui.visWindow;

import com.badlogic.gdx.Gdx;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.*;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 11/28/2015.
 */
public class Login extends VisWindow {

    public Login() {
        super("Log In", false);

        TableUtils.setSpacingDefaults(this);
        defaults().padTop(5);
        defaults().padRight(1);
        defaults().padLeft(1);
        columnDefaults(0).left();

        VisTextButton cancelButton=new VisTextButton("Cancel");
        VisTextButton acceptButton=new VisTextButton("Accept");

        VisTextField firstName=new VisTextField();
        VisTextField lastName=new VisTextField();
        VisTextField age=new VisTextField();

        VisTable buttonTable=new VisTable(true);
        buttonTable.add(cancelButton);
        buttonTable.add(acceptButton);

        add(new VisLabel("First Name"));
        add(firstName).expand().fill();
        row();
        add(new VisLabel("Last Name"));
        add(lastName).expand().fill();
        row();
        add(buttonTable).fill().expand().colspan(2).padBottom(3);

        pack();
        setSize(getWidth(),getHeight());
        setPosition(Gdx.graphics.getWidth()/2-getWidth()/2,2* Gdx.graphics.getHeight()/3-getHeight()/2);

        setMovable(false);

        //addCloseButton();


    }
}
