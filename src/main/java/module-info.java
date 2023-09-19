module pack {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.prefs;
    exports mcarb;
    exports mcarb.view;
    opens mcarb.view to javafx.fxml;
    opens mcarb.model;
}
