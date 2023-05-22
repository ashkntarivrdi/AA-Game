package view.Menus;

import controller.ScoreBoardController;
import controller.SettingController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
    ScoreBoardController scoreBoardController = new ScoreBoardController();
    String level = "medium";
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(ScoreBoard.class.getResource("/FXML/ScoreBoard.fxml"));

        ToggleButton easy = new ToggleButton("easy");
        ToggleButton medium = new ToggleButton("medium");
        ToggleButton hard = new ToggleButton("hard");
        HBox hBox = new HBox(easy, medium, hard);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        borderPane.setRight(hBox);

        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "easy";
                System.out.println("easy");
            }
        });

        medium.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "medium";
            }
        });

        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "hard";
            }
        });

        //TODO: change scoreboard by level doesn't work


        String[] sortedPlayers = scoreBoardController.getHighRanks(level).split(",");
        VBox vBox = new VBox();
        Text text = new Text();
        text.setFill(Color.BLACK);
        Font font = Font.font("serif", FontWeight.BOLD, FontPosture.REGULAR, 18);
        for(int i = sortedPlayers.length, j = 0; i > 0 && j < sortedPlayers.length; i--, j++) {
            text = new Text(sortedPlayers[j]);
            switch (j) {
                case 0:
                    text.setFill(Color.GOLD);
                    break;
                case 1:
                    text.setFill(Color.SILVER);
                    break;
                case 2:
                    text.setFill(Color.SANDYBROWN);
                    break;
            }
            text.setFont(font);
            vBox.getChildren().add(text);
        }
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("ScoreBoard");
        stage.show();
    }


}
