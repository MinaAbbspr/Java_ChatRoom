module view.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens view to javafx.fxml;
    exports view;
    exports view.graphic;
    opens view.graphic to javafx.fxml;
    exports view.graphic.fxmlController;
    opens view.graphic.fxmlController to javafx.fxml;
}