package Controller;

import ConnectionMysql.DBHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.getData;
import models.makina;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class AdminCarRegistrationController implements Initializable {
    @FXML
    private Button DashboardBtn;
    @FXML
    private Button availableCarImport;

    @FXML
    private TextField availableCars_carid;

    @FXML
    private Button availableCars_clearBtn;

    @FXML
    private Button availableCars_deleteBtn;

    @FXML
    private Button availableCars_insertBtn;

    @FXML
    private TextField availableCars_model;

    @FXML
    private TextField availableCars_price;

    @FXML
    private ComboBox<?> availableCars_status;

    @FXML
    private Button availableCars_updateBtn;

    @FXML
    private Button carBtn;

    @FXML
    private Button carList;

    @FXML
    private Button close;

    @FXML
    private TableColumn<makina, String> columnBrand;

    @FXML
    private TableColumn<makina, String> columnModel;

    @FXML
    private TableColumn<makina, Double> columnPrice;

    @FXML
    private TableColumn<makina, String> columnStatus;
    @FXML
    private TableColumn<makina, Integer> columnCarId;
    @FXML
    private Button customers;

    @FXML
    private Button dataAnalysis;

    @FXML
    private Button minimize;

    @FXML
    private TableView<makina> tableCarsRegistration;
    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField availableCars_brand;

    @FXML
    private ImageView availableCars_imageView;

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
            columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));


            tableCarsRegistration.setItems(data);
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

    @FXML
    void availableCarClear() {
        availableCars_carid.setText("");
        availableCars_brand.setText("");
        availableCars_model.setText("");
        availableCars_status.getSelectionModel().clearSelection();
        availableCars_price.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);
    }


    @FXML
    void availableCarAdd(ActionEvent event) {
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

                fillTable();
                availableCarClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void availableCarDelete(ActionEvent event) {
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

                    fillTable();
                    availableCarClear();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void availableCarStatusList(ActionEvent event) {

    }

    @FXML
    void availableCarUpdate(ActionEvent event) {
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

                    fillTable();
                    availableCarClear();
                }

            }
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
        handler = new DBHandler();
        connection = handler.getConnection();
        data = loadDataFromDatabase();
        fillTable();
//        homeAvailableCars();
//        homeTotalIncome();
//        homeTotalCustomers();
//        home_totalCars();
//        home_unavailableCars();
//        homeIncomeChart();
//        homeCustomerChart();
    }
    @FXML
    void availableCarImport(ActionEvent event) {

    }
}


