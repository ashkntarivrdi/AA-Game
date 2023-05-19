package controller.Utils;
import controller.GameController;
import enums.Avatar;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Database;
import model.User;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static model.CurrentGame.guestPlayer;

public class UserUtils {
    public static boolean isGuestPlayer() {
        return guestPlayer;
    }

    public static void setGuestPlayerTrue() {
        Database.loadUsers();
        guestPlayer = true;
    }
    public static String getCurrentUsername() {
        return Database.getCurrentUser().getName();
    }

    public static int getCurrentUserScore() {
        return Database.getCurrentUser().getScore();
    }

    public static void userLogout() {
        Database.setCurrentUser(null);
        guestPlayer = false;
    }

    public static Alert logoutConfirmation() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to logout?");
        return alert;
    }

    public static Alert logoutMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Logout Successful");
        alert.setContentText("User logged out successfully");
        return alert;
    }

    public static Image getCurrentUserAvatar() throws IOException {
        if(Database.getCurrentUser().getAvatar() != null)
            return Database.getCurrentUser().getAvatar().image;
        String imageUrl = "file:///" + Database.getCurrentUser().getPath().replace('\\', '/');
        return new Image(new URL(imageUrl).openStream());
    }
}
