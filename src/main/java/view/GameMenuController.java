package view;

import javafx.scene.input.MouseEvent;

public class GameMenuController {
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        //TODO: reset static fields from currentGame
        new MainMenu().start(LoginMenu.stage);
    }
}
