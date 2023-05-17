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

public class SignupForm extends Application {
    @Override

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();

        VBox container = new VBox();
        container.setAlignment(Pos.BOTTOM_RIGHT);
        container.setSpacing(10);
        container.setPadding(new Insets(20));

        Label primeDriveLabel = new Label("PrimeDrive");
        primeDriveLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold;");

        container.getChildren().add(primeDriveLabel);
        pane.getChildren().add(container);


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sign up");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}