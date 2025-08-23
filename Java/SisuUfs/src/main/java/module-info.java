module com.mycompany.sisuufs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.sisuufs to javafx.fxml;
    exports com.mycompany.sisuufs;
}
