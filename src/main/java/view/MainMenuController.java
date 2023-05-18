package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Database;

import java.lang.module.ModuleDescriptor;

public class MainMenuController {
    public void startNewGame(MouseEvent mouseEvent) {
    }

    public void playPreviousGame(MouseEvent mouseEvent) {
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        new ProfileMenu().start(LoginMenu.stage);
    }

    public void enterScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
        new ScoreBoard().start(LoginMenu.stage);
    }

    public void enterSetting(MouseEvent mouseEvent) throws Exception{
        new SettingMenu().start(LoginMenu.stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Database.setCurrentUser(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logout successfully");
        alert.show();
        new LoginMenu().start(LoginMenu.stage);
    }
}
