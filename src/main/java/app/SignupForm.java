package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();

        Label primeDriveLabel = new Label("PrimeDrive");
        primeDriveLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");


        pane.getChildren().add(primeDriveLabel);
        primeDriveLabel.setLayoutX(pane.getWidth() / 2 - primeDriveLabel.getWidth() / 2);
        primeDriveLabel.setLayoutY(pane.getHeight() / 2 - primeDriveLabel.getHeight() / 2);


        Scene scene = new Scene(pane,450,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign up");
        primaryStage.show();


    }
    public static void main(String[] args){
        launch(args);
    }
}
