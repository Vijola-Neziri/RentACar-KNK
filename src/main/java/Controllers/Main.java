package Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            Scene scene = new Scene(root,400,400);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
        }catch(Exception e ){
            e.printStackTrace();

        }

    }
    public static void main(String[] args){
        launch(args);
    }
}
