package controller.Utils;
import javafx.scene.control.Alert;
import model.Database;

import static model.CurrentGame.guestPlayer;

public class UserUtils {
    public static boolean isGuestPlayer() {
        return guestPlayer;
    }

    public static void setGuestPlayerTrue() {
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
}
