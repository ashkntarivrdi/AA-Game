package view.Menus;

import controller.SettingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.CurrentGame;

public class GameResult extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(GameResult.class.getResource("/FXML/GameResult.fxml"));
        Scene scene = new Scene(pane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Game Result");
        stage.show();
    }

    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        //TODO: reset static fields
//        CurrentGame.resetNumberOfBallsInEachPhase();
        new MainMenu().start(LoginMenu.stage);
    }
}
