package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRentCarForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UserRentCarForm.class.getResource("/views/UserRentCar.fxml"));
        Pane pane = fxmlLoader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
