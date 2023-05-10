package Controller;

import app.LoginForm;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import ConnectionMysql.DBHandler;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.util.Duration;

public class SignupFormController {
    @FXML
    private TextField addressid;


    @FXML
    private CheckBox female;

    @FXML
    private Button loginid;

    @FXML
    private CheckBox male;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane paneid;

    @FXML
    private PasswordField passwordid;

    @FXML
    private TextField phoneid;

    @FXML
    private Button signupid;

    @FXML
    private TextField usernameid;

    private Connection connection;
    private DBHandler handler;

    private PreparedStatement pst;


    @FXML
    public void loginaction(ActionEvent event) throws IOException {
        signupid.getScene().getWindow().hide();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/LogInForm.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Log in");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signupaction(ActionEvent event) {
        // Kontrollo nese të gjitha fushat janë të mbushura
        if (usernameid.getText().isEmpty() || name.getText().isEmpty() || phoneid.getText().isEmpty() || addressid.getText().isEmpty() || passwordid.getText().isEmpty()) {
            // Trego një alert nëse nuk janë të gjitha fushat e mbushura
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
        } else {
            // Vazhdo nëse të gjitha fushat janë të mbushura
            PauseTransition pt = new PauseTransition();
            pt.setDuration(Duration.seconds(3));
            pt.setOnFinished(ev -> {
            });

            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            // Fut të dhënat në databazë
            handler = new DBHandler();
            try {
                connection = handler.getConnection();
                String insert = "INSERT INTO klientet_signup ( emri_i_klientit, klient_username, fjalekalimi_i_klientit ,telefoni_i_klientit ,adresa_e_klientit, gjinia) " +
                        "VALUES(?,?,?,?,?,?)";
                pst = connection.prepareStatement(insert);
                pst.setString(1, name.getText());
                pst.setString(2, usernameid.getText());
                pst.setString(3, passwordid.getText());
                pst.setString(4, phoneid.getText());
                pst.setString(5, addressid.getText());
                pst.setString(6, getGender());

                pst.executeUpdate();
                connection.close();

                // Shto këtë kod për të hapur "Dashboard.fxml"
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Dashboard.fxml"));
                    Pane pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Dashboard");
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Mbyll dritaren e regjistrimit
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize (URL args0, ResourceBundle arg1) throws SQLException {
            signupid.setOnAction(this::signupaction);
            handler = new DBHandler();

        }



    public String getGender() {
        String gen = "";

        if (male.isSelected()) {
            gen = "Male";
        }  if(female.isSelected()) {
            gen = "Female";

        }
        return  gen;
    }
}
