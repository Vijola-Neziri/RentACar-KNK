package Controller;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private UserRepository userRepository = new UserRepository();



    public void close(){
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

    @FXML
   public void signupaction(ActionEvent event) throws IOException {
        loginid.getScene().getWindow().hide();
        Stage signup=new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/SignUpForm.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene=new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
handler = new DBHandler();
    }
}

