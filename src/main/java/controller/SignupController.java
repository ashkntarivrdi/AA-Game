package controller;

import enums.RegistrationMenuMessages;
import javafx.scene.control.TextField;
import model.Database;
import model.User;

public class SignupController {
    public RegistrationMenuMessages createUser(TextField username, TextField password) {
        if(username.getText().isEmpty() || password.getText().isEmpty())
            return RegistrationMenuMessages.EMPTY_FIELD;

        Database.loadUsers();
        if(Database.getUserByUsername(username.getText()) != null)
            return RegistrationMenuMessages.USERNAME_EXISTS;

        if(password.getText().length() < 6 ||
                !password.getText().matches(".*[0-9].*") ||
                !password.getText().matches(".*[A-Z].*") ||
                !password.getText().matches(".*[a-z].*"))
            return RegistrationMenuMessages.WEAK_PASSWORD;

        Database.addUsers(new User(username.getText(), password.getText()));
        return RegistrationMenuMessages.SUCCESS;
    }
}
