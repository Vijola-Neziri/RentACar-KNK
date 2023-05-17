package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DashboardForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Dashboard.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane,1100,600);
//        scene.getStylesheets().add(getClass().getResource("/css/dashboard.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
        
    }
    public static void main(String[] args){
        launch(args);
    }
}
