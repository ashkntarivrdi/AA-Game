package view.Menus;

import controller.GameController;
import controller.SettingController;
import enums.Phase;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

public class GameMenu extends Application{
    GameController gameController = new GameController();
    @Override
    public void start(Stage stage) throws Exception {
        Pane gamePane = FXMLLoader.load(GameMenu.class.getResource("/FXML/GameMenu.fxml"));

        CenterBall innerBall = new CenterBall();
        CenterBall outerBall = new CenterBall(150);

        Text phaseNumber = getPhaseNumber(outerBall);

        ProgressBar progressBar = new ProgressBar(0);

        VBox vBox = new VBox(getProgressBarText(), progressBar);
        vBox.setAlignment(Pos.TOP_LEFT);

        gamePane.getChildren().addAll(innerBall, phaseNumber, vBox);

        Scene scene = new Scene(gamePane);
        if(SettingController.isDarkMode()) scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DarkMode.css").toExternalForm());
        else scene.getStylesheets().add(LoginMenu.class.getResource("/CSS/DefaultStyle.css").toExternalForm());

        initializeGame(gamePane, outerBall, progressBar);

        if (CurrentGame.getPhase() == Phase.ONE)
            initializeGamePhaseOne();

        gamePane.requestFocus();

        stage.setTitle("Game Menu");
        stage.setScene(scene);
        stage.show();
    }

    private void initializeGamePhaseOne() {

    }

    private void initializeGame(Pane gamePane, CenterBall outerBall, ProgressBar progressBar) {
        CurrentGame.resetNumberOfBallsInEachPhase();
        CurrentGame.resetBalls();

        Ball ball = new Ball();
        gamePane.getChildren().add(ball);

            gamePane.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    String keyName = event.getCode().getName();
                    if(keyName.equals(CurrentGame.getShootKey())) {
                        gameController.shoot(ball, gamePane, outerBall, progressBar);
                    }
                    else if(keyName.equals(CurrentGame.getFreezeKey())) {
                        System.out.println(progressBar.getProgress());
                        if(progressBar.getProgress() >= 1)
                            gameController.freeze(progressBar);
                    }
                }

            });
    }

    public Text getPhaseNumber(CenterBall centerBall) {
        Text phaseNumber = new Text(centerBall.getCenterX() - 18, centerBall.getCenterY() + 18,
                "" + CurrentGame.getPhase().getPhase());
        phaseNumber.setFill(Color.WHITE);
        phaseNumber.setFont(new Font(65));
        return phaseNumber;
    }

    public Text getProgressBarText() {
        Text text = new Text("Freeze");
        text.setFill(Color.LIGHTBLUE);
        Font font = Font.font("serif", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 14);
        text.setFont(font);
        return text;
    }

}
