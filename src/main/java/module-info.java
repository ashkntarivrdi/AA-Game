module AA {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    exports view;
    opens view to javafx.fxml;
    exports model;
    opens model to com.google.gson;
}