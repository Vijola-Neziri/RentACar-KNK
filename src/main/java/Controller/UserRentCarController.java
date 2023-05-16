package Controller;

import models.User;
import ConnectionMysql.DBHandler;
import Services.carData;
import app.LoginForm;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.CarRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import repository.CarRepository;
import models.Car;
import models.User;
import repository.CarRepository;
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
    private ComboBox<String> rent_brand;

    @FXML
    private ComboBox<String> rent_carid;

    @FXML
    private TableColumn<carData, String> rent_col_brand;

    @FXML
    private TableColumn<carData, Integer> rent_col_carid;

    @FXML
    private TableColumn<carData, String> rent_col_model;

    @FXML
    private TableColumn<carData, Double> rent_col_price;

    @FXML
    private TableColumn<carData, String> rent_col_status;

    @FXML
    private TableColumn<carData, Date> rent_col_date;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private TextField rent_firstName;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<String> rent_gender;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<String> rent_model;

    @FXML
    private TableView<carData> rent_tableView;

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

    private CarRepository carRepository;
    public UserRentCarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @FXML
    void RentCar(ActionEvent event) {
        // Get selected car information
        carData selectedCar = rent_tableView.getSelectionModel().getSelectedItem();
        if (selectedCar != null) {
            LocalDate dateRented = ent_dateRented.getValue();
            LocalDate dateReturn = rent_dateReturn.getValue();
            String firstName = rent_firstName.getText();
            String lastName = rent_lastName.getText();
            String gender = rent_gender.getValue();
            double amount = Double.parseDouble(rent_amount.getText());

            // Perform the rental and update the car status
            carRepository.rentCar(selectedCar.getCarId(), firstName, lastName, gender, dateRented, dateReturn, amount);

            // Rental successful, display a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Car Rental");
            alert.setHeaderText(null);
            alert.setContentText("Car rented successfully!");
            alert.showAndWait();

            // Clear the form
            rent_carid.setValue(null);
            ent_dateRented.setValue(null);
            rent_firstName.clear();
            rent_lastName.clear();
            rent_gender.setValue(null);
            rent_amount.clear();
        } else {
            // No car selected, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Car Rental");
            alert.setHeaderText(null);
            alert.setContentText("Please select a car to rent.");
            alert.showAndWait();
        }
    }

    @FXML
    void rentCarBrand(ActionEvent event) {
        String brand = rent_brand.getValue();
        if (brand != null) {
            ObservableList<String> carModels = (ObservableList<String>) carRepository.getCarModelsByBrand(brand);
            rent_model.setItems(carModels);
        }
    }

    @FXML
    void rentCarCarId(ActionEvent event) {
        String carId = rent_carid.getValue();
        if (carId != null) {
            Car selectedCar = carRepository.getCarById(Integer.parseInt(carId));
            if (selectedCar != null) {
                rent_brand.setValue(selectedCar.getBrandMakina());
                rent_model.setValue(selectedCar.getModelMakina());
                rent_amount.setText("");
            }
        }
    }

    // ... remaining methods ...

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        connection = handler.getConnection(); // Get the database connection
        carRepository = new CarRepository(connection); // Initialize the CarRepository with the connection
    }
}