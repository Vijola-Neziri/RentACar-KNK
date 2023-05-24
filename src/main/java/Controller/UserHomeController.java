package Controller;

import ConnectionMysql.DBHandler;
import app.LoginForm;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserHomeController implements Initializable {

    @FXML
    private MenuBar Help;
    @FXML
    private Label welcome_label;
    @FXML
    private RadioButton enButton;
    @FXML
    private RadioButton alButton;

    @FXML
    private Label totalCustomers_label;

    @FXML
    private Label totalIncome_label;

    @FXML
    private Label availableCars_label;

    @FXML
    private Button carlist;

    @FXML
    private Label home_availableCars;





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
    private Button helpbtn;

    @FXML
    private Label  usrLabel;
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet resultSet;

    public void homeAvailableCars() {
        String sql = "SELECT COUNT(makina_id) AS count FROM makina WHERE statusiMakina = 'Available'";
        connection = handler.getConnection();
        int countAC = 0;
        try {
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
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



    @FXML
    private MenuItem help;

    @FXML
    public void HelpBtn(ActionEvent event) throws IOException {
        Help.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/Help.fxml"));
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

    public void homeIncomeChart() {
        home_incomeChart.getData().clear();
        String sql = "SELECT date_rented, SUM(total) AS total FROM klientet GROUP BY date_rented ORDER BY TIMESTAMP(date_rented) ASC LIMIT 6";
        connection = handler.getConnection();
        try {
            XYChart.Series<String, Number> chart = new XYChart.Series<>();
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
    void signOut(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Logout Confirmation");
        confirmation.setHeaderText("Are you sure you want to logout?");
        confirmation.setContentText("Press OK to confirm.");


        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);


        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOK) {
            Platform.exit();
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
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserRent1.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }


    public  void changeLanguage() {
        ToggleGroup languageToggleGroup = new ToggleGroup();
        alButton.setToggleGroup(languageToggleGroup);
        enButton.setToggleGroup(languageToggleGroup);
        languageToggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if(newToggle == alButton) {
                Locale currentLocale = new Locale("sq", "AL");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.AL_SQ", currentLocale);
                totalIncome_label.setText(bundle.getString("total.income.label"));
                totalCustomers_label.setText(bundle.getString("total.customers.label"));
                availableCars_label.setText(bundle.getString("available.cars.label"));
                welcome_label.setText(bundle.getString("Welcome.label"));




            }else if(newToggle == enButton)  {
                Locale currentLocale = new Locale("en", "US");
                ResourceBundle bundle = ResourceBundle.getBundle("translations.US_EN", currentLocale);
                totalIncome_label.setText(bundle.getString("total.income.label"));
                totalCustomers_label.setText(bundle.getString("total.customers.label"));
                availableCars_label.setText(bundle.getString("available.cars.label"));
                welcome_label.setText(bundle.getString("Welcome.label"));

            }

        });
        languageToggleGroup.selectToggle(alButton);
    }
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        String loggedInUsername = LoginFormController.getLoggedInUsername();

     displayUsername(loggedInUsername);
        // Additional code for other initialization tasks
        homeAvailableCars();
        homeTotalIncome();
        homeTotalCustomers();
        homeIncomeChart();
        homeCustomerChart();
        changeLanguage();
    }

}
