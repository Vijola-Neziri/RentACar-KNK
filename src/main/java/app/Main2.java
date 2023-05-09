package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main2 extends Application{
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/HomePage.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane,700,600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in");
        primaryStage.show();


    }
    public static void main(String [] args){
        launch(args);
    }
}