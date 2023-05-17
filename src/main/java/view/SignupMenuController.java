package view;

import controller.SignupController;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SignupMenuController {
    SignupController signupController = new SignupController();
    public TextField username;
    public TextField password;

    public void submit(MouseEvent mouseEvent) throws Exception {
        switch (signupController.createUser(username, password)) {
            case SUCCESS:
                new MainMenu().start(LoginMenu.stage);
                break;
            case WEAK_PASSWORD:
                //TODO
                break;
            case USERNAME_EXISTS:
                //TODO
                break;
        }
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }
}
