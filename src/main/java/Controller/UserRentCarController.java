package Controller;

import javafx.collections.FXCollections;
import ConnectionMysql.DBHandler;
import Services.carData;
import app.LoginForm;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.CarRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import models.Car;

public class UserRentCarController implements Initializable {

    @FXML
    private TextField availableCars_brand;

    @FXML
    private Button availableCars_btn;

    @FXML
    private TextField availableCars_carid;

    @FXML
    private Button availableCars_clearBtn;

    @FXML
    private TableColumn<carData, String> availableCars_col_brand;

    @FXML
    private TableColumn<carData, String> availableCars_col_carid;

    @FXML
    private TableColumn<carData, String> availableCars_col_model;

    @FXML
    private TableColumn<carData, String> availableCars_col_price;

    @FXML
    private TableColumn<carData, String> availableCars_col_status;

    @FXML
    private Button availableCars_deleteBtn;

    @FXML
    private AnchorPane availableCars_form;

    @FXML
    private ImageView availableCars_imageView;

    @FXML
    private Button availableCars_importBtn;

    @FXML
    private Button availableCars_insertBtn;

    @FXML
    private TextField availableCars_model;

    @FXML
    private TextField availableCars_price;

    @FXML
    private TextField availableCars_search;

    @FXML
    private ComboBox<?> availableCars_status;

    @FXML
    private TableView<carData> availableCars_tableView;

    @FXML
    private Button availableCars_updateBtn;

    @FXML
    private Button close;

    //    @FXML
//    private ImageView home_availableCars;
    @FXML
    private Label home_availableCars;


    @FXML
    private Button home_btn;

    @FXML
    private LineChart<?, ?> home_customerChart;

    @FXML
    private BarChart<?, ?> home_incomeChart;

//    @FXML
//    private AnchorPane home_totalCustomers;
//
//    @FXML
//    private AnchorPane home_totalIncome;

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
    private TableColumn<carData, String> rent_col_carid;

    @FXML
    private TableColumn<carData, String> rent_col_model;

    @FXML
    private TableColumn<carData, String> rent_col_price;

    @FXML
    private TableColumn<carData, String> rent_col_status;

    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private TextField rent_firstName;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<?> rent_model;

//    @FXML
//    private Label rent_tableView;

    @FXML
    private Button carlist;

    @FXML
    private Label rent_total;

    @FXML
    private Label user;

    @FXML
    private Label username;
    public UserRentCarController() {
        // Default constructor
    }

    @FXML
    private TableView<carData> rent_tableView;
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

    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    // private Connection connect;
//    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private Image image;

    @FXML
    void carlist(ActionEvent event) throws  IOException{
        carlist.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/CarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    private CarRepository carRepository;

    public UserRentCarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // ... existing code ...

    private int customerId;
    public void rentPay(){
        rentCustomerId();

        String sql = "INSERT INTO klientet "
                + "(klient_id, emri_klient, mbiemri_klient, gjinia, makina_id, brand_makina"
                + ", model_makina, total, date_rented, date_returned ) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";

        connection = handler.getConnection();

        try{
            Alert alert;

            if(rent_firstName.getText().isEmpty()
                    || rent_lastName.getText().isEmpty()
                    || rent_gender.getSelectionModel().getSelectedItem() == null
                    || rent_carid.getSelectionModel().getSelectedItem() == null
                    || rent_brand.getSelectionModel().getSelectedItem() == null
                    || rent_model.getSelectionModel().getSelectedItem() == null
                    || totalP == 0 || rent_amount.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            }else{

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){

                    pst = connection.prepareStatement(sql);
                    pst.setString(1, String.valueOf(customerId));
                    pst.setString(2, rent_firstName.getText());
                    pst.setString(3, rent_lastName.getText());
                    pst.setString(4, (String)rent_gender.getSelectionModel().getSelectedItem());
                    pst.setString(5, (String)rent_carid.getSelectionModel().getSelectedItem());
                    pst.setString(6, (String)rent_brand.getSelectionModel().getSelectedItem());
                    pst.setString(7, (String)rent_model.getSelectionModel().getSelectedItem());
                    pst.setString(8, String.valueOf(totalP));
                    pst.setString(9, String.valueOf(rent_dateRented.getValue()));
                    pst.setString(10, String.valueOf(rent_dateReturn.getValue()));

                    pst.executeUpdate();

                    // SET THE  STATUS OF CAR TO NOT AVAILABLE
                    String updateCar = "UPDATE makina SET statusiMakina  = 'Not Available' WHERE makina_id = '"
                            +rent_carid.getSelectionModel().getSelectedItem()+"'";

                    Statement  statement = connection.createStatement();
                    statement.executeUpdate(updateCar);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Successful!");
                    alert.showAndWait();

                    rentCarShowListData();

                    rentClear();
                }
            }
        }catch(Exception e){e.printStackTrace();}

    }
    @FXML
    public void RentCar(ActionEvent event) throws IOException {
        rentCar_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserRentCar.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }
    public void rentClear(){
        totalP = 0;
        rent_firstName.setText("");
        rent_lastName.setText("");
        rent_gender.getSelectionModel().clearSelection();
        amount = 0;
        balance = 0;
        rent_balance.setText("$0.0");
        rent_total.setText("$0.0");
        rent_amount.setText("");
        rent_carid.getSelectionModel().clearSelection();
        rent_brand.getSelectionModel().clearSelection();
        rent_model.getSelectionModel().clearSelection();
    }

    //    private int customerId;
    public void rentCustomerId(){
        String sql = "SELECT klient_id FROM klientet";

        connection = handler.getConnection();

        try{
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while(result.next()){
                // GET THE LAST id and add + 1
                customerId = result.getInt("klient_id") + 1;
            }

        }catch(Exception e){e.printStackTrace();}
    }

    private double amount;
    private double balance;
    public void rentAmount(){
        Alert alert;
        if(totalP == 0 || rent_amount.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();

            rent_amount.setText("");
        }else{
            amount = Double.parseDouble(rent_amount.getText());

            if(amount >= totalP){
                balance = (amount - totalP);
                rent_balance.setText("$" + String.valueOf(balance));
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid :3");
                alert.showAndWait();

                rent_amount.setText("");
            }

        }

    }

    private int countDate;
    public void rentDate(){
        Alert alert;
        if(rent_carid.getSelectionModel().getSelectedItem() == null
                || rent_brand.getSelectionModel().getSelectedItem() == null
                || rent_model.getSelectionModel().getSelectedItem() == null){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Something wrong :3");
            alert.showAndWait();

            rent_dateRented.setValue(null);
            rent_dateReturn.setValue(null);

        }else{

            if(rent_dateReturn.getValue().isAfter(rent_dateRented.getValue())){
                // COUNT THE DAY
                countDate = rent_dateReturn.getValue().compareTo(rent_dateRented.getValue());
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
                // INCREASE OF 1 DAY ONCE THE USER CLICKED THE SAME DAY
                rent_dateReturn.setValue(rent_dateRented.getValue().plusDays(1));

            }
        }
    }

    private double totalP;
    public void rentDisplayTotal(){
        rentDate();
        double price = 0;
        String sql = "SELECT cmimi_makina, model_makina FROM makina WHERE model_makina = '"
                +rent_model.getSelectionModel().getSelectedItem()+"'";

        connection = handler.getConnection();

        try{
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();

            if(result.next()){
                price = result.getDouble("cmimi_makina");
            }
            // price * the count day you want to use the car
            totalP = (price * countDate);
            // DISPLAY TOTAL
            rent_total.setText("$" + String.valueOf(totalP));

        }catch(Exception e){e.printStackTrace();}

    }


    private String[] genderList = {"Male", "Female", "Others"};

    public void rentCarGender() {

        List<String> listG = new ArrayList<>();

        for (String data : genderList) {
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);

        rent_gender.setItems(listData);

    }

    public void rentCarCarId() {

        String sql = "SELECT * FROM makina WHERE statusiMakina  = 'Available'";

        connection = handler.getConnection();

        try {
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("makina_id"));
            }

            rent_carid.setItems(listData);

            rentCarBrand();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<carData> rentCarListData() {

        ObservableList<carData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM makina";

        connection = handler.getConnection();

        try {
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();

            carData carD;

            while (result.next()) {
                carD = new carData(result.getInt("makina_id"), result.getString("brand_makina"),
                        result.getString("model_makina"), result.getDouble("cmimi_makina"),
                        result.getString("statusiMakina"),
                        result.getString("foto_makina"),
                        result.getDate("date"));

                listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    public void rentCarBrand() {

        String sql = "SELECT * FROM makina WHERE makina_id = '"
                + rent_carid.getSelectionModel().getSelectedItem() + "'";

        connection =handler.getConnection();

        try {
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("brand_makina"));
            }

            rent_brand.setItems(listData);

            rentCarModel();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void rentCarModel() {

        String sql = "SELECT * FROM makina WHERE brand = '"
                + rent_brand.getSelectionModel().getSelectedItem() + "'";

        connection =handler.getConnection();

        try {
            pst = connection.prepareStatement(sql);
            result = pst.executeQuery();

            ObservableList listData = FXCollections.observableArrayList();

            while (result.next()) {
                listData.add(result.getString("model_makina"));
            }

            rent_model.setItems(listData);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ObservableList<carData> rentCarList;

    public void rentCarShowListData() {
        rentCarList = rentCarListData();
        for (carData car:rentCarList
        ) {
            rent_col_carid.setCellValueFactory(new PropertyValueFactory<>("carId"));
            rent_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            rent_col_model.setCellValueFactory(new PropertyValueFactory<>("model"));
            rent_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
            rent_col_status.setCellValueFactory(new PropertyValueFactory<>("status "));

            rent_tableView.setItems(rentCarList);
        }

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        connection = handler.getConnection(); // Get the database connection
        carRepository = new CarRepository(connection); // Initialize the CarRepository with the connection
    }
}