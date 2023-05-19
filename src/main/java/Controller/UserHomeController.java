package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable {
    @FXML
    private Button availableCars_btn;

    @FXML
    private Button close;
    @FXML
    private Button carlist;

    @FXML
    private Label home_availableCars;

    @FXML
    private Button home_btn;



    @FXML
    private AnchorPane home_form;



    @FXML
    private Label home_totalCustomers;

    @FXML
    private Label home_totalIncome;
    @FXML
    private LineChart<String, Number> home_customerChart;

    @FXML
    private BarChart<String, Number> home_incomeChart;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button rentCar_btn;

    @FXML
    private Label user;

    @FXML
    private Label username;
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    public void homeAvailableCars() {
        String sql = "SELECT COUNT(makina_id) AS count FROM makina WHERE statusiMakina = 'Available'";
        connection = handler.getConnection();
        int countAC = 0;
        try {
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                countAC = result.getInt("count");
            }
            home_availableCars.setText(String.valueOf(countAC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeTotalIncome() {
        String sql = "SELECT SUM(total) AS totalIncome FROM klientet";
        double sumIncome = 0;
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                sumIncome = result.getDouble("totalIncome");
            }
            home_totalIncome.setText("$" + String.valueOf(sumIncome));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homeTotalCustomers() {
        String sql = "SELECT COUNT(klient_id) AS count FROM klientet";
        int countTC = 0;
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                countTC = result.getInt("count");
            }
            home_totalCustomers.setText(String.valueOf(countTC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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


    @FXML
    public void CarList(ActionEvent event) throws  IOException {
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

        @Override
    public void initialize(URL location, ResourceBundle resources) {
            handler = new DBHandler();
            connection = handler.getConnection();
            homeAvailableCars();
            homeTotalIncome();
            homeTotalCustomers();
            homeIncomeChart();
            homeCustomerChart();
    }
}
