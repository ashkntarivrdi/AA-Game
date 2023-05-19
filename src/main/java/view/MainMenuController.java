package view;

import controller.MainController;
import controller.Utils.UserUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;

public class MainMenuController {
    private MainController mainController = new MainController();
    public void startNewGame(MouseEvent mouseEvent) {
        //TODO
    }

    public void playPreviousGame(MouseEvent mouseEvent) {
        //TODO
    }

    public void enterProfileMenu(MouseEvent mouseEvent) throws Exception {
        if (!UserUtils.isGuestPlayer())
            new ProfileMenu().start(LoginMenu.stage);
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Access Denied");
            alert.setContentText("You don't have access to this menu");
            alert.show();
        }
    }

    public void enterScoreBoardMenu(MouseEvent mouseEvent) throws Exception {
        new ScoreBoard().start(LoginMenu.stage);
    }

    public void enterSetting(MouseEvent mouseEvent) throws Exception{
        new SettingMenu().start(LoginMenu.stage);
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Alert alert = UserUtils.logoutConfirmation();
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK) {
                UserUtils.userLogout();
                Alert alert1 = UserUtils.logoutMessage();
                alert1.show();
                try {
                    new LoginMenu().start(LoginMenu.stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
