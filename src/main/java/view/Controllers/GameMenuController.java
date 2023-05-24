package view.Controllers;

import controller.GameController;
import controller.SettingController;
import enums.Phase;
import javafx.scene.input.MouseEvent;
import model.CurrentGame;
import view.Menus.GameMenu;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;

import java.util.Set;

public class GameMenuController {
    GameController gameController = new GameController();
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        //TODO: reset static fields from currentGame
//        GameController.enterPauseMenu();
        GameController.resetEverything();
        new MainMenu().start(LoginMenu.stage);
    }

    public void resumeGame(MouseEvent mouseEvent) {
        GameController.enterPauseMenu(GameMenu.gamePane, GameMenu.pausePane);
    }

    public void showKeyBinds(MouseEvent mouseEvent) {
        //TODO
    }

    public void muteSound(MouseEvent mouseEvent) {
        SettingController.setMute(!SettingController.isMute());
        if (SettingController.isMute()) GameMenu.mediaPlayer.pause();
        else GameMenu.mediaPlayer.play();
    }

    public void restartGame(MouseEvent mouseEvent) throws Exception{
        GameMenu.mediaPlayer.stop();
        new GameMenu().start(LoginMenu.stage);
    }

    public void exitGame(MouseEvent mouseEvent) throws Exception{
        GameMenu.mediaPlayer.stop();
        new MainMenu().start(LoginMenu.stage);
    }
}
