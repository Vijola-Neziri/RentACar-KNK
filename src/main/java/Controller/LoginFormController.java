package Controller;

import app.LoginForm;
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
import repository.AdminRepository;
import repository.UserRepository;
import ConnectionMysql.DBHandler;
import models.Admin;
import models.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    @FXML
    private Button loginid;

    @FXML
    private RadioButton alButton;

    @FXML
    private RadioButton enButton;

    @FXML
    private TextField passwordid;

    @FXML
    private Button signupid;

    @FXML
    private Button close;

    @FXML
    private TextField usernameid;

    @FXML
    private Label label_clickSignupToRegisterid;

    @FXML
    private Label label_passwordid;

    @FXML
    private Label label_selectUserid;

    @FXML
    private Label label_usernameid;

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

//    @FXML
//    public void changeLanguage() {
//
//    }

    @FXML
    public void selectUser() {

    }

    @FXML
    void loginaction(ActionEvent event) {
        handleLogin();
    }


    public  void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        alButton.setToggleGroup(languageToggleGroup);
        enButton.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if(newToggle == alButton) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.AL_SQ", currentLocale);
                loginid.setText(bundle.getString("login.button.text"));
                signupid.setText(bundle.getString("signup.button.text"));
                usernameid.setPromptText(bundle.getString("Enter.your.username.placeholder"));
                passwordid.setPromptText(bundle.getString("Enter.your.password.placeholder"));
                label_clickSignupToRegisterid.setText(bundle.getString("Click.signup.to.register.label"));
                label_selectUserid.setText(bundle.getString("select.user.label"));
                label_usernameid.setText(bundle.getString("username.label"));



            }else if(newToggle == enButton)  {
                Locale currentLocale = new Locale("en", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.US_EN", currentLocale);
                loginid.setText(bundle.getString("login.button.text"));
                signupid.setText(bundle.getString("signup.button.text"));
                usernameid.setPromptText(bundle.getString("Enter.your.username.placeholder"));
                passwordid.setPromptText(bundle.getString("Enter.your.password.placeholder"));
                label_clickSignupToRegisterid.setText(bundle.getString("Click.signup.to.register.label"));
                label_selectUserid.setText(bundle.getString("select.user.label"));
                label_usernameid.setText(bundle.getString("username.label"));

            }

        });
        languageToggleGroup.selectToggle(alButton);
    }

    public static String loggedInUsername;

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
                loggedInUsername = username;
                loginid.getScene().getWindow().hide();
                Stage home = new Stage();

                try {
                    System.out.println("Connected with database");
                    System.out.println("The user this username :"+ username +" and password "+ user.getSalt()+" is logged in");
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
                loggedInUsername = username;
                loginid.getScene().getWindow().hide();
                Stage home = new Stage();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/AdminHome.fxml"));
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
    public static String getLoggedInUsername() {
        return loggedInUsername;
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
        changeLanguage();

    }
    



    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
