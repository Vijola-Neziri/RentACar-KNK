package repository;

import ConnectionMysql.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class KlientRepository {
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;
    private ResultSet result;

    public KlientRepository() {
        handler = (DBHandler) DBHandler.getConnection();
    }

    public void rentCarSave(TextField rent_firstName, TextField rent_lastName, ComboBox<String> rent_gender,
                            Label rent_balance, Label rent_total, TextField rent_amount, ComboBox<String> rent_carid,
                            ComboBox<String> rent_brand, ComboBox<String> rent_model, DatePicker rent_dateRented,
                            DatePicker rent_dateReturn) {
        String insertQuery = "INSERT INTO klientet (klient_id, emri_klient, mbimeri_klient, gjinia, makina_id, " +
                "brand_makina, model_makina, total, date_rented, date_returned) VALUES (?,?,?,?,?,?,?,?,?,?)";

        int customerId = rentCustomerId();
        String firstName = rent_firstName.getText();
        String lastName = rent_lastName.getText();
        String gender = rent_gender.getValue();

        int carId = Integer.parseInt(rent_carid.getValue());
        String brand = rent_brand.getValue();
        String model = rent_model.getValue();

        LocalDate dateRented = rent_dateRented.getValue();
        LocalDate dateReturned = rent_dateReturn.getValue();

        double total = calculateTotal(carId, brand, model, dateRented, dateReturned);

        connection = handler.getConnection();

        try {
            pst = connection.prepareStatement(insertQuery);
            pst.setInt(1, customerId);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, gender);
            pst.setInt(5, carId);
            pst.setString(6, brand);
            pst.setString(7, model);
            pst.setDouble(8, total);
            pst.setObject(9, dateRented);
            pst.setObject(10, dateReturned);
            pst.executeUpdate();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Car rented successfully.");
            alert.showAndWait();

            rentClear(rent_firstName, rent_lastName, rent_gender, rent_balance, rent_total, rent_amount,
                    rent_carid, rent_brand, rent_model, rent_dateRented, rent_dateReturn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int rentCustomerId() {
        int customerId = 0;
        String sql = "SELECT MAX(klient_id) as max_id FROM klientet";

        connection = handler.getConnection();

        try {
            pst = connection.prepareStatement(sql);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                customerId = result.getInt("max_id") + 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }

    public void rentClear(TextField rent_firstName, TextField rent_lastName, ComboBox<String> rent_gender,
                          Label rent_balance, Label rent_total, TextField rent_amount, ComboBox<String> rent_carid,
                          ComboBox<String> rent_brand, ComboBox<String> rent_model, DatePicker rent_dateRented,
                          DatePicker rent_dateReturn) {
        rent_firstName.clear();
        rent_lastName.clear();
        rent_gender.getSelectionModel().clearSelection();
        rent_balance.setText("");
        rent_total.setText("");
        rent_amount.clear();
        rent_carid.getSelectionModel().clearSelection();
        rent_brand.getSelectionModel().clearSelection();
        rent_model.getSelectionModel().clearSelection();
        rent_dateRented.setValue(null);
        rent_dateReturn.setValue(null);
    }public double calculateTotal(int carId, String brand, String model, LocalDate dateRented, LocalDate dateReturned) {
        double total = 0;

        // Implement the logic here to calculate the total rent amount
        // based on the selected car, brand, model, and dates

        return total;
    }

}