package repository;

import models.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private Connection connection;

    public CarRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try {
            String query = "SELECT * FROM makina";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int carId = resultSet.getInt("makina_id");
                String brand = resultSet.getString("brand_makina");
                String model = resultSet.getString("model_makina");
                float price = resultSet.getFloat("cmimi_makina");
                String status = resultSet.getString("statusiMakina");
                String photo = resultSet.getString("foto_makina");
                LocalDate date = resultSet.getDate("date").toLocalDate();

                Car car = new Car(carId, brand, model, price, status, photo, date);
                cars.add(car);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public void rentCar(int carId, String firstName, String lastName, String gender,
                        LocalDate dateRented, LocalDate dateReturn, double amount) {
        try {
            String query = "INSERT INTO rental_table (car_id, first_name, last_name, gender, date_rented, date_returned, amount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, carId);
            pst.setString(2, firstName);
            pst.setString(3, lastName);
            pst.setString(4, gender);
            pst.setDate(5, java.sql.Date.valueOf(dateRented));
            pst.setDate(6, java.sql.Date.valueOf(dateReturn));
            pst.setDouble(7, amount);
            pst.executeUpdate();

            String updateQuery = "UPDATE makina SET statusiMakina = ? WHERE makina_id = ?";
            PreparedStatement updatePst = connection.prepareStatement(updateQuery);
            updatePst.setString(1, "Rented");
            updatePst.setInt(2, carId);
            updatePst.executeUpdate();

            pst.close();
            updatePst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getCarModelsByBrand(String brand) {
        List<String> models = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT model_makina FROM makina WHERE brand_makina = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, brand);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                String model = resultSet.getString("model_makina");
                models.add(model);
            }

            resultSet.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    public Car getCarById(int carId) {
        Car car = null;

        try {
            String query = "SELECT * FROM makina WHERE makina_id = ?";
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, carId);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                String brand = resultSet.getString("brand_makina");
                String model = resultSet.getString("model_makina");
                float price = resultSet.getFloat("cmimi_makina");
                String status = resultSet.getString("statusiMakina");
                String photo = resultSet.getString("foto_makina");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                car = new Car(carId, brand, model, price, status, photo, date);
            }
            resultSet.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return car;
    }

    // Add other methods as needed for your application
}