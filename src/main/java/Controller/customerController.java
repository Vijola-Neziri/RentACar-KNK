package Controller;

import ConnectionMysql.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Klientet;
import models.makina;
import repository.CarRepository;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class customerController  implements Initializable {

    @FXML
    private Button carlist;
    @FXML
    private TableColumn<Klientet, Date> Date_rentedId;

    @FXML
    private TableColumn<Klientet, Date> Date_returnedId;

    @FXML
    private Button close;

    @FXML
    private TableColumn<Klientet, String> columnBrand;

    @FXML
    private TableColumn<Klientet, String> columnEmri;

    @FXML
    private TableColumn<Klientet, String> columnGjinia;

    @FXML
    private TableColumn<Klientet, Integer> columnKlientId;

    @FXML
    private TableColumn<Klientet, String> columnMakina;

    @FXML
    private TableColumn<Klientet, String> columnMbiemri;

    @FXML
    private TableColumn<Klientet, String> columnModel;
    @FXML
    private TableView<Klientet> tableKlientet;

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
    private Button rentCar_btn;

    @FXML
    private Label rent_tableView1;

    @FXML
    private TableView<Klientet> tableCars;
    @FXML
    private TableColumn<Klientet, Date> columnDate;

    @FXML
    private Label user;

    @FXML
    private Label username;

    @FXML
    void CarList(ActionEvent event) {

    }

    @FXML
    void RentCar(ActionEvent event) {

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

    private ObservableList<Klientet> data;
    private CarRepository carRepository;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    private void fillTable() {
        columnKlientId.setCellValueFactory(new PropertyValueFactory<>("klient_id"));
        columnEmri.setCellValueFactory(new PropertyValueFactory<>("emri_klient"));
        columnMbiemri.setCellValueFactory(new PropertyValueFactory<>("mbimeri_klient"));
        columnGjinia.setCellValueFactory(new PropertyValueFactory<>("gjinia"));
        columnMakina.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date_rented"));

        tableKlientet.setItems(data);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = loadDataFromDatabase();
        fillTable();
    }

    private ObservableList<Klientet> loadDataFromDatabase() {
        ObservableList<Klientet> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Klientet";
            Connection con = DBHandler.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                int KlientId = rs.getInt("klient_id");
                String Emri = rs.getString("emri_klient");
                String Mbiemri = rs.getString("mbimeri_klient");
                String Gjinia = rs.getString("gjinia");
                int Makina = rs.getInt("makina_id");
                String Brand = rs.getString("brand_makina");
                String Model = rs.getString("model_makina");




                Klientet klient = new Klientet(KlientId, Emri, Mbiemri, Gjinia, Makina, Brand, Model, 0, LocalDate.now(), LocalDate.now());

                data.add(klient);

                System.out.println(klient.getKlient_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}

