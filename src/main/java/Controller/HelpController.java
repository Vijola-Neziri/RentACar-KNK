package Controller;

import app.LoginForm;
import ConnectionMysql.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Komentet;
import repository.KomentetRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    @FXML
    private AnchorPane availableCars_form;

    @FXML
    private Button carListid;

    @FXML
    private Button close;

    @FXML
    private TextField emailid;

    @FXML
    private Button helpid;

    @FXML
    private Button home_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TextField nameid;

    @FXML
    private Button rentCar_btn;

    @FXML
    private Button submitid;

    @FXML
    private TextArea textfieldid;

    @FXML
    private Label user;

    private Connection connection;
    private KomentetRepository komentetRepository;

    public HelpController() {
        connection = DBHandler.getConnection();
        komentetRepository = new KomentetRepository(connection);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Add any initialization logic here
    }

    @FXML
    void carList(ActionEvent event) {

    }

    @FXML
    void help(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void switchForm(ActionEvent event) throws IOException {
        home_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserHome.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void rentCar(ActionEvent event) {

    }

    @FXML
    void signOut(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {
        String name = nameid.getText();
        String email = emailid.getText();
        String message = textfieldid.getText();

        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Submission Error", "Please fill in all the fields.");
            return;
        }

        Komentet komentet = new Komentet(name, email, message);

        try {
            komentetRepository.addKomentet(komentet);
            showAlert(Alert.AlertType.INFORMATION, "Submission Success", "Your message has been submitted.");
            clearFields();
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Submission Error", "An error occurred while submitting your message.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    private void clearFields() {
        nameid.clear();
        emailid.clear();
        textfieldid.clear();
    }
}
