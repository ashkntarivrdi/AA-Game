package view;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class SignupMenuController {
    public TextField username;
    public TextField password;

    public void submit(MouseEvent mouseEvent) {
    }

    public void enterLoginMenu(MouseEvent mouseEvent) throws Exception {
        new LoginMenu().start(LoginMenu.stage);
    }
}
