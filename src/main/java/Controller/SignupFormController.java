package Controller;

import app.LoginForm;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ConnectionMysql.DBHandler;
import repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import repository.UserRepository;

public class SignupFormController implements Initializable {
    @FXML
    private TextField addressid;

    @FXML
    private RadioButton female;

    @FXML
    private Button loginid;

    @FXML
    private RadioButton male;

    @FXML
    private TextField name;

    @FXML
    private PasswordField passwordid;

    @FXML
    private TextField phoneid;

    @FXML
    private Button signupid;

    @FXML
    private TextField usernameid;

    private DBHandler handler;
    private UserRepository userRepository;
    @FXML
    private Label myLabel;
    @FXML
    private RadioButton Male,Female;
    public void getMale(ActionEvent event) {
        if (Male.isSelected()) {
            myLabel.setText(Male.getText());
        } else if (Female.isSelected()) {


        }
    }






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
    public void signupaction(ActionEvent event) {
        if (usernameid.getText().isEmpty() || name.getText().isEmpty() || phoneid.getText().isEmpty()
                || addressid.getText().isEmpty() || passwordid.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
        } else {
            String username = usernameid.getText();
            String name = this.name.getText();
            String phone = phoneid.getText();
            String address = addressid.getText();
            String password = passwordid.getText();
            String gender = getGender();

            boolean registrationSuccessful = userRepository.signup(username, name, phone, address, password, gender);

            if (registrationSuccessful) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Registration successful!");
                successAlert.showAndWait();

                // Close the signup window
                Stage stage = (Stage) signupid.getScene().getWindow();
                stage.close();

                // Open the UserHome.fxml page
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserHome.fxml"));
                    Pane pane = fxmlLoader.load();
                    Scene scene = new Scene(pane);
                    Stage userHomeStage = new Stage();
                    userHomeStage.setScene(scene);
                    userHomeStage.setTitle("User Home");
                    userHomeStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Registration failed. Please try again.");
                errorAlert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signupid.setOnAction(this::signupaction);
        userRepository = new UserRepository();
    }

        public String getGender() {
            if (male.isSelected()) {
                return "Male";
            } else if (female.isSelected()) {
                return "Female";
            } else {
                return "";
            }
        }
    }
