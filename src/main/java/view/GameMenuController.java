package view;

import javafx.scene.input.MouseEvent;

public class GameMenuController {
    public void enterMainMenu(MouseEvent mouseEvent) throws Exception{
        new MainMenu().start(LoginMenu.stage);
    }
}
