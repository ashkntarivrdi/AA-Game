package model;

import enums.Level;
import enums.Phase;

import java.util.ArrayList;

public class CurrentGame {
    private static Level difficultyRate = Level.EASY;
    private static Phase phase = Phase.ONE;
    private static ArrayList<Ball> balls = new ArrayList<>();
    private static String shootKey = "Space";
    private static String freezeKey = "Tab";
    private static String rightKey = "Right";
    private static String leftKey = "Left";
    private static int numberOfBalls = 20;
    private static int defaultBallsCount = 5;
    public static boolean guestPlayer;
    public static boolean mute = false;
    public static boolean darkMode = false;

    public static int getDefaultBallsCount() {
        return defaultBallsCount;
    }

    public static void setDefaultBallsCount(int defaultBallsCount) {
        CurrentGame.defaultBallsCount = defaultBallsCount;
    }

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

    public static Phase getPhase() {
        return phase;
    }

    public static void setPhase(Phase phase) {
        CurrentGame.phase = phase;
    }

    public static ArrayList<Ball> getBalls() {
        return balls;
    }

    public static void setBalls(ArrayList<Ball> balls) {
        CurrentGame.balls = balls;
    }

    public static String getShootKey() {
        return shootKey;
    }


    public static String getFreezeKey() {
        return freezeKey;
    }


    public static String getRightKey() {
        return rightKey;
    }


    public static String getLeftKey() {
        return leftKey;
    }


    public static int getNumberOfBalls() {
        return numberOfBalls;
    }

    public static void setNumberOfBalls(int numberOfBalls) {
        CurrentGame.numberOfBalls = numberOfBalls;
    }

    public static void resetBalls() {
        balls.clear();
    }
}
