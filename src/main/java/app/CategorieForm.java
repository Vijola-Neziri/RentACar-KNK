package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CategorieForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/Categories.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Categories");
        primaryStage.show();


    }
    public static void main(String[] args){
        launch(args);
    }
}
