module com.example.ragagui {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.jsoup;

    opens com.example.ragagui to javafx.fxml;
    exports com.example.ragagui;
}