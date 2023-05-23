package Controller;

import ConnectionMysql.DBHandler;
import app.AdminHomeForm;
import javafx.application.Platform;
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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.*;

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
    private ResultSet rs = null;
    private Statement statement;
    private Image image;

    private PreparedStatement pst = null;
    private ObservableList<makina> data;

    @FXML
    private TableColumn<makina, String> columnPhoto;

    private void fillTable() {
        columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));
        columnPhoto.setCellValueFactory(new PropertyValueFactory<>("foto_makina"));
        tableCarsRegistration.setItems(data);
    }


    private String[] listStatus = {"Available", "Not Available"};


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
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

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
                alert.setContentText("Please fill in all required fields");
                alert.showAndWait();
            } else {
                Connection connection = null;
                PreparedStatement pst = null;
                try {
                    connection = DBHandler.getConnection();
                    pst = connection.prepareStatement(sql);
                    pst.setInt(1, Integer.parseInt(availableCars_carid.getText()));
                    pst.setString(2, availableCars_brand.getText());
                    pst.setString(3, availableCars_model.getText());
                    pst.setFloat(4, Float.parseFloat(availableCars_price.getText()));
                    pst.setString(5, (String) availableCars_status.getSelectionModel().getSelectedItem());
                    pst.setString(6, availableCarImport.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    pst.setString(6, uri);


                    pst.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    fillTable();
                    availableCarClear();
                } finally {
                    if (pst != null) {
                        pst.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                }
            }
        } catch (SQLException e) {
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

    public void availableCarAdd() {
        String sql = "INSERT INTO makina (makina_id , brand_makina, model_makina , cmimi_makina , statusiMakina , foto_makina, date) "
                + "VALUES(?,?,?,?,?,?,?)";
        connection = DBHandler.getConnection();
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
                pst = connection.prepareStatement(sql);
                pst.setString(1, availableCars_carid.getText());
                pst.setString(2, availableCars_brand.getText());
                pst.setString(3, availableCars_model.getText());
                pst.setString(4, availableCars_price.getText());
                pst.setString(5, (String) availableCars_status.getSelectionModel().getSelectedItem());
                String uri = getData.path;
                uri = uri.replace("\\", "\\\\");
                pst.setString(6, uri);
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                pst.setString(7, String.valueOf(sqlDate));
                pst.executeUpdate();
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();
                availableCarClear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void availableCarStatusList() {
        List<String> listS = new ArrayList<>();
        for (String data : listStatus) {
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        availableCars_status.setItems(listData);
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
        Stage stage = (Stage) main_form.getScene().getWindow();
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
        connection = DBHandler.getConnection();
        data = loadDataFromDatabase();
        fillTable();
        availableCarStatusList();

        availableCarSelect();
//        homeAvailableCars();
//        homeTotalIncome();
//        homeTotalCustomers();
//        home_totalCars();
//        home_unavailableCars();
//        homeIncomeChart();
//        homeCustomerChart();
    }

    public void availableCarSelect() {
        makina carD = tableCarsRegistration.getSelectionModel().getSelectedItem();
        int num = tableCarsRegistration.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        availableCars_carid.setText(String.valueOf(carD.getMakina_id()));
        availableCars_brand.setText(carD.getBrand_makina());
        availableCars_model.setText(carD.getModel_makina());
        availableCars_price.setText(String.valueOf(carD.getModel_makina()));
        getData.path = carD.getFoto_makina();
        String uri = "file:" + carD.getFoto_makina();
        image = new Image(uri, 116, 153, false, true);
        availableCars_imageView.setImage(image);
    }

    @FXML
    void availableCarImport(ActionEvent event) {
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
    public void carReg(ActionEvent event) throws IOException {
        carBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminCarRegistration.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }

    @FXML
    private Button carRentBtn;

    @FXML
    void signOut(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Logout Confirmation");
        confirmation.setHeaderText("Are you sure you want to logout?");
        confirmation.setContentText("Press OK to confirm.");

        // Customize the button types
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel");

        confirmation.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);

        // Wait for the user's response
        Optional<ButtonType> result = confirmation.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeOK) {
            // User clicked OK, perform logout actions
            Platform.exit(); // Close all windows and exit the application
        }
    }

        @FXML
        public void carList (ActionEvent event) throws IOException {
            carList.getScene().getWindow().hide();
            Stage signup = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminCarList.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }

        @FXML
        public void dataAnalysis (ActionEvent event) throws IOException {
            dataAnalysis.getScene().getWindow().hide();
            Stage signup = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminStatistics.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);

        }
        @FXML
        public void carRent (ActionEvent event) throws IOException {
            carBtn.getScene().getWindow().hide();
            Stage signup = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminRent.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }


        @FXML
        public void customers (ActionEvent event) throws IOException {
            customers.getScene().getWindow().hide();
            Stage signup = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/customer.fxml"));
            Pane pane = fxmlLoader.load();
            Scene scene = new Scene(pane);
            signup.setScene(scene);
            signup.show();
            signup.setResizable(false);
        }
    }


