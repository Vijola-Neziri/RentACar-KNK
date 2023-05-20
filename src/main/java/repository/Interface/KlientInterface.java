package repository.Interface;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public interface KlientInterface {
    void rentCarSave(TextField rent_firstName, TextField rent_lastName, ComboBox<String> rent_gender,
                     Label rent_balance, Label rent_total, TextField rent_amount, ComboBox<String> rent_carid,
                     ComboBox<String> rent_brand, ComboBox<String> rent_model, DatePicker rent_dateRented,
                     DatePicker rent_dateReturn);

    void rentClear(TextField rent_firstName, TextField rent_lastName, ComboBox<String> rent_gender,
                   Label rent_balance, Label rent_total, TextField rent_amount, ComboBox<String> rent_carid,
                   ComboBox<String> rent_brand, ComboBox<String> rent_model, DatePicker rent_dateRented,
                   DatePicker rent_dateReturn);

    double calculateTotal(int carId, String brand, String model, LocalDate dateRented, LocalDate dateReturned);
}
