package app;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminCarRegister extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminRentForm.class.getResource("/views/AdminCarRegistration.fxml"));
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
