module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires java.logging;



    exports view;
    opens view to javafx.fxml;
    exports model;
    opens model to com.google.gson;
    exports enums;
    exports controller;
    exports view.Animations;
    opens view.Animations to javafx.fxml;
}