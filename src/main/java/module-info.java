module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.rentacarknk to javafx.fxml;
    exports com.example.rentacarknk;
}