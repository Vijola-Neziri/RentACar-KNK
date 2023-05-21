package repository;

import ConnectionMysql.DBHandler;
import javafx.scene.control.Alert;
import models.Admin;
import repository.Interface.AdminInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository implements AdminInterface {
    private DBHandler handler;
    private Connection connection;
    private PreparedStatement pst;

    public AdminRepository() {
        handler = new DBHandler();
    }

    public Admin getAdminByUsernameAndPassword(String username, String password) {
        connection = handler.getConnection();
        String query = "SELECT * FROM Admins WHERE admin_username=? AND admin_password=?";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int admin_ID = rs.getInt("Admin_ID");
                String admin_emri = rs.getString("Admin_emri");
                String telefoni_admin = rs.getString("telefoni_admin");
                String adresa_admin = rs.getString("adresa_admin");
                String admin_username = rs.getString("admin_username");
                String admin_password = rs.getString("admin_password");

                return new Admin(admin_ID, admin_emri, telefoni_admin, adresa_admin,
                        admin_username, admin_password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null; // Admin not found
    }

    public List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        connection = handler.getConnection();
        String query = "SELECT * FROM Admins";

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int admin_ID = rs.getInt("Admin_ID");
                String admin_emri = rs.getString("Admin_emri");
                String telefoni_admin = rs.getString("telefoni_admin");
                String adresa_admin = rs.getString("adresa_admin");
                String admin_username = rs.getString("admin_username");
                String admin_password = rs.getString("admin_password");

                Admin admin = new Admin(admin_ID, admin_emri, telefoni_admin, adresa_admin,
                        admin_username, admin_password);
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return admins;
    }

    public boolean signup(String adminUsername, String adminName, String adminPhone, String adminAddress,
                          String adminPassword) {
        connection = handler.getConnection();

        try {
            String insert = "INSERT INTO Admins (admin_emri, telefoni_admin, adresa_admin, admin_username, admin_password) " +
                    "VALUES (?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(insert);
            pst.setString(1, adminName);
            pst.setString(2, adminPhone);
            pst.setString(3, adminAddress);
            pst.setString(4, adminUsername);
            pst.setString(5, adminPassword);

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Registration successful!");
                successAlert.showAndWait();
                return true;
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Registration failed. Please try again.");
                errorAlert.showAndWait();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
