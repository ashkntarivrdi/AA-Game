package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Database;

public class ProfileMenuController {
    public void changeUsername(MouseEvent mouseEvent) {
    }

    public void changePassword(MouseEvent mouseEvent) {
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        Database.setCurrentUser(null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logout successfully");
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
