package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SignupForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane,450,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign up");
        primaryStage.show();


    }
    public static void main(String[] args){
        launch(args);
    }
}
