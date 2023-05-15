package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CarList1 extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarList1.class.getResource("/views/CarList1.fxml"));
        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car List");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
