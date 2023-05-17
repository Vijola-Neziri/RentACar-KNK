package Controller;

import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import repository.AdminRepository;
import repository.UserRepository;

import app.LoginForm;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import ConnectionMysql.DBHandler;
import models.Admin;
import models.User;

public class LoginFormController implements Initializable {
    @FXML
    private Button loginid;

    @FXML
    private TextField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private Button close;

    @FXML
    private TextField usernameid;

    @FXML
    private ComboBox<String> comboBox;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private UserRepository userRepository = new UserRepository();
    private AdminRepository adminRepository = new AdminRepository();

    public void close() {
        System.exit(0);
    }

    @FXML
    void showKeyword(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleLogin();
        }
    }
    @FXML
    void selectUser(ActionEvent event) {

    }

    @FXML
    void loginaction(ActionEvent event) {
        handleLogin();
    }

    private void handleLogin() {
        String username = usernameid.getText();
        String password = passwordid.getText();

        String selectedOption = comboBox.getSelectionModel().getSelectedItem();
        if (selectedOption == null) {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Please select a role.");
            return;
        }

        if (selectedOption.equals("User")) {
            User user = userRepository.getUserByUsernameAndPassword(username, password);

            if (user != null) {
                loginid.getScene().getWindow().hide();
                Stage home = new Stage();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserHome.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Username and Password are not correct.");
            }
        } else if (selectedOption.equals("Admin")) {
            Admin admin = adminRepository.getAdminByUsernameAndPassword(username, password);

            if (admin != null) {
                loginid.getScene().getWindow().hide();
                Stage home = new Stage();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Dashboard.fxml"));
                    Parent root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    home.setScene(scene);
                    home.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Error", "Username and Password are not correct.");
            }
        }
    }

    @FXML
    public void signupaction(ActionEvent event) throws IOException {
        loginid.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().addAll("Admin", "User"); // Add options to the comboBox
        handler = new DBHandler();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
