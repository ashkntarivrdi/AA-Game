package controller;

import enums.Level;
import model.CurrentGame;
import model.Database;

public class SettingController {
    public void setDifficultyRate(Level level) {
        CurrentGame.setDifficultyRate(level);
    }
}
