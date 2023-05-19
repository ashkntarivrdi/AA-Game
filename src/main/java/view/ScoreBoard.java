package view;

import controller.ScoreBoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoreBoard extends Application {
    ScoreBoardController scoreBoardController = new ScoreBoardController();
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = FXMLLoader.load(ScoreBoard.class.getResource("/FXML/ScoreBoard.fxml"));

        String[] sortedPlayers = scoreBoardController.getHighRanks().split(",");
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
        stage.setScene(scene);
        stage.setTitle("ScoreBoard");
        stage.show();
    }
}
