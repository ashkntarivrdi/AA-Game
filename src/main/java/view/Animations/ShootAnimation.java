package view.Animations;

import controller.GameController;
import controller.Utils.UserUtils;
import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Ball;
import model.CenterBall;
import model.CurrentGame;

public class ShootAnimation extends Transition {
    GameController gameController = new GameController();
    private Pane pane;
    private Ball ball;
    private CenterBall outerBall;
    private Text numberOfBallsLeft;
    private Line line;
    private Text score;
    private ProgressBar progressBar;
    private int guestScore = 0;

    public ShootAnimation(Pane pane, Ball ball, CenterBall outerBall , Text numberOfBallsLeft, ProgressBar progressBar, Line line, Text score) {
        this.pane = pane;
        this.ball = ball;
        this.outerBall = outerBall;
        this.numberOfBallsLeft = numberOfBallsLeft;
        this.line = line;
        this.progressBar = progressBar;
        this.score = score;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
        this.progressBar.setProgress(this.progressBar.getProgress() + 0.2);
        CenterBall.addBallToArray(this.ball);
        CenterBall.addTextToArray(this.numberOfBallsLeft);
        CenterBall.addLineToArray(this.line);
    }

    @Override
    protected void interpolate(double frac) {
        double y = ball.getCenterY() - 10;
        double textY = numberOfBallsLeft.getY() - 10;

        double x;
        double textX;

        if (ball.getCenterX() > 140 && ball.getCenterX() < 150 + 66) {
            x = ball.getCenterX() + 4;
            textX = numberOfBallsLeft.getX() + 4;
        }
        else if (ball.getCenterX() >= 150 + 66 && ball.getCenterX() <= 150 + 2 * 66){
            x = ball.getCenterX() - 1;
            textX = numberOfBallsLeft.getX() - 1;
        }
        else {
            x = ball.getCenterX() - 4;
            textX = numberOfBallsLeft.getX() - 4;
        }

        if(outerBall.getBoundsInParent().intersects(ball.getLayoutBounds())) {
            Media media = new Media(getClass().getResource("/musics/shoot.wav").toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);

            this.stop();

            line.setEndX(ball.getCenterX());
            line.setEndY(ball.getCenterY());
            pane.getChildren().add(0, line);

            if(!UserUtils.isGuestPlayer()) {
                gameController.setScore((CurrentGame.getPhase().getPhase() * 2) + gameController.getScore());
                score.setText("" + gameController.getScore());
            }else {
                score.setText("" + (2 * CurrentGame.getNumberOfBalls() - (gameController.getNumberOfBallsLeft()) + gameController.getNumberOfUpperBallsLeft()) * 2 * CurrentGame.getPhase().getPhase());
                gameController.setScore((2 * CurrentGame.getNumberOfBalls() - (gameController.getNumberOfBallsLeft()) + gameController.getNumberOfUpperBallsLeft()) * 2 * CurrentGame.getPhase().getPhase());
            }

            try {
                GameController.rotate(ball, line, numberOfBallsLeft);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if(y <= 20) {
            pane.getChildren().remove(ball);
            this.stop();
        }

        ball.setCenterY(y);
        numberOfBallsLeft.setY(textY);
        if(gameController.getNumberOfBallsLeft() <= CurrentGame.getNumberOfBalls()/4) {
            ball.setCenterX(x);
            numberOfBallsLeft.setX(textX);
        }
    }

}
