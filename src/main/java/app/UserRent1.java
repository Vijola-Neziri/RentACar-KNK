package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserRent1 extends Application {
        @Override
        public void start(Stage primaryStage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(UserRent1.class.getResource("/views/UserRent1.fxml"));
            Pane pane = fxmlLoader.load();

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Help");
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
