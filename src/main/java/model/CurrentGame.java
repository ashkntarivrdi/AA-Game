package model;

import enums.Level;

public class CurrentGame {
    private static Level difficultyRate = Level.MEDIUM;
    public static boolean guestPlayer;

    public static Level getDifficultyRate() {
        return difficultyRate;
    }

    public static void setDifficultyRate(Level difficultyRate) {
        CurrentGame.difficultyRate = difficultyRate;
    }
}
