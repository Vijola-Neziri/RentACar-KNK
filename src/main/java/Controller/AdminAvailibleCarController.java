package  Controller;

import ConnectionMysql.DBHandler;
import Services.carData;
import app.LoginForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.getData;
import models.makina;
import repository.CarRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class AdminAvailibleCarController  implements Initializable {
    @FXML
    private TextField availableCars_brand;

    @FXML
    private Button availableCars_btn;

    @FXML
    private TextField availableCars_carid;

    @FXML
    private Button availableCars_clearBtn;

    @FXML
    private TableColumn<makina, String> availableCars_col_brand;

    @FXML
    private TableColumn<makina, String> availableCars_col_carid;

    @FXML
    private TableColumn<makina, String> availableCars_col_model;

    @FXML
    private TableColumn<makina, String> availableCars_col_price;

    @FXML
    private TableColumn<makina, String> availableCars_col_status;

    @FXML
    private Button availableCars_deleteBtn;

    @FXML
    private AnchorPane availableCars_form;

    @FXML
    private ImageView availableCars_imageView;

    @FXML
    private Button availableCars_importBtn;

    @FXML
    private Button availableCars_insertBtn;

    @FXML
    private TextField availableCars_model;

    @FXML
    private TextField availableCars_price;

    @FXML
    private TextField availableCars_search;

    @FXML
    private ComboBox<?> availableCars_status;

    @FXML
    private TableView<makina> availableCars_tableView;

    @FXML
    private Button availableCars_updateBtn;

    @FXML
    private Button close;

    //    @FXML
//    private ImageView home_availableCars;
    @FXML
    private Label home_availableCars;


    @FXML
    private Button home_btn;


    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button rentBtn;

    @FXML
    private Button rentCar_btn;

    @FXML
    private TextField rent_amount;

    @FXML
    private Label rent_balance;

    @FXML
    private ComboBox<?> rent_brand;

    @FXML
    private ComboBox<?> rent_carid;

    @FXML
    private TableColumn<makina, String> rent_col_brand;

    @FXML
    private TableColumn<makina, Integer> rent_col_carid;

    @FXML
    private TableColumn<makina, String> rent_col_model;

    @FXML
    private TableColumn<makina, Double> rent_col_price;

    @FXML
    private TableColumn<makina, String> rent_col_status;

    @FXML
    private DatePicker rent_dateRented;

    @FXML
    private DatePicker rent_dateReturn;

    @FXML
    private TextField rent_firstName;

    @FXML
    private AnchorPane home_form;

    @FXML
    private AnchorPane rent_form;

    @FXML
    private ComboBox<?> rent_gender;

    @FXML
    private TextField rent_lastName;

    @FXML
    private ComboBox<?> rent_model;

//    @FXML
//    private Label rent_tableView;

    @FXML
    private Label rent_total;

    @FXML
    private Label user;

    @FXML
    private Label username;

    @FXML
    private TableView<makina> rent_tableView;


    private DBHandler handler;
    private Connection connection;



    private ResultSet rs=null;
    private Statement statement;
    private Image image;

    private PreparedStatement pst = null;


    private ObservableList<makina> data;
    private void fillTable() {
        for (makina car:this.data) {
            System.out.println(car.getMakina_id());
            rent_col_carid.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            rent_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            rent_col_model.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            rent_col_price.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            rent_col_status.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));

            rent_tableView.setItems(data);
        }
    }

    private String[] listStatus ={"Available","Not Available"};
    public void availableCarStatusList() {

        List<String> listS = new ArrayList<>();

        for (String data : listStatus) {
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCars_status.setItems(listData);
    }
    public void availableCarAdd() {
        String sql = "INSERT INTO makina (makina_id, brand_makina, model_makina, cmimi_makina, statusiMakina, foto_makina, date) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";

        try {
            Alert alert;

            if (availableCars_carid.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                connection = handler.getConnection();
                pst = connection.prepareStatement(sql);
                pst.setString(1, availableCars_carid.getText());
                pst.setString(2, availableCars_brand.getText());
                pst.setString(3, availableCars_model.getText());
                pst.setString(4, availableCars_price.getText());
                pst.setString(5, (String) availableCars_status.getSelectionModel().getSelectedItem());

                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");

                pst.setString(6, uri);

                java.util.Date date = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                pst.setDate(7, sqlDate);

                pst.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                availableCarShowListData();
                availableCarClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void availableCarUpdate() {

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE makina SET brand_makina = '" + availableCars_brand.getText() + "', model_makina = '"
                + availableCars_model.getText() + "', statusiMakina ='"
                + availableCars_status.getSelectionModel().getSelectedItem() + "', cmimi_makina = '"
                + availableCars_price.getText() + "', foto_makina = '" + uri
                + "' WHERE  = '" + availableCars_carid.getText() + "'";

        connection = handler.getConnection();

        try {
            Alert alert;

            if (availableCars_carid.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Car ID: " + availableCars_carid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void availableCarClear() {
        availableCars_carid.setText("");
        availableCars_brand.setText("");
        availableCars_model.setText("");
        availableCars_status.getSelectionModel().clearSelection();
        availableCars_price.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);

    }
   @FXML
    public void availableCarImportImage() {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 140, 170, false, true);
            availableCars_imageView.setImage(image);

        }

    }


    public ObservableList<carData> availableCarListData() {
        ObservableList<carData> listData = FXCollections.observableArrayList();
        String sql = "Select * from makina";
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            ResultSet  result = pst.executeQuery();

            carData carD;

            while (result.next()){
                carD = new carData(result.getInt("makina_id"),
                        result.getString("brand_makina"),
                        result.getString("model_makina"),
                        result.getDouble("cmimi_makina"),
                        result.getString("statusiMakina"),
                        result.getString("foto_makina"),
                        result.getDate("date"));

                listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private CarRepository carRepository;





    public void availableCarShowListData() {
        for (makina car:this.data) {
            System.out.println(car.getMakina_id());
            availableCars_col_carid.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            availableCars_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            availableCars_col_model.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            availableCars_col_price.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            availableCars_col_status.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));

            availableCars_tableView.setItems(data);
        }
    }

    public void availableCarSearch() {


    }


    public void availableCarSelect() {
        makina carD = availableCars_tableView.getSelectionModel().getSelectedItem();
        int num = availableCars_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        availableCars_carid.setText(String.valueOf(carD.getMakina_id()));
        availableCars_brand.setText(carD.getBrand_makina());
        availableCars_model.setText(carD.getModel_makina());
        availableCars_price.setText(String.valueOf(carD.getCmimi_makina()));

        getData.path = carD.getFoto_makina();

        String uri = "file:" + carD.getFoto_makina();

        image = new Image(uri, 116, 153, false, true);
        availableCars_imageView.setImage(image);

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
    void switchForm(ActionEvent event) throws IOException {
        home_btn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/UserHome.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    public void availableCarDelete() {

        String sql = "DELETE FROM makina WHERE makina_id = '" + availableCars_carid.getText() + "'";

        connection = handler.getConnection();

        try {
            Alert alert;
            if (availableCars_carid.getText().isEmpty()
                    || availableCars_brand.getText().isEmpty()
                    || availableCars_model.getText().isEmpty()
                    || availableCars_status.getSelectionModel().getSelectedItem() == null
                    || availableCars_price.getText().isEmpty()
                    || getData.path == null || getData.path == "") {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Car ID: " + availableCars_carid.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    statement = connection.createStatement();
                    statement.executeUpdate(sql);

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    availableCarShowListData();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    ObservableList<makina> loadDataFromDatabase() {
        ObservableList<makina> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM makina";
            Connection con = DBHandler.getConnection();
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                int carId = rs.getInt("makina_id");
                String brand = rs.getString("brand_makina");
                String model = rs.getString("model_makina");
                double price = rs.getDouble("cmimi_makina");
                String status = rs.getString("statusiMakina");
                String photo = rs.getString("foto_makina");

                makina car = new makina(carId, brand, model, price, status, photo, new Date());
                data.add(car);

                System.out.println(car.getMakina_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data = loadDataFromDatabase();


    }
}
