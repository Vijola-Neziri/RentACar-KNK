package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class CarListController implements Initializable {

    @FXML
    private Button carlist;

    @FXML
    private Button availableCars_btn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button nextFotot;

    @FXML
    private Button rentCar_btn;

    @FXML
    private Label user;

    @FXML
    private Label username;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void switchForm(ActionEvent event) {

    }

    @FXML
    public void nextofotot(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList1.fxml"));
        Pane pane = fxmlLoader.load();
        Scene carList1Scene = new Scene(pane);
        Stage carList1Stage = new Stage();
        carList1Stage.setScene(carList1Scene);
        carList1Stage.show();
    }

    @FXML
    public void CarList(ActionEvent event) throws  IOException {
        carlist.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }


}
