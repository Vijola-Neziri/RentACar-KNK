package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
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
import java.util.*;
import java.util.Date;

public class UserRent implements Initializable {

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
    private DatePicker ent_dateRented;

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




    public void rentPay(){
        rentCustomerId();
        String sql = "INSERT INTO klientet "
                + "(klient_id, emri_klient, mbiemri_klient, gjinia, makina_id, brand_makina"
                + ", model_makina, total, date_rented, date_returned ) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        connection = DBHandler.getConnection();
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
                    pst.setInt(5, (Integer )rent_carid.getSelectionModel().getSelectedItem());
                    pst.setString(6, (String)rent_brand.getSelectionModel().getSelectedItem());
                    pst.setString(7, (String)rent_model.getSelectionModel().getSelectedItem());
                    pst.setString(8, String.valueOf(totalP));
                    pst.setString(9, String.valueOf(ent_dateRented.getValue()));
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

    }
    private double amount;
    private double balance;
    public void rentAmount(){

    }
    private int countDate;

    @FXML
    private Label rent_total;
    private double totalP;

    @FXML
    private Label rent_balance;
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
            rs = pst.executeQuery();
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


    private ObservableList<makina> rentCarList;

    public void rentCarShowListData() {
    }


    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }


    private ResultSet rs = null;
    private PreparedStatement pst = null;

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
            ent_dateRented.setValue(null);
            rent_dateReturn.setValue(null);
        }else{
            if(rent_dateReturn.getValue().isAfter(ent_dateRented.getValue())){
                // COUNT THE DAY
                countDate = rent_dateReturn.getValue().compareTo(ent_dateRented.getValue());
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something wrong :3");
                alert.showAndWait();
                // INCREASE OF 1 DAY ONCE THE USER CLICKED THE SAME DAY
                rent_dateReturn.setValue(ent_dateRented.getValue().plusDays(1));
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
        data = loadDataFromDatabase();
        fillTable();
        rentCarCarId();
        rentCarBrand();
        rentCarModel();
        rentCarGender();
        rentCarListData();
    }

}
