package Controller;
import ConnectionMysql.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Rent;
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

public class AdminRentController implements Initializable {
    @FXML
    private AnchorPane main_form;
    @FXML
    private TableView<Rent> AdminTableRent;

    @FXML
    private Button close;

    @FXML
    private TableColumn<Rent, Double> rent_total;

    @FXML
    private TableColumn<Rent, Date> columnDate;

    @FXML
    private TableColumn<Rent, Date> rent_dateRented;

    @FXML
    private TableColumn<Rent, Date> rent_dateReturn;

    @FXML
    private TableColumn<Rent, Integer> columnKlientId;

    @FXML
    private TableColumn<Rent, Integer> columnCarId;

    @FXML
    private TableColumn<Rent, Integer> columnid;

    @FXML
    private Button minimize;


    private CarRepository carRepository;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    private ObservableList<Rent> data;

    private void fillTable() {
        for (Rent car:this.data) {
            System.out.println(car.getId());
            columnid.setCellValueFactory(new PropertyValueFactory<>("id"));
            columnCarId.setCellValueFactory(new PropertyValueFactory<>("klient_id"));
            columnKlientId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("data_e_rezervimit"));
            rent_dateRented.setCellValueFactory(new PropertyValueFactory<>("data_e_fillimit_te_qirase"));
            rent_dateReturn.setCellValueFactory(new PropertyValueFactory<>("data_e_mbarimit_te_qirase"));
            rent_total.setCellValueFactory(new PropertyValueFactory<>("cmimi_total"));
            AdminTableRent.setItems(data);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = loadDataFromDatabase();
        fillTable();
    }
    ObservableList<Rent> loadDataFromDatabase() {
        ObservableList<Rent> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM rent";
            Connection con = DBHandler.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                int Id = rs.getInt("id");
                int klient_id = rs.getInt("klient_id");
                int makina_id= rs.getInt("makina_id");
                Date dataERezervimit = rs.getDate("data_e_rezervimit");
                Date  fillimi_qirase = rs.getDate("data_e_fillimit_te_qirase");
                Date mbarimiQirase = rs.getDate("data_e_mbarimit_te_qirase");
                Double cmimiTotal = rs.getDouble("cmimi_total");
//                LocalDate fillimi_qirase = fillimi_qiraseSQL.toLocalDate();

                Rent rent = new Rent(Id, klient_id, makina_id, dataERezervimit, fillimi_qirase,
                        mbarimiQirase, cmimiTotal);
                data.add(rent);

                System.out.println(rent.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    void carList(ActionEvent event) {

    }

    @FXML
    void carReg(ActionEvent event) {

    }

    @FXML
    void carRent(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void customers(ActionEvent event) {

    }

    @FXML
    void dataAnalysis(ActionEvent event) {

    }

    @FXML
    void home(ActionEvent event) {

    }

    @FXML
    void minimize(ActionEvent event) {

    }

    @FXML
    void signOut(ActionEvent event) {

    }

}




