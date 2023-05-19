package Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class customerController {
    @FXML
    private TableView<?> tableCars;
    @FXML
    private TableColumn<?, ?> columnCarId;
    @FXML
    private TableColumn<?, ?> columnBrand;
    @FXML
    private TableColumn<?, ?> columnModel;
    @FXML
    private TableColumn<?, ?> columnPrice;
    @FXML
    private TableColumn<?, ?> columnStatus;
    @FXML
    private TableColumn<?, ?> columnPhoto;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TextField rent_firstName;
    @FXML
    private TextField rent_lastName;
    @FXML
    private DatePicker ent_dateRented;
    @FXML
    private DatePicker rent_dateReturn;
    @FXML
    private TextField rent_amount;
    @FXML
    private ComboBox<?> rent_gender;
    @FXML
    private ComboBox<?> rent_model;
    @FXML
    private ComboBox<?> rent_brand;
    @FXML
    private ComboBox<?> rent_carid;
    @FXML
    private Button rentBtn;

    // Metoda të ndryshme për të përcjellë veprimet e përdoruesit
    // P.sh., mund të përdorni metoda të tipit @FXML për të përcjellë ngjarjet e butonit, etj.

    @FXML
    private void initialize() {
        // Metoda initialize do të thirret automatikisht pas ngarkimit të fxml dhe inicializimit të kontrolluesit
        // Këtu mund të vendosni logjikën fillestare për formën e klientit
    }

    @FXML
    private void rentCar() {
        // Logjika për procedurën e dhënies së makinës me qera
    }

    // Metoda të tjera sipas nevojës

}
