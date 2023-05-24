package controller;

import enums.Level;
import model.CurrentGame;
import model.Database;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

public class SettingController {
    public static void setDifficultyRate(Level level) {
        CurrentGame.setDifficultyRate(level);
//        System.out.println(CurrentGame.getDifficultyRate());
    }

    public static void setDarkMode(boolean darkMode) {
        CurrentGame.setDarkMode(darkMode);
        System.out.println(CurrentGame.isDarkMode());
    }

    public static void setMute(boolean mute) {
        CurrentGame.setMute(mute);
        System.out.println(CurrentGame.isMute());
    }

    public static boolean isMute() {
        return CurrentGame.isMute();
    }

    public static boolean isDarkMode() {
        return CurrentGame.isDarkMode();
    }

    public static Level getDifficultyRate() {
        return CurrentGame.getDifficultyRate();
    }

    public static int getBallsCount() {
        return CurrentGame.getNumberOfBalls();
    }

    public static void setBallsCount(int numberOfBalls) {
        CurrentGame.setNumberOfBalls(numberOfBalls);
    }

    public static void setDefaultBallsCount(int defaultBallsCount) {
        CurrentGame.setDefaultBallsCount(defaultBallsCount);
    }

    public static int getDefaultBallsCount() {
        return CurrentGame.getDefaultBallsCount();
    }
    //TODO: change number of balls
}
