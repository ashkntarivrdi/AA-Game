package controller.Utils;
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
}
