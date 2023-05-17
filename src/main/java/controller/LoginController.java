package controller;

import Enums.RegistrationMenuMessages;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Database;

import static model.Database.getUserByUsername;

public class LoginController {
    public RegistrationMenuMessages loginUser(TextField username, PasswordField password) {
        if(username.getText().isEmpty() || password.getText().isEmpty())
            return RegistrationMenuMessages.EMPTY_FIELD;
        Database.loadUsers();
        if(getUserByUsername(username.getText()) == null)
            return RegistrationMenuMessages.USERNAME_DOES_NOT_EXIST;

        if(!getUserByUsername(username.getText()).getPassword().equals(password.getText()))
            return RegistrationMenuMessages.INCORRECT_PASSWORD;

        Database.setCurrentUser(getUserByUsername(username.getText()));
        return RegistrationMenuMessages.SUCCESS;
    }
}
