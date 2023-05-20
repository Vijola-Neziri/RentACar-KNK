package repository.Interface;

import models.Car;

import java.time.LocalDate;
import java.util.List;

public interface CarInterface {
    List<Car> getAllCars();
    void rentCar(int carId, String firstName, String lastName, String gender,
                 LocalDate dateRented, LocalDate dateReturn, double amount);
    List<String> getCarModelsByBrand(String brand);
    Car getCarById(int carId);
    // Add other methods as needed for your application
}
