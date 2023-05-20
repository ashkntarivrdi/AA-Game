package controller;

import enums.Level;
import model.CurrentGame;
import model.Database;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;

public class SettingController {
    public void setDifficultyRate(Level level) {
        CurrentGame.setDifficultyRate(level);
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
}
