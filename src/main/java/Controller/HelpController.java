package Controller;

import app.LoginForm;
import ConnectionMysql.DBHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Komentet;
import repository.KomentetRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
    private MenuBar Help;
    @FXML
    private TextArea textfieldid;

    @FXML
    private Label usrLabel;
    @FXML
    private Label user;
    private DBHandler handler;

    private PreparedStatement pst;
    private ResultSet resultSet;

    private Connection connection;
    private KomentetRepository komentetRepository;
    @FXML
    public  void HelpBtn(ActionEvent event) throws IOException {
        Help.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Help.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    public HelpController() {
        connection = DBHandler.getConnection();
        komentetRepository = new KomentetRepository(connection);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String loggedInUsername = LoginFormController.getLoggedInUsername();

        displayUsername(loggedInUsername);
    }

    @FXML
    void carList(ActionEvent event) throws IOException{
        carListid.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    @FXML
    public  void help(ActionEvent event) throws IOException {
        home_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Help.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
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
    void rentCar(ActionEvent event) throws  IOException{
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserRent1.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void signOut(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Logout Confirmation");
        confirmation.setHeaderText("Are you sure you want to logout?");
        confirmation.setContentText("Press OK to confirm.");

        // Customize the button types
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Wait for the user's response
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOK) {
            // User clicked OK, perform logout actions
            Platform.exit(); // Close all windows and exit the application
        }
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
    private void displayUsername(String username) {
        try (Connection connection = handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT emri_user FROM User WHERE user_username = ?")) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("emri_user");
                usrLabel.setText(name);
            } else {
                usrLabel.setText("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        nameid.clear();
        emailid.clear();
        textfieldid.clear();
    }
}
