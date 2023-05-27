package view.Controllers;

import controller.SignupController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import view.Menus.LoginMenu;

public class SignupMenuController {
    SignupController signupController = new SignupController();
    public TextField username;
    public TextField password;

    public void submit(MouseEvent mouseEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (signupController.createUser(username, password)) {
            case SUCCESS:
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Registration Successful");
                alert.setContentText("User created successfully!");
                alert.show();
                username.clear();
                password.clear();
                break;
            case WEAK_PASSWORD:
                alert.setHeaderText("Registration Failed");
                alert.setContentText("Your password is weak!");
                alert.show();
                break;
            case USERNAME_EXISTS:
                alert.setHeaderText("Registration Failed");
                alert.setContentText("Username already exists!");
                alert.show();
                break;
            case EMPTY_FIELD:
                alert.setHeaderText("Registration Failed");
                alert.setContentText("Username or Password field is empty!");
                alert.show();
                break;
        }
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }
}
