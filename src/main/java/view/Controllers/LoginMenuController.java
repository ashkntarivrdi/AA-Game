package view.Controllers;

import controller.LoginController;
import controller.Utils.UserUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;
import view.Menus.SignupMenu;

public class LoginMenuController {
    private LoginController loginController = new LoginController();
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    public void enterSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }

    public void submit(MouseEvent mouseEvent) throws Exception {
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (loginController.loginUser(username, password)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Login Successful");
                alert.setContentText("User logged in successfully!");
                alert.show();
                new MainMenu().start(LoginMenu.stage);
                break;
            case USERNAME_DOES_NOT_EXIST:
                alert.setHeaderText("Login Failed");
                alert.setContentText("Username doesn't exist!");
                alert.show();
                break;
            case INCORRECT_PASSWORD:
                alert.setHeaderText("Login Failed");
                alert.setContentText("Password is not correct!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Login Failed");
                alert.setContentText("Username or Password field is empty!");
                alert.show();
                break;
        }
    }

    public void playAsGuest(MouseEvent mouseEvent) throws Exception{
        UserUtils.setGuestPlayerTrue();
        new MainMenu().start(LoginMenu.stage);
    }
}
