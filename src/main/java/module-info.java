module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;
requires java.sql;


    opens com.example.rentacarknk to javafx.fxml;
    opens Controllers to javafx.graphics;
    exports Controllers to javafx.graphics;




}