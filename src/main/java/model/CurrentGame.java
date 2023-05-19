package model;

import enums.Level;

public class CurrentGame {
    private Level difficultyRate;
    public static boolean guestPlayer;

    public Level getDifficultyRate() {
        return difficultyRate;
    }

    public void setDifficultyRate(Level difficultyRate) {
        this.difficultyRate = difficultyRate;
    }
}
