package Controller;

import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelpController {
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
        Stage stage = (Stage)main_form.getScene().getWindow();
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

    }



}
