package Controller;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Car;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CarsController  implements Initializable {


    @FXML
    private Button availableCars_btn;

    @FXML
    private AnchorPane availableCars_form;

    @FXML
    private TextField brandtxt;

    @FXML
    private TextField caridtxt;

    @FXML
    private TableView<Car> carsTable;

    @FXML
    private Button clearbtn;

    @FXML
    private Button close;

    @FXML
    private TableColumn<Car, Integer> col_id;

    @FXML
    private TableColumn<Car, String> colbrand;

    @FXML
    private TableColumn<Car, String> colmodel;

    @FXML
    private TableColumn<Car, String> coltype;

    @FXML
    private Button deletebtn;

    @FXML
    private ImageView fotoimp;

    @FXML
    private Button home_btn;

    @FXML
    private TableColumn<Car, String> imagecol;

    @FXML
    private Button imporbtn;

    @FXML
    private Button insertbtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private TextField modeltxt;

    @FXML
    private TableColumn<Car, Float> pricecol;

    @FXML
    private TextField pricetxt;

    @FXML
    private Button rentCar_btn;

    @FXML
    private TextField typetxt;

    @FXML
    private Button updatebtn;

    @FXML
    private Label user;

    @FXML
    private Label username;

    @FXML
    void clear(ActionEvent event) {

    }


    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void importimg(ActionEvent event) {

    }

    @FXML
    void insert(ActionEvent event) {
        // Get the values from the text fields
        String brand = brandtxt.getText();
        int carId = Integer.parseInt(caridtxt.getText());
        String model = modeltxt.getText();
        float price = Float.parseFloat(pricetxt.getText());
        String status = ""; // Set the initial status as empty
        String photo = ""; // Set the initial photo path as empty
        LocalDate currentDate = LocalDate.now();

        // Create a new car object with the input values
        Car newCar = new Car(carId, brand, model, price, status, photo, currentDate);

        // Add the new car to the table
        carsTable.getItems().add(newCar);

        // Clear the text fields
        clear(event);

        // Insert the car information into the database
        try {
            // Establish database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RENT", "root", "Rinesa123.");

            // Create the SQL insert statement
            String sql = "INSERT INTO makina (makina_id, brand_makina, model_makina, cmimi_makina, statusiMakina, foto_makina, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

            // Prepare the statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, newCar.getMakinaId());
            statement.setString(2, newCar.getBrandMakina());
            statement.setString(3, newCar.getModelMakina());
            statement.setDouble(4, newCar.getCmimiMakina());
            statement.setString(5, newCar.getStatusiMakina());
            statement.setString(6, newCar.getFotoMakina());
            statement.setDate(7, java.sql.Date.valueOf(newCar.getDate()));

            // Execute the statement
            statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions here
        }
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
    void update(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
