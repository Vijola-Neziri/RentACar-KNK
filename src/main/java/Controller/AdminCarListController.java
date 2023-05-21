package Controller;
import ConnectionMysql.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.makina;
import repository.CarRepository;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminCarListController implements Initializable {
    @FXML
    private Button DashboardBtn;

    @FXML
    private Button carBtn;

    @FXML
    private Button carList;

    @FXML
    private Button close;

    @FXML
    private TableColumn<?, ?> columnBrand;

    @FXML
    private TableColumn<?, ?> columnCarId;

    @FXML
    private TableColumn<?, ?> columnDate;

    @FXML
    private TableColumn<?, ?> columnModel;

    @FXML
    private TableColumn<?, ?> columnPrice;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private Button customers;

    @FXML
    private Button dataAnalysis;

    @FXML
    private Button minimize;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TableView<makina> tableCarList;
    private ObservableList<makina> data;

    private CarRepository carRepository;
    private ResultSet rs = null;
    private PreparedStatement pst = null;


        public void close(){
            System.exit(0);
        }

    private void fillTable() {
        for (makina car:this.data) {
            System.out.println(car.getMakina_id());
            columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));

            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableCarList.setItems(data);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = loadDataFromDatabase();
        fillTable();
    }
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

        public void minimize(){
            Stage stage = (Stage)main_form.getScene().getWindow();
            stage.setIconified(true);
        }

    @FXML
    void signOut(ActionEvent event) {

    }
    }

//    @FXML
//    void minimize(ActionEvent event) {
//
//    }

