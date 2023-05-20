package model;

import enums.Level;

public class CurrentGame {
    private static Level difficultyRate = Level.MEDIUM;
    public static boolean guestPlayer;
    public static boolean mute = false;
    public static boolean darkMode = false;

    public static Level getDifficultyRate() {
        return difficultyRate;
    }

    public static void setDifficultyRate(Level difficultyRate) {
        CurrentGame.difficultyRate = difficultyRate;
    }

    public static boolean isMute() {
        return mute;
    }

    public static void setMute(boolean mute) {
        CurrentGame.mute = mute;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setDarkMode(boolean darkMode) {
        CurrentGame.darkMode = darkMode;
    }
}
