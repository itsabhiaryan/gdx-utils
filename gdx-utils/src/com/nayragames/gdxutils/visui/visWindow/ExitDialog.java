package com.nayragames.gdxutils.visui.visWindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.util.dialog.ConfirmDialogListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 12/6/2015.
 */
public class ExitDialog {

    private static final String TAG = "[" + ExitDialog.class.getSimpleName() + "]";
    private static Dialogs.ConfirmDialog exitDialog;
    private static String buttonCaption[]=new String[]{"YES", "RATE", "NO"};
    private static String title="Exit ?";
    private static String text="Do you really want to exit?";

    private enum ExitReturn{
       YES,RATE,NO
    }

    public static void showExitDialog(Stage stage, BitmapFont bitmapFont) {

        Label.LabelStyle labelStyle= VisUI.getSkin().get(Label.LabelStyle.class);
        labelStyle.font= bitmapFont;

        Window.WindowStyle windowStyle= VisUI.getSkin().get(Window.WindowStyle.class);
        windowStyle.titleFont=bitmapFont;

        exitDialog = Dialogs.showConfirmDialog(stage, title, text,
                buttonCaption, new Integer[]{ExitReturn.YES.ordinal(), ExitReturn.RATE.ordinal(), ExitReturn.NO.ordinal()},
                new ConfirmDialogListener<Integer>() {
                    @Override
                    public void result(Integer result) {
                        if (result == ExitReturn.YES.ordinal()) {
                            //GameManager.gameExit();
                        }else
                        if (result == ExitReturn.RATE.ordinal()) {
                            //game.rateURL();
                        }else
                        if (result == ExitReturn.NO.ordinal()) {
                            exitDialog.closeOnEscape();
                        }
                    }
                });

        float width= Gdx.graphics.getWidth();
        float height=Gdx.graphics.getHeight();
        exitDialog.setSize(width*.65f,height*.35f);
        exitDialog.setRound(true);
        exitDialog.addCloseButton();
        exitDialog.centerWindow();
        exitDialog.setMovable(false);

        //exitDialog.getTitleTable().setSkin(VisUI.getSkin());
        //exitDialog.setSize(stage.getWidth()*.4f,stage.getHeight()*.2f);
        //exitDialog.getTitleTable().setBackground("button-blue");
        //exitDialog.getTitleTable().setBackground("border-error");

        //exitDialog.setStyle(VisUI.getSkin().get("noborder", Window.WindowStyle.class));
        //exitDialog.getStyle().stageBackground=VisUI.getSkin().getDrawable("button-blue");
        //exitDialog.getStyle().titleFontColor= Color.RED ;

        //exitDialog.setBackground("button-blue");
        //exitDialog.setBackground("button-blue");
        //exitDialog.
        //exitDialog.setModal(true);

        // VisTextButton.VisLabel visTextButtonStyle = VisUI.getSkin().get(VisTextButton.VisTextButtonStyle.class);
        // visTextButtonStyle.font = Assets.bitmapFontAsset.uiFont;
    }
}
