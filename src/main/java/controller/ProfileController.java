package controller;

import Enums.RegistrationMenuMessages;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import static model.Database.*;

public class ProfileController {
    public RegistrationMenuMessages setNewUsername(TextField newUsername) {
        if(newUsername.getText().isEmpty())
            return RegistrationMenuMessages.EMPTY_FIELD;

        if(getUserByUsername(newUsername.getText()) != null)
            return RegistrationMenuMessages.USERNAME_EXISTS;

        loadUsers();
        getUserByUsername(getCurrentUser().getName()).setName(newUsername.getText());
        getCurrentUser().setName(newUsername.getText());
        saveUsers();

        return RegistrationMenuMessages.SUCCESS;
    }

    public RegistrationMenuMessages setNewPassword(PasswordField newPassword) {
        if(newPassword.getText().isEmpty())
            return RegistrationMenuMessages.EMPTY_FIELD;

        if(newPassword.getText().length() < 6 ||
                !newPassword.getText().matches(".*[0-9].*") ||
                !newPassword.getText().matches(".*[A-Z].*") ||
                !newPassword.getText().matches(".*[a-z].*"))
            return RegistrationMenuMessages.WEAK_PASSWORD;

        loadUsers();
        getUserByUsername(getCurrentUser().getName()).setPassword(newPassword.getText());
        getCurrentUser().setPassword(newPassword.getText());
        saveUsers();

        return RegistrationMenuMessages.SUCCESS;
    }

    public void removeCurrentUser() {
        loadUsers();
        removeUser(getUserByUsername(getCurrentUser().getName()));
    }
}
