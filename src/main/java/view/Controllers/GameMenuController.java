package view.Controllers;

import javafx.scene.input.MouseEvent;
import model.CurrentGame;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;

public class GameMenuController {
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        //TODO: reset static fields from currentGame

        CurrentGame.resetNumberOfBallsInEachPhase();
        new MainMenu().start(LoginMenu.stage);
    }
}
