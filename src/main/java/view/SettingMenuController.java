package view;

import controller.SettingController;
import enums.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import model.CurrentGame;

import java.util.Set;

public class SettingMenuController {
    public SettingController settingController = new SettingController();
    public RadioButton easy = new RadioButton();
    public RadioButton medium = new RadioButton();
    public RadioButton hard = new RadioButton();

    public void chooseMap(MouseEvent mouseEvent) {
        //TODO

    }

//    public void muteSound(MouseEvent mouseEvent) {
//        settingController.setMute(mute.isSelected());
//    }

    public void changeBallsCount(MouseEvent mouseEvent) {
        //TODO
    }

//    public void changeTheme(MouseEvent mouseEvent) {
//        settingController.setDarkMode(darkMode.isSelected());
//    }

    public void changeDifficultyRate(MouseEvent mouseEvent) throws Exception{
        Pane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/DifficultyRate.fxml"));
        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

//        switch (SettingController.getDifficultyRate().getName()) {
//            case "easy":
//                easy.setSelected(true);
//                break;
//            case "medium":
//                medium.setSelected(true);
//                break;
//            case "hard":
//                hard.setSelected(true);
//                break;
//        }

        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void changeLanguage(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeControlKeys(MouseEvent mouseEvent) {
        //TODO
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    public void enterSettingMenu(MouseEvent mouseEvent) throws Exception {
        new SettingMenu().start(LoginMenu.stage);
    }

    public void setNewDifficultyRate(MouseEvent mouseEvent) {
//        if(easy.isSelected()) easy.setSelected(true);
//        else if(hard.isSelected()) hard.setSelected(true);
//        else medium.setSelected(true);
//        switch (SettingController.getDifficultyRate().getName()) {
//            case "easy":
//                easy.setSelected(true);
//                break;
//            case "medium":
//                medium.setSelected(true);
//                break;
//            case "hard":
//                hard.setSelected(true);
//                break;
//        }

        if(easy.isSelected()) {
            hard.setSelected(false);
            medium.setSelected(false);
            settingController.setDifficultyRate(Level.EASY);
        }
        else if(hard.isSelected()) {
            easy.setSelected(false);
            medium.setSelected(false);
            settingController.setDifficultyRate(Level.HARD);
        }
        else {
            easy.setSelected(false);
            hard.setSelected(false);
            settingController.setDifficultyRate(Level.MEDIUM);
        }
        System.out.println(CurrentGame.getDifficultyRate());
    }
}
