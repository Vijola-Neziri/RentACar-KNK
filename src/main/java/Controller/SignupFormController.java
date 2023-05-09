package Controller;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/views/LogInForm.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
        login.setResizable(false);
    }

    @FXML
    void signupaction(ActionEvent event) {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {
        });

        String insert = "INSERT INTO klientet(klient_username, emri_i_klientit, telefoni_i_klientit, adresa_e_klientit, fjalekalimi_i_klientit, gjinia)"
                + "VALUES(?,?,?,?,?,?)";


        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();

// Fut të dhënat në databazë
        handler = new DBHandler();
        try {
            connection = handler.getConnection();
            pst = connection.prepareStatement(insert);
            pst.setString(1, usernameid.getText());
            pst.setString(2, name.getText());
            pst.setString(3, phoneid.getText());
            pst.setString(4, addressid.getText());
            pst.setString(5, passwordid.getText());
            pst.setString(6,getGender());

            pst.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
