package view;

import controller.SettingController;
import enums.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.CurrentGame;

public class SettingMenuController {
    public SettingController settingController = new SettingController();
    public RadioButton easy;
    public RadioButton medium;
    public RadioButton hard;
    public RadioButton sound;
    public RadioButton darkMode;

    public void chooseMap(MouseEvent mouseEvent) {
        //TODO
    }

    public void muteSound(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeBallsCount(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeTheme(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeDifficultyRate(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/DifficultyRate.fxml"));


        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void changeLanguage(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeControlKeys(MouseEvent mouseEvent) {
        //TODO
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void enterSettingMenu(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(LoginMenu.stage);
    }

    public void setNewDifficultyRate(MouseEvent mouseEvent) {
        if(easy.isSelected()) {
            hard.setSelected(false);
            medium.setSelected(false);
            settingController.setDifficultyRate(Level.EASY);
        }
        else if(hard.isSelected()) {
            easy.setSelected(false);
            medium.setSelected(false);
            settingController.setDifficultyRate(Level.HARD);
        }
        else {
            easy.setSelected(false);
            hard.setSelected(false);
            settingController.setDifficultyRate(Level.MEDIUM);
        }
        System.out.println(CurrentGame.getDifficultyRate());
    }
}
