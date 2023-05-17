package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import models.User;
import repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private SplitMenuButton langEnum;

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

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private UserRepository userRepository = new UserRepository();

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
    void loginaction(ActionEvent event) {
        handleLogin();
    }

    private void handleLogin() {
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(3));
        pt.setOnFinished(ev -> {

        });
        pt.play();

        String username = usernameid.getText();
        String password = passwordid.getText();

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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Username and Password are not correct");
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set the default language to English

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

    @FXML
    void close(ActionEvent event) {
        // Add your login action logic here
        // This method will be called when the login button is clicked
    }

    @FXML
    void changeLanguage(ActionEvent event) {
        if (event.getTarget() instanceof MenuItem) {
            MenuItem selectedItem = (MenuItem) event.getTarget();
            String languageCode = selectedItem.getUserData().toString();

            // Load the resource bundle for the selected language
            ResourceBundle bundle = ResourceBundle.getBundle("Controller.Bundle", new Locale(languageCode));

            // Example usage: retrieving a localized string
            String loginButtonLabel = bundle.getString("loginButtonLabel");
            loginid.setText(loginButtonLabel);

            // Apply the same approach to other UI elements that need localization
            String closeButtonLabel = bundle.getString("closeButtonLabel");
            close.setText(closeButtonLabel);

            // Show an alert with localized strings
            String languageChangeHeader = bundle.getString("languageChangeHeader");
            String languageChangeMessage = bundle.getString("languageChangeMessage");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(languageChangeHeader);
            alert.setContentText(languageChangeMessage);
            alert.show();
        }
    }
}

