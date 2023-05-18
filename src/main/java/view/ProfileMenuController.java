package view;

import controller.MainController;
import controller.ProfileController;
import controller.Utils.UserUtils;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Database;
import model.User;

public class ProfileMenuController {
    private final ProfileController profileController = new ProfileController();
    public void changeUsername(MouseEvent mouseEvent) {
    }

    public void changePassword(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        UserUtils.userLogout();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logged out successfully");
        alert.show();
        new LoginMenu().start(LoginMenu.stage);
    }

    public void deleteAccount(MouseEvent mouseEvent) {
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }
}
