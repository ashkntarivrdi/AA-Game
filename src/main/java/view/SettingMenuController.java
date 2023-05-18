package view;

import javafx.scene.input.MouseEvent;

import java.lang.module.ModuleDescriptor;

public class SettingMenuController {
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

    public void changeDifficultyRate(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeLanguage(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeControlKeys(MouseEvent mouseEvent) {
        //TODO
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }
}
