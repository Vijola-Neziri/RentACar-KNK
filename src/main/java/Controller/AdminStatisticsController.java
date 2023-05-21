package Controller;

import ConnectionMysql.DBHandler;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    }

    @FXML
    void minimize(ActionEvent event) {

    }
    @FXML
    void signOut(ActionEvent event) {

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
