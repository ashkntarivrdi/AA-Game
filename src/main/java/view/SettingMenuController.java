package view;

import controller.SettingController;
import enums.Level;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.CurrentGame;

import javax.print.attribute.SetOfIntegerSyntax;
import java.awt.event.ActionEvent;
import java.util.Set;

public class SettingMenuController {
    public SettingController settingController = new SettingController();


    public void chooseMap(MouseEvent mouseEvent) {
        //TODO

    }

    public void changeBallsCount(MouseEvent mouseEvent) {
        //TODO
    }

    public void changeDifficultyRate(MouseEvent mouseEvent) throws Exception{
        BorderPane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/DifficultyRate.fxml"));

        ToggleButton easy = new ToggleButton("easy");
        ToggleButton medium = new ToggleButton("medium");
        ToggleButton hard = new ToggleButton("hard");
        easy.setMaxWidth(100);
        medium.setMaxWidth(100);
        hard.setMaxWidth(100);

        setSelectedButtons(easy, medium, hard);

        easy.setOnAction(response -> {
            SettingController.setDifficultyRate(Level.EASY);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        medium.setOnAction(response -> {
            SettingController.setDifficultyRate(Level.MEDIUM);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        hard.setOnAction(response -> {
            SettingController.setDifficultyRate(Level.HARD);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        TextField textField = getCurrentDifficultyRate();

        VBox vBox = new VBox(easy, medium, hard, textField);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        Button back = new Button("Back");
        back.setOnAction(response -> {
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        back.setMaxWidth(150);
        VBox vBox1 = new VBox(back);
        vBox1.setAlignment(Pos.CENTER);

        pane.setCenter(vBox);
        pane.setBottom(vBox1);

        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public TextField getCurrentDifficultyRate() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);
        textField.setOpacity(0.8);
        textField.setText("Current Difficulty Rate : " + SettingController.getDifficultyRate());
        return textField;
    }

    public void setSelectedButtons(ToggleButton easy, ToggleButton medium, ToggleButton hard) {
        switch (SettingController.getDifficultyRate()) {
            case EASY:
                easy.setSelected(true);
                break;
            case MEDIUM:
                medium.setSelected(true);
                break;
            case HARD:
                hard.setSelected(true);
                break;
        }
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

    private void enterSettingMenu(javafx.event.ActionEvent actionEvent) throws Exception{
        new SettingMenu().start(LoginMenu.stage);
    }
}
