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
        {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/Login.fxml"));
                Pane pane = fxmlLoader.load();
                Scene scene = new Scene(pane, 650, 500);
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setResizable(false);
            }catch (Exception e) {
                e.printStackTrace();
            }
            }


        }


    public static void main(String[] args){
        launch(args);
    }
}
