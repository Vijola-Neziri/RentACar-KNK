package app;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CarListForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CarListForm.class.getResource("/views/CarList.fxml"));
   Pane root = new Pane();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car List");
        primaryStage.show();
    }


    @FXML
    public void nextofotot(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList1.fxml"));
        Pane pane = fxmlLoader.load();
        Scene carList1Scene = new Scene(pane);
        Stage carList1Stage = new Stage();
        carList1Stage.setScene(carList1Scene);
        carList1Stage.show();
    }
    @FXML
    public void backtoslide(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList1.fxml"));
        Pane pane = fxmlLoader.load();
        Scene carList1Scene = new Scene(pane);
        Stage carList1Stage = new Stage();
        carList1Stage.setScene(carList1Scene);
        carList1Stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
