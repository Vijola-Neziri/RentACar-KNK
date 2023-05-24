package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.makina;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.*;

public class UserRentController implements Initializable {

    @FXML
    private RadioButton alButton;

    @FXML
    private RadioButton enButton;

    @FXML
    private Label model_label;
    @FXML
    private Label amount_label;

    @FXML
    private Label balance_label;

    @FXML
    private Label brand_label;

    @FXML
    private Label dateRented_label;

    @FXML
    private Label dateReturned_label;
    @FXML
    private Label carid_label;
    @FXML
    private Label firstName_label;
    @FXML
    private Label lastName_label;

    @FXML
    private Label gebder_label;

    @FXML
    private Button help;
    @FXML
    private Button carlist;

    @FXML
    private Button close;

    @FXML
    private TableColumn<makina ,String> columnBrand;

    @FXML
    private TableColumn<makina, Integer> columnCarId;

    @FXML
    private TableColumn<makina, Date> columnDate;

    @FXML
    private TableColumn<makina, String> columnModel;

    @FXML
    private TableColumn<makina, String > columnPhoto;

    @FXML
    private TableColumn<makina, Double> columnPrice;

    @FXML
    private TableColumn<makina, String> columnStatus;

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
    private Button rentBtn;

    @FXML
    private Button rentCar_btn;

    @FXML
    private TextField rent_amount;

    @FXML
    private ComboBox<String> rent_brand;

    @FXML
    private ComboBox<Integer> rent_carid;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private TextField rent_firstName;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<String> rent_model;

    @FXML
    private Label rent_tableView1;

    @FXML
    private TableView<makina> tableCars;

    @FXML
    private Label user;

    @FXML
    private Label username;


    @FXML
    void RentCar(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    public  void Help(ActionEvent event) throws IOException {
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
    private Label rent_balance;

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

    public  void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        alButton.setToggleGroup(languageToggleGroup);
        enButton.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if(newToggle == alButton) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.AL_SQ", currentLocale);
                carid_label.setText(bundle.getString("Car.id.label"));
                brand_label.setText(bundle.getString("Brand.label"));
                model_label.setText(bundle.getString("Model.label"));
                firstName_label.setText(bundle.getString("First.name.label"));
                lastName_label.setText(bundle.getString("Last.name.label"));
                gebder_label.setText(bundle.getString("Gender1.label "));
                dateRented_label.setText(bundle.getString("Date.rented.label"));
                dateReturned_label.setText(bundle.getString("Date.returned.label"));
                amount_label.setText(bundle.getString("Amount.label"));
                balance_label.setText(bundle.getString("Balance.label"));
                rent_firstName.setPromptText(bundle.getString("First.name.label"));
                rent_lastName.setPromptText(bundle.getString("Last.name.label"));
                columnCarId.setText(bundle.getString("Car.id.col"));
                columnBrand.setText(bundle.getString("Brand.col"));
                columnModel.setText(bundle.getString("Model.col"));
                columnPrice.setText(bundle.getString("Price.col"));
                columnStatus.setText(bundle.getString("Status.col"));
                columnPhoto.setText(bundle.getString("Photo.col"));
            }else if(newToggle == enButton)  {
                Locale currentLocale = new Locale("en", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.US_EN", currentLocale);
                carid_label.setText(bundle.getString("Car.id.label"));
                brand_label.setText(bundle.getString("Brand.label"));
                model_label.setText(bundle.getString("Model.label"));
                firstName_label.setText(bundle.getString("First.name.label"));
                lastName_label.setText(bundle.getString("Last.name.label"));
                gebder_label.setText(bundle.getString("Gender1.label "));
                dateRented_label.setText(bundle.getString("Date.rented.label"));
                dateReturned_label.setText(bundle.getString("Date.returned.label"));
                amount_label.setText(bundle.getString("Amount.label"));
                balance_label.setText(bundle.getString("Balance.label"));
                rent_firstName.setPromptText(bundle.getString("First.name.label"));
                rent_lastName.setPromptText(bundle.getString("Last.name.label"));
                columnCarId.setText(bundle.getString("Car.id.col"));
                columnBrand.setText(bundle.getString("Brand.col"));
                columnModel.setText(bundle.getString("Model.col"));
                columnPrice.setText(bundle.getString("Price.col"));
                columnStatus.setText(bundle.getString("Status.col"));
                columnPhoto.setText(bundle.getString("Photo.col"));

            }

        });
        languageToggleGroup.selectToggle(alButton);
    }

    public void rentPay() {
        rentCustomerId();
        String sql = "INSERT INTO klientet " +
                "(emri_klient, mbimeri_klient, gjinia, makina_id, brand_makina, model_makina, total, date_rented, date_returned) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        connection = handler.getConnection();
        try {
            Alert alert;
            if (rent_firstName.getText().isEmpty()
                    || rent_lastName.getText().isEmpty()
                    || rent_gender.getSelectionModel().getSelectedItem() == null
                    || rent_carid.getSelectionModel().getSelectedItem() == null
                    || rent_brand.getSelectionModel().getSelectedItem() == null
                    || rent_model.getSelectionModel().getSelectedItem() == null
                    || totalP == 0 || rent_amount.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    pst.setString(1, rent_firstName.getText());
                    pst.setString(2, rent_lastName.getText());
                    pst.setString(3, (String) rent_gender.getSelectionModel().getSelectedItem());
                    pst.setInt(4, (Integer) rent_carid.getSelectionModel().getSelectedItem());
                    pst.setString(5, (String) rent_brand.getSelectionModel().getSelectedItem());
                    pst.setString(6, (String) rent_model.getSelectionModel().getSelectedItem());
                    pst.setInt(7, (int) totalP);
                    pst.setDate(8, java.sql.Date.valueOf(rent_dateRented.getValue()));
                    pst.setDate(9, java.sql.Date.valueOf(rent_dateReturn.getValue()));
                    pst.executeUpdate();

                    ResultSet generatedKeys = pst.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        System.out.println("Generated ID: " + generatedId);
                        // SET THE STATUS OF CAR TO NOT AVAILABLE
                        String updateCar = "UPDATE makina SET statusiMakina = 'Not Available' WHERE makina_id = ?";
                        PreparedStatement updateCarStatement = connection.prepareStatement(updateCar);
                        updateCarStatement.setInt(1, (Integer) rent_carid.getSelectionModel().getSelectedItem());
                        updateCarStatement.executeUpdate();

                        // Insert data into the rent table
                        String rentInsertQuery = "INSERT INTO rent (klient_id, makina_id, data_e_rezervimit, data_e_fillimit_te_qirase, data_e_mbarimit_te_qirase, cmimi_total) " +
                                "VALUES (?, ?, ?, ?, ?, ?)";
                        PreparedStatement rentInsertStatement = connection.prepareStatement(rentInsertQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                        rentInsertStatement.setInt(1, CustomerID()); // Replace with appropriate method to get the customer ID
                        rentInsertStatement.setInt(2, (Integer) rent_carid.getSelectionModel().getSelectedItem());
                        rentInsertStatement.setDate(3, java.sql.Date.valueOf(rent_dateRented.getValue()));
                        rentInsertStatement.setDate(4, java.sql.Date.valueOf(rent_dateRented.getValue()));
                        rentInsertStatement.setDate(5, java.sql.Date.valueOf(rent_dateReturn.getValue()));
                        rentInsertStatement.setFloat(6, (float) totalP);
                        rentInsertStatement.executeUpdate();

                        ResultSet rentGeneratedKeys = rentInsertStatement.getGeneratedKeys();
                        if (rentGeneratedKeys.next()) {
                            int rentGeneratedId = rentGeneratedKeys.getInt(1);
                            System.out.println("Rent Generated ID: " + rentGeneratedId);

                            // Rest of the code remains the same
                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setHeaderText(null);
                            alert.setContentText("Successful!");
                            alert.showAndWait();

                            rentCarShowListData();
                            rentClear();
                        } else {
                            // Handle the case when rent ID is not generated
                            System.err.println("Failed to generate rent ID.");
                        }
                    } else {
                        // Handle the case when customer ID is not generated
                        System.err.println("Failed to generate customer ID.");
                    }
                }
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                String errorMessage = "Cannot add or update a child row: a foreign key constraint fails.\n" +
                        "Please make sure you have selected valid data.";
                System.err.println(errorMessage);

                Alert  alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(errorMessage);
                alert.showAndWait();
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int CustomerID() {
        int customerId = 0;
        String sql = "SELECT klient_id FROM klientet WHERE emri_klient = ? AND mbimeri_klient = ?";
        connection = handler.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, rent_firstName.getText());
            preparedStatement.setString(2, rent_lastName.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                customerId = resultSet.getInt("klient_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }

    private void clearFields() {
        rent_firstName.clear();
        rent_lastName.clear();
        rent_gender.getSelectionModel().clearSelection();
        rent_carid.getSelectionModel().clearSelection();
        rent_brand.getSelectionModel().clearSelection();
        rent_model.getSelectionModel().clearSelection();
        rent_amount.clear();
        rent_dateRented.setValue(null);
        rent_dateReturn.setValue(null);
    }




    private ObservableList<makina> rentCarList;

    public void rentCarShowListData() {
        rentCarList = rentCarListData();
        columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));
        columnPhoto.setCellValueFactory(new PropertyValueFactory<>("foto_makina"));

        tableCars.setItems(rentCarList);
    }


    @FXML
    void CarList(ActionEvent event) throws IOException {
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
    private int customerId;

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
        }else
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


    private int countDate;

    @FXML
    private Label rent_total;
    private double totalP;

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


    public void rentDisplayTotal(){
        rentDate();
        double price = 0;
        String sql = "SELECT cmimi_makina, model_makina FROM makina WHERE model_makina = '"
                +rent_model.getSelectionModel().getSelectedItem()+"'";
        connection = DBHandler.getConnection();
        try{
            pst = connection.prepareStatement(sql);
            rs= pst.executeQuery();
            if(rs.next()){
                price = rs.getDouble("cmimi_makina");
            }
            // price * the count day you want to use the car
            totalP = (price * countDate);
            // DISPLAY TOTAL
            rent_total.setText("$" + String.valueOf(totalP));
        }catch(Exception e){e.printStackTrace();}
    }

    private String[] genderList = {"Male", "Female", "Others"};




    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    private ResultSet rs ;
    private PreparedStatement pst ;

    private void fillTable() {
        for (makina car:this.data) {
            System.out.println(car.getMakina_id());
            columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));
            columnPhoto.setCellValueFactory(new PropertyValueFactory<>("foto_makina"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableCars.setItems(data);
        }
    }
    private ObservableList<makina> data;

    ObservableList<makina> loadDataFromDatabase() {
        ObservableList<makina> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM makina";
            Connection con = DBHandler.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                int carId = rs.getInt("makina_id");
                String brand = rs.getString("brand_makina");
                String model = rs.getString("model_makina");
                double price = rs.getDouble("cmimi_makina");
                String status = rs.getString("statusiMakina");
                String photo = rs.getString("foto_makina");

                makina car = new makina(carId, brand, model, price, status, photo, new Date());
                data.add(car);

                System.out.println(car.getMakina_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    private Connection connection;
    private DBHandler handler = new DBHandler();



    @FXML
    void rentCarCarId() {
        String sql = "SELECT * FROM makina WHERE statusiMakina  = 'Available'";
        connection = DBHandler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            ObservableList<Integer> listData = FXCollections.observableArrayList();
            while (rs.next()) {
                listData.add(rs.getInt("makina_id"));
            }
            rent_carid.setItems(listData);
            rentCarBrand();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void rentCarBrand() {
        Integer selectedCarId = rent_carid.getValue();
        if (selectedCarId != null) {
            String sql = "SELECT * FROM makina WHERE makina_id = ?";
            connection = DBHandler.getConnection();
            try {
                pst = connection.prepareStatement(sql);
                pst.setInt(1, selectedCarId);
                rs = pst.executeQuery();

                ObservableList<String> listData = FXCollections.observableArrayList();
                while (rs.next()) {
                    String brand = rs.getString("brand_makina");
                    listData.add(brand);
                }
                rent_brand.setItems(listData);
                rentCarModel();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void rentCarModel() {
        String selectedBrand = (String) rent_brand.getValue();
        if (selectedBrand != null) {
            String sql = "SELECT * FROM makina WHERE brand_makina = ?";
            connection = DBHandler.getConnection();
            try {
                pst = connection.prepareStatement(sql);
                pst.setString(1, selectedBrand);
                rs = pst.executeQuery();

                ObservableList<String> listData = FXCollections.observableArrayList();
                while (rs.next()) {
                    String model = rs.getString("model_makina");
                    listData.add(model);
                }
                rent_model.setItems(listData);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    private DatePicker rent_dateRented;
    @FXML
    private  Label usrLabel;
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
                countDate = (int) ChronoUnit.DAYS.between(rent_dateRented.getValue(), rent_dateReturn.getValue());

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
    public void rentCarGender() {
        List<String> listG = new ArrayList<>();
        for (String data : genderList) {
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        rent_gender.setItems(listData);
    }
    public ObservableList<makina> rentCarListData() {
        ObservableList<makina> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM makina";
        connection = DBHandler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            makina carD;
            while (rs.next()) {
                carD = new makina(rs.getInt("makina_id"), rs.getString("brand_makina"),
                        rs.getString("model_makina"), rs.getDouble("cmimi_makina"),
                        rs.getString("statusiMakina"),
                        rs.getString("foto_makina"),
                        rs.getDate("date"));
                listData.add(carD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBHandler DBHandler = new DBHandler();
        changeLanguage();

        data = loadDataFromDatabase();
        String loggedInUsername = LoginFormController.getLoggedInUsername();

        displayUsername(loggedInUsername);
        fillTable();
        rentCarCarId();
        rentCarBrand();
        rentCarModel();
        rentCarGender();
        rentCarListData();
        rentCarShowListData();
    }


}