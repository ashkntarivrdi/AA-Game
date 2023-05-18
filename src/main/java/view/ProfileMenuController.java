package view;

import controller.ProfileController;
import controller.Utils.UserUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.Database;

public class ProfileMenuController {
    private final ProfileController profileController = new ProfileController();
    public TextField newUsername;
    public PasswordField newPassword;

    public void changeUsername(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChangeUsername.fxml"));
        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void changePassword(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/ChangePassword.fxml"));
        Scene scene = new Scene(pane);
        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void logout(MouseEvent mouseEvent) throws Exception {
        UserUtils.userLogout();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logged out successfully");
        alert.show();
        new LoginMenu().start(LoginMenu.stage);
    }

    public void deleteAccount(MouseEvent mouseEvent) throws Exception{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to delete your account?");
        alert.showAndWait().ifPresent(response -> {
            if(response == ButtonType.OK)
                profileController.removeCurrentUser();
        });
        new LoginMenu().start(LoginMenu.stage);
    }

    public void chooseAvatar(MouseEvent mouseEvent) {
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void setNewUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (profileController.setNewUsername(newUsername)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful");
                alert.setContentText("Username changed successfully!");
                alert.show();
                newUsername.clear();
                break;
            case USERNAME_EXISTS:
                alert.setHeaderText("Fail");
                alert.setContentText("Username already exists!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Fail");
                alert.setContentText("Username field is empty!");
                alert.show();
                break;
        }
    }

    public void setNewPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (profileController.setNewPassword(newPassword)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful");
                alert.setContentText("Password changed successfully!");
                alert.show();
                newPassword.clear();
                break;
            case WEAK_PASSWORD:
                alert.setHeaderText("Fail");
                alert.setContentText("Your password is weak!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Fail");
                alert.setContentText("Password field is empty!");
                alert.show();
                break;
        }
    }
}
