package repository;

import ConnectionMysql.DBHandler;
import javafx.scene.control.Alert;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Utils.PasswordHasher.generateSalt;

public class UserRepository {
        private DBHandler handler;
        private Connection connection;
    private PreparedStatement pst;


    public UserRepository() {
            handler = new DBHandler();
        }

        public User getUserByUsernameAndPassword(String username, String password) {
            connection = handler.getConnection();
            String query = "SELECT * FROM User WHERE user_username=? AND Salted_Password=?";

            try (PreparedStatement pst = connection.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, password);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    int user_ID = rs.getInt("User_ID");
                    String user_username = rs.getString("user_username");
                    String emri_user = rs.getString("emri_user");
                    String telefoni_user = rs.getString("telefoni_user");
                    String adresa_user = rs.getString("adresa_user");
                    String Salted_Password = rs.getString("Salted_Password");
                    String Salt = rs.getString("Salt");
                    String gjinia = rs.getString("gjinia");

                    return new User(user_ID, user_username, emri_user, telefoni_user, adresa_user, Salted_Password, Salt, gjinia);
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

            return null; // User not found
        }

        public List<User> getAllUsers() {
            List<User> users = new ArrayList<>();
            connection = handler.getConnection();
            String query = "SELECT * FROM User";

            try (PreparedStatement pst = connection.prepareStatement(query)) {
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int user_ID = rs.getInt("User_ID");
                    String user_username = rs.getString("user_username");
                    String emri_user = rs.getString("emri_user");
                    String telefoni_user = rs.getString("telefoni_user");
                    String adresa_user = rs.getString("adresa_user");
                    String Salted_Password = rs.getString("Salted_Password");
                    String Salt = rs.getString("Salt");
                    String gjinia = rs.getString("gjinia");

                    User user = new User(user_ID, user_username, emri_user, telefoni_user, adresa_user, Salted_Password, Salt, gjinia);
                    users.add(user);
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

            return users;
        }
        public boolean signup(String username, String name, String phone, String address, String password, String gender) {
            connection = handler.getConnection();

            try {
                String insert = "INSERT INTO User (user_username, emri_user, telefoni_user, adresa_user, Salted_Password, Salt, gjinia) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = connection.prepareStatement(insert);
                pst.setString(1, username);
                pst.setString(2, name);
                pst.setString(3, phone);
                pst.setString(4, address);
                pst.setString(5, password);
                pst.setString(6, generateSalt());
                pst.setString(7, gender);

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
