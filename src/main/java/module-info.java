module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;
requires java.sql;


    opens com.example.rentacarknk to javafx.fxml;
    exports com.example.rentacarknk;

    opens Application to javafx.fxml;
  exports Application to javafx.graphics;
    exports app to javafx.graphics;
    opens app to javafx.fxml;


}