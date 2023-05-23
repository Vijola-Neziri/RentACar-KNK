package Controller;

import ConnectionMysql.DBHandler;
import app.AdminHomeForm;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;


public class AdminStatisticsController implements Initializable {
    @FXML
    private Button DashboardBtn;

    @FXML
    private Button carBtn;

    @FXML
    private Button carList;

    @FXML
    private Button close;

    @FXML
    private Button customers;

    @FXML
    private Button dataAnalysis;

    @FXML
    private AnchorPane main_form;
    @FXML
    private LineChart<String, Number> home_customerChart;

    @FXML
    private BarChart<String, Number> home_incomeChart;
    @FXML
    private Button minimize;
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;




    public void homeIncomeChart() {
        home_incomeChart.getData().clear();
        String sql = "SELECT date_rented, SUM(total) AS total FROM klientet GROUP BY date_rented ORDER BY TIMESTAMP(date_rented) ASC LIMIT 6";
        connection = handler.getConnection();
        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<>(); // Specify the generic types explicitly
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString("date_rented"), result.getDouble("total")));
            }
            home_incomeChart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeCustomerChart() {
        home_customerChart.getData().clear();
        String sql = "SELECT date_rented, COUNT(klient_id) AS count FROM klientet GROUP BY date_rented ORDER BY TIMESTAMP(date_rented) ASC LIMIT 4";
        connection = handler.getConnection();
        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<>(); // Specify the generic types explicitly
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString("date_rented"), result.getInt("count")));
            }
            home_customerChart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
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



    @FXML
    private Button carListBtn;
    @FXML
    void carList(ActionEvent event) throws IOException {
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
    void carReg(ActionEvent event) throws IOException {
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
    private Button carRentBtn;
    @FXML
    private Button customersBtn;
    @FXML
    private Button dataAnalysisBtn;


    @FXML
    void carRent(ActionEvent event) throws IOException {
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
    void home(ActionEvent event) throws IOException {
        DashboardBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminHome.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        connection = handler.getConnection();
//        homeAvailableCars();
//        homeTotalIncome();
//        homeTotalCustomers();
//        home_totalCars();
//        home_unavailableCars();
        homeIncomeChart();
        homeCustomerChart();
    }
}
