module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.google.gson;
    requires java.desktop;
    requires java.logging;



    exports view.Menus;
    exports model;
    opens model to com.google.gson;
    exports enums;
    exports controller;
    exports view.Animations;
    opens view.Animations to javafx.fxml;
    exports view.Controllers;
    opens view.Controllers to javafx.fxml;
    opens view.Menus to javafx.fxml;
}