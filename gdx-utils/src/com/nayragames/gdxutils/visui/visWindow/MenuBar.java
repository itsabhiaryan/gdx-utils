package com.nayragames.gdxutils.visui.visWindow;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.PopupMenu;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 14-01-2016.
 */
public class MenuBar {

    public static com.kotcrab.vis.ui.widget.MenuBar menuBar;
    static String menuName[] = new String[]{"Games", "Employee", "Help"};
    static String idName[] = new String[]{"sds", "sdsds", "sdsd", "sdsd"};
    static String games[][] = new String[][]{{"Bridge Stick Hero", "Ssds", "sds"},
            {"TG", "sdsd", "sds"},
            {"sdsds", "Resdrosdsf"},
            {"Motu Psdsdatlu"}
    };

    static String employee[] = new String[]{"Abhishek Aryan", "Susdsdraj Yadav", "Ssdurasdsj Kumar", "Amidsdt Singh",
            "Irfdsan Kurasi"};

    static String helpContent[] = new String[]{"Contact", "About"};




    public static com.kotcrab.vis.ui.widget.MenuBar getMenuBar() {

        menuBar = new com.kotcrab.vis.ui.widget.MenuBar();
        return menuBar;
    }

    public static void addMenuBar(Table table) {

        com.kotcrab.vis.ui.widget.MenuBar menuBar = getMenuBar();
        table.add(menuBar.getTable()).expandX().fillX().row();
        //table.add().expand().fill();
    }

    public static void createMenu() {

        Menu menu[] = new Menu[menuName.length];

        for (int i = 0; i < menu.length; i++)
            menu[i] = new Menu(menuName[i], VisUI.getSkin().get("noborder", Menu.MenuStyle.class));

        MenuItem id[] = new MenuItem[idName.length];

        for (int i = 0; i < id.length; i++) {
            id[i] = new MenuItem(idName[i]);
            id[i].setShortcut(idName[i].substring(0, 1));
            id[i].setSubMenu(createGameList(i));
            menu[0].addItem(id[i]);
        }

        for (int i = 0; i < employee.length; i++)
            menu[1].addItem(new MenuItem(employee[i]));

        for (int i = 0; i < helpContent.length; i++)
            menu[2].addItem(new MenuItem(helpContent[i]));

        for (int i = 0; i < menu.length; i++)
            menuBar.addMenu(menu[i]);
    }


    public static PopupMenu createGameList(int id) {

        PopupMenu.PopupMenuStyle style = new PopupMenu.PopupMenuStyle(VisUI.getSkin().getDrawable("button"), VisUI.getSkin().getDrawable("border-error"));

        //PopupMenu popupMenu=new PopupMenu("noborder");
        PopupMenu popupMenu = new PopupMenu(style);

        for (int i = 0; i < games[id].length; i++)
            popupMenu.addItem(new MenuItem(games[id][i]));

        return popupMenu;
    }


}
