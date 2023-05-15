package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
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

public class UserHomeController implements Initializable {
    @FXML
    private Button availableCars_btn;

    @FXML
    private Button close;

    @FXML
    private Label home_availableCars;

    @FXML
    private Button home_btn;

    @FXML
    private LineChart<?, ?> home_customerChart;

    @FXML
    private AnchorPane home_form;

    @FXML
    private BarChart<?, ?> home_incomeChart;

    @FXML
    private Label home_totalCustomers;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button rentCar_btn;

    @FXML
    private Label user;

    @FXML
    private Label username;
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

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
    void CarList(ActionEvent event) {

    }
    @FXML
    public void RentCar(ActionEvent event) throws IOException {
        rentCar_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

        @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
    }
}
