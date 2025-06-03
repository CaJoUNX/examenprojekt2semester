module org.media.examsprojekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires sqljdbc4;
    requires java.desktop;

    opens org.media.examsprojekt to javafx.fxml;
    exports org.media.examsprojekt;
    exports UI.Login;
    opens UI.Login to javafx.fxml;
}