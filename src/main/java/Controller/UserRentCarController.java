package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

public class UserRentCarController implements Initializable {

    @FXML
    private Button carlist;

    @FXML
    private Button close;

    @FXML
    private DatePicker ent_dateRented;

    @FXML
    private Button home_btn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button rentBtn;

    @FXML
    private Button rentCar_btn;

    @FXML
    private TextField rent_amount;

    @FXML
    private Label rent_balance;

    @FXML
    private ComboBox<?> rent_brand;

    @FXML
    private ComboBox<?> rent_carid;

    @FXML
    private TableColumn<?, ?> rent_col_brand;

    @FXML
    private TableColumn<?, ?> rent_col_carid;

    @FXML
    private TableColumn<?, ?> rent_col_model;

    @FXML
    private TableColumn<?, ?> rent_col_price;

    @FXML
    private TableColumn<?, ?> rent_col_status;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private TextField rent_firstName;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<?> rent_model;

    @FXML
    private TableView<?> rent_tableView;

    @FXML
    private Label rent_tableView1;

    @FXML
    private Label rent_total;

    @FXML
    private Label user;

    @FXML
    private Label username;

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;


    @FXML
    void RentCar(ActionEvent event) {

    }

    @FXML
    public void carlist(ActionEvent event) throws  IOException{
        carlist.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList.fxml"));
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
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void rentAmount(ActionEvent event) {

    }

    @FXML
    void rentCarBrand(ActionEvent event) {

    }

    @FXML
    void rentCarCarId(ActionEvent event) {

    }

    @FXML
    void rentCarGender(ActionEvent event) {

    }

    @FXML
    void rentDisplayTotal(ActionEvent event) {

    }

    @FXML
    void rentPay(ActionEvent event) {

    }

    @FXML
    public void switchform(ActionEvent event) throws IOException {
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
    void switchForm(ActionEvent event) throws  IOException{
        home_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserHome.fxml"));
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

