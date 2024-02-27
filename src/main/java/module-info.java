module com.example.pocok {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pocok to javafx.fxml;
    exports com.example.pocok;
}