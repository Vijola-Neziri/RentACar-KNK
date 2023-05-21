package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminRentForm extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminRentForm.class.getResource("/views/AdminRent.fxml"));
        Pane pane = fxmlLoader.load();

        VBox container = new VBox();
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.setSpacing(10);
        container.setPadding(new Insets(20));


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Rent");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}
