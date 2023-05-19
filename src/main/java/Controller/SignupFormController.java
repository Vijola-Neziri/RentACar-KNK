package Controller;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ConnectionMysql.DBHandler;
import repository.UserRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

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


    @FXML
    private Label label_addressid;

    @FXML
    private Label label_doYouHaveAnAccountid;

    @FXML
    private Label label_genderid;

    @FXML
    private Label label_nameid;

    @FXML
    private Label label_passwordid;

    @FXML
    private Label label_phoneid;

    @FXML
    private Label label_usernameid;

    private DBHandler handler;
    private UserRepository userRepository;
    @FXML
    private Label myLabel;
    @FXML
    private RadioButton alButton;

    @FXML
    private RadioButton enButton;

    @FXML
    private RadioButton Male, Female;

    public  void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        alButton.setToggleGroup(languageToggleGroup);
        enButton.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if(newToggle == alButton) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.AL_SQ", currentLocale);
                loginid.setText(bundle.getString("login.button"));
                signupid.setText(bundle.getString("signup.button"));
                usernameid.setPromptText(bundle.getString("Enter.your.username.placeholder1"));
                passwordid.setPromptText(bundle.getString("Enter.your.password.placeholder1"));
                name.setPromptText(bundle.getString("Enter.your.name.placeholder"));
                phoneid.setPromptText(bundle.getString("Enter.your.phone.placeholder"));
                addressid.setPromptText(bundle.getString("Enter.your.address.placeholder"));
                label_nameid.setText(bundle.getString("Name.label"));
                label_usernameid.setText(bundle.getString("Username.label"));
                label_phoneid.setText(bundle.getString("Phone.label"));
                label_addressid.setText(bundle.getString("Address.label"));
                label_genderid.setText(bundle.getString("Gender.label"));
                male.setText(bundle.getString("Male.radio"));
                female.setText(bundle.getString("Female.radio"));
                label_doYouHaveAnAccountid.setText(bundle.getString("Do.you.have.an.account.label"));





            }else if(newToggle == enButton)  {
                Locale currentLocale = new Locale("en", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.US_EN", currentLocale);
                loginid.setText(bundle.getString("login.button"));
                signupid.setText(bundle.getString("signup.button"));
                usernameid.setPromptText(bundle.getString("Enter.your.username.placeholder1"));
                passwordid.setPromptText(bundle.getString("Enter.your.password.placeholder1"));
                name.setPromptText(bundle.getString("Enter.your.name.placeholder"));
                phoneid.setPromptText(bundle.getString("Enter.your.phone.placeholder"));
                addressid.setPromptText(bundle.getString("Enter.your.address.placeholder"));
                label_nameid.setText(bundle.getString("Name.label"));
                label_usernameid.setText(bundle.getString("Username.label"));
                label_phoneid.setText(bundle.getString("Phone.label"));
                label_addressid.setText(bundle.getString("Address.label"));
                label_genderid.setText(bundle.getString("Gender.label"));
                male.setText(bundle.getString("Male.radio"));
                female.setText(bundle.getString("Female.radio"));
                label_doYouHaveAnAccountid.setText(bundle.getString("Do.you.have.an.account.label "));

            }

        });
        languageToggleGroup.selectToggle(alButton);
    }

    public void getMale(ActionEvent event) {
        if (Male.isSelected()) {
            myLabel.setText(Male.getText());
        } else if (Female.isSelected()) {
            myLabel.setText(Female.getText());
        }
    }

    public void getFemale(ActionEvent event) {
        if (Female.isSelected()) {
            myLabel.setText(Female.getText());
        } else if (Male.isSelected()) {
            myLabel.setText(Male.getText());
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

    private String getGender() {
        if (Male.isSelected()) {
            return Male.getText();
        } else if (Female.isSelected()) {
            return Female.getText();
        } else {
            return ""; // Return an empty string if no gender is selected
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        signupid.setOnAction(this::signupaction);
        userRepository = new UserRepository();
        changeLanguage();
    }
}

