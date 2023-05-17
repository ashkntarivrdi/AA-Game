package view;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginMenuController {
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    public void enterSignupMenu(MouseEvent mouseEvent) throws Exception {
        new SignupMenu().start(LoginMenu.stage);
    }

    public void submit(MouseEvent mouseEvent) {

    }
}
