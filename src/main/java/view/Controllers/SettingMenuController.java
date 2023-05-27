package view.Controllers;

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
import javafx.scene.layout.VBox;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;
import view.Menus.ProfileMenu;
import view.Menus.SettingMenu;

public class SettingMenuController {
    public SettingController settingController = new SettingController();


    public void chooseMap(MouseEvent mouseEvent) throws Exception{
        BorderPane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/DefaultMap.fxml"));

        RadioButton five = new RadioButton("5");
        RadioButton six = new RadioButton("6");
        RadioButton seven = new RadioButton("7");
        five.setMaxWidth(100);
        six.setMaxWidth(100);
        seven.setMaxWidth(100);

        setDefaultBallsCountSelectedButtons(five, six, seven);

        five.setOnAction(response -> {
            SettingController.setDefaultBallsCount(5);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        six.setOnAction(response -> {
            SettingController.setDefaultBallsCount(6);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        seven.setOnAction(response -> {
            SettingController.setDefaultBallsCount(7);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        TextField textField = getCurrentDefaultBallsCount();

        VBox vBox = new VBox(five, six, seven, textField);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        pane.setCenter(vBox);
        pane.setBottom(backButton());

        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public void changeBallsCount(MouseEvent mouseEvent) throws Exception{
        BorderPane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/BallsCount.fxml"));

        RadioButton sixteen = new RadioButton("16");
        RadioButton twenty = new RadioButton("20");
        RadioButton twentyFour = new RadioButton("24");
        sixteen.setMaxWidth(100);
        twenty.setMaxWidth(100);
        twentyFour.setMaxWidth(100);

        setBallsCountSelectedButtons(sixteen, twenty, twentyFour);

        sixteen.setOnAction(response -> {
            SettingController.setBallsCount(16);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        twenty.setOnAction(response -> {
            SettingController.setBallsCount(20);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        twentyFour.setOnAction(response -> {
            SettingController.setBallsCount(24);
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        TextField textField = getCurrentBallsCount();

        VBox vBox = new VBox(sixteen, twenty, twentyFour, textField);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        pane.setCenter(vBox);
        pane.setBottom(backButton());

        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();

    }

    public void changeDifficultyRate(MouseEvent mouseEvent) throws Exception{
        BorderPane pane = FXMLLoader.load(ProfileMenu.class.getResource("/FXML/DifficultyRate.fxml"));

        ToggleButton easy = new ToggleButton("easy");
        ToggleButton medium = new ToggleButton("medium");
        ToggleButton hard = new ToggleButton("hard");
        easy.setMaxWidth(100);
        medium.setMaxWidth(100);
        hard.setMaxWidth(100);

        setDifficultyRateSelectedButtons(easy, medium, hard);

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

        pane.setCenter(vBox);
        pane.setBottom(backButton());

        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        LoginMenu.stage.setScene(scene);
        LoginMenu.stage.show();
    }

    public VBox backButton() {
        Button back = new Button("Back");
        back.setOnAction(response -> {
            try {
                enterSettingMenu(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        back.setMaxWidth(150);
        VBox vBox = new VBox(back);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public TextField getCurrentDifficultyRate() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);
        textField.setOpacity(0.8);
        textField.setText("Current Difficulty Rate : " + SettingController.getDifficultyRate());
        return textField;
    }

    public TextField getCurrentBallsCount() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);
        textField.setOpacity(0.8);
        textField.setText("Current Balls Count : " + SettingController.getBallsCount());
        return textField;
    }

    public TextField getCurrentDefaultBallsCount() {
        TextField textField = new TextField();
        textField.setMaxWidth(200);
        textField.setAlignment(Pos.CENTER);
        textField.setOpacity(0.8);
        textField.setText("Current Default Balls Count : " + SettingController.getDefaultBallsCount());
        return textField;
    }

    public void setDifficultyRateSelectedButtons(ToggleButton easy, ToggleButton medium, ToggleButton hard) {
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

    public void setBallsCountSelectedButtons(RadioButton sixteen, RadioButton twenty, RadioButton twentyFour) {
        switch (SettingController.getBallsCount()) {
            case 16:
                sixteen.setSelected(true);
                break;
            case 20:
                twenty.setSelected(true);
                break;
            case 24:
                twentyFour.setSelected(true);
                break;
        }
    }

    public void setDefaultBallsCountSelectedButtons(RadioButton five, RadioButton six, RadioButton seven) {
        switch (SettingController.getDefaultBallsCount()) {
            case 5:
                five.setSelected(true);
                break;
            case 6:
                six.setSelected(true);
                break;
            case 7:
                seven.setSelected(true);
                break;
        }
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(LoginMenu.stage);
    }

    private void enterSettingMenu(javafx.event.ActionEvent actionEvent) throws Exception{
        new SettingMenu().start(LoginMenu.stage);
    }
}
