module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;
requires java.sql;


    opens com.example.rentacarknk to javafx.fxml;
opens Application to javafx.fxml;

    exports Application to javafx.graphics;



}