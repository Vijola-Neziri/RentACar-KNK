package Controller;

import ConnectionMysql.DBHandler;
import app.AdminHomeForm;
import app.LoginForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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

public class AdminHomeController implements Initializable {
    @FXML
    private Button DashboardBtn;

    @FXML
    private Button carBtn;

    @FXML
    private Button carListBtn;

    @FXML
    private Button close;

    @FXML
    private Button customers;

    @FXML
    private Button dataAnalysisBtn;

    @FXML
    private Label home_availableCars;

    @FXML
    private Button home_btn;

    @FXML
    private Label home_totalCars;

    @FXML
    private Label home_totalCustomers;

    @FXML
    private Label home_totalIncome;

    @FXML
    private Label home_unavailableCars;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;
    @FXML
    private Button carRentBtn;

    @FXML
    private Button customersBtn;
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    public void home_totalCars() {
        String sql = "SELECT COUNT(makina_id) FROM makina WHERE makina_id != ''";
        connection = handler.getConnection();
        try{

            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while(result.next()){

                String totalStudent = result.getString("count(makina_id)");

                home_totalCars.setText(totalStudent);

            }

        }catch(Exception e){}

    }





    public void homeAvailableCars(){
        String sql = "SELECT COUNT(makina_id) FROM makina WHERE statusiMakina = 'Available'";
        connection = handler.getConnection();
        int countAC = 0;
        try{
        pst = connection.prepareStatement(sql);
        ResultSet result = pst.executeQuery();
        while(result.next()){
        countAC = result.getInt("COUNT(makina_id)");
        }
        home_availableCars.setText(String.valueOf(countAC));
        }catch(Exception e){e.printStackTrace();}
    }
    public void homeUnavailableCars() {
        String sql = "SELECT COUNT(makina_id) FROM makina WHERE statusiMakina = 'Not Available' ";

        connection = handler.getConnection();
        int countAC = 0;
        try{
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            while(result.next()){
                countAC = result.getInt("COUNT(makina_id)");
            }

            home_unavailableCars.setText(String.valueOf(countAC));

        }catch(Exception e){e.printStackTrace();}
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

    @FXML
    public void signOut(ActionEvent event) {
        // Get the current stage/window
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        // Close the window
        currentStage.close();
    }



    @FXML
    public  void home(ActionEvent event) throws IOException {
        home_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/AdminHome.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
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
    public void carReg(ActionEvent event) throws  IOException {
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
    public void carList(ActionEvent event) throws  IOException {
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
    public void dataAnalysis(ActionEvent event) throws IOException {
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
    public void carRent(ActionEvent event) throws IOException {
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
    public void customers(ActionEvent event) throws IOException {
        customersBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/customer.fxml"));
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
        homeUnavailableCars();
        home_totalCars();
    //        homeIncomeChart();
//        homeCustomerChart();
    }
}
