package Controller;

import ConnectionMysql.DBHandler;
import app.AdminHomeForm;
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
import models.Rent;
import repository.CarRepository;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
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
    @FXML
    private Button carListBtn;


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
    void carList(ActionEvent event)throws IOException {
        carListBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminCarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    public Button carBtn;

    @FXML
    public Button carRentBtn;
    @FXML
    public Button customersBtn;
    @FXML
    public Button dataAnalysisBtn;
    @FXML
    void carReg(ActionEvent event) throws IOException{
        carBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminCarRegistration.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void carRent(ActionEvent event) throws IOException{
        carRentBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminRent.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void customers(ActionEvent event) throws IOException {
        customersBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/customer.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    void dataAnalysis(ActionEvent event) throws IOException{
        dataAnalysisBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminStatistics.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
@FXML
private Button DashboardBtn;
    @FXML
    void home(ActionEvent event) throws  IOException{
        DashboardBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminHome.fxml"));
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

}




