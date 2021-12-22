module com.dicto.dicto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires com.jfoenix;
    requires java.sql;
    requires mysql.connector.java;

    opens com.dicto.dicto to javafx.fxml;
    exports com.dicto.dicto;
}