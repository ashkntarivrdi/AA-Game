package controller;

import Enums.RegistrationMenuMessages;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Database;
import model.User;

public class ProfileController {
    public RegistrationMenuMessages setNewUsername(TextField newUsername) {
        if(newUsername.getText().isEmpty())
            return RegistrationMenuMessages.EMPTY_FIELD;

        if(Database.getUserByUsername(newUsername.getText()) != null)
            return RegistrationMenuMessages.USERNAME_EXISTS;

        System.out.println("current user name: " + Database.getCurrentUser().getName());
        System.out.println("new username: " + newUsername.getText());

        Database.loadUsers();
        Database.getUserByUsername(Database.getCurrentUser().getName()).setName(newUsername.getText());
        Database.getCurrentUser().setName(newUsername.getText());
        Database.saveUsers();

        System.out.println("current user name: " + Database.getCurrentUser().getName());

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

        Database.loadUsers();
        Database.getUserByUsername(Database.getCurrentUser().getName()).setPassword(newPassword.getText());
        Database.getCurrentUser().setPassword(newPassword.getText());
        Database.saveUsers();

        return RegistrationMenuMessages.SUCCESS;
    }
}
