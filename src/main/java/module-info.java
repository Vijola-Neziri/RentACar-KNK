module com.example.rentacarknk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.rentacarknk to javafx.fxml;
    opens models to javafx.base;
    exports com.example.rentacarknk;
    opens Controller to javafx.fxml;
    exports Controller to javafx.graphics;

    exports app to javafx.graphics;
    opens app to javafx.fxml;


}