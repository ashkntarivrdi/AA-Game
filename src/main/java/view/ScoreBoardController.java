package view;

import javafx.scene.input.MouseEvent;

public class ScoreBoardController {
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        new MainMenu().start(LoginMenu.stage);
    }
}
