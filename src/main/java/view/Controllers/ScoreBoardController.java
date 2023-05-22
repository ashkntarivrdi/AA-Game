package view.Controllers;

import javafx.scene.input.MouseEvent;
import view.Menus.LoginMenu;
import view.Menus.MainMenu;

public class ScoreBoardController {
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        new MainMenu().start(LoginMenu.stage);
    }
}
