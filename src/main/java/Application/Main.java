package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException{

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane,700,700);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);


        }


    public static void main(String[] args){
        launch(args);
    }
}
