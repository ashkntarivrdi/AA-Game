package view.Controllers;

import controller.GameController;
import controller.SettingController;
import enums.Phase;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import model.CurrentGame;
import view.Menus.GameMenu;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;
import view.Menus.MultiplePlayersMenu;

import java.util.Set;

import static view.Menus.GameMenu.mediaPlayer;

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
        Label text = new Label("Shoot: " + CurrentGame.getShootKey() + "\nFreeze: " + CurrentGame.getFreezeKey() +
                "\nMove Right: " + CurrentGame.getRightKey() + "\nMove Left: " + CurrentGame.getLeftKey() +
                "\nPause: " + KeyCode.ESCAPE.getName());
        Font font = Font.font("serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14);
        text.setFont(font);
        text.setTranslateX(20);
        text.setTranslateY(400);

        Popup popup = new Popup();
        popup.getContent().add(text);

        popup.setAutoHide(true);
        if (!popup.isShowing())
            popup.show(LoginMenu.stage);
    }

    public void muteSound(MouseEvent mouseEvent) {
        SettingController.setMute(!SettingController.isMute());
        if (SettingController.isMute()) mediaPlayer.pause();
        else mediaPlayer.play();
    }

    public void restartGame(MouseEvent mouseEvent) throws Exception{
        mediaPlayer.stop();
        if (MultiplePlayersMenu.isMultiplePlayer) {
            new MultiplePlayersMenu().start(LoginMenu.stage);
        }else
            new GameMenu().start(LoginMenu.stage);
    }

    public void exitGame(MouseEvent mouseEvent) throws Exception{
        mediaPlayer.stop();
        new MainMenu().start(LoginMenu.stage);
    }

    public void nextSong(MouseEvent mouseEvent) {
        GameMenu.currentMediaIndex = (GameMenu.currentMediaIndex + 1) % GameMenu.mediaList.size();
        playMedia(GameMenu.mediaList.get(GameMenu.currentMediaIndex));
    }

    public void playMedia(Media media) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
    }
}
