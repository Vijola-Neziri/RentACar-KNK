module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;
requires java.sql;


    opens com.example.rentacarknk to javafx.fxml;
exports com.example.rentacarknk;
    opens Controllers to javafx.fxml;
    exports Controllers to javafx.graphics;




}