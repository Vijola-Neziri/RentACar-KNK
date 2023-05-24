package Controller;

import ConnectionMysql.DBHandler;
import app.AdminHomeForm;
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
import org.w3c.dom.events.MouseEvent;
import repository.CarRepository;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class AdminCarRegistration2 implements Initializable {
    @FXML
    private Button DashboardBtn;

    @FXML
    private TextField availableCars_brand;

    @FXML
    private TextField availableCars_carid;

    @FXML
    private Button availableCars_clearBtn;

    @FXML
    private Button availableCars_deleteBtn;

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
    private ComboBox<?> availableCars_status;

    @FXML
    private Button availableCars_updateBtn;

    @FXML
    private Button carBtn;

    @FXML
    private Button carListBtn;

    @FXML
    private Button close;

    @FXML
    private TableColumn<makina, ?> columnBrand;

    @FXML
    private TableColumn<makina, String> columnCarId;

    @FXML
    private TableColumn<makina, String> columnDate;

    @FXML
    private TableColumn<makina, String> columnModel;

    @FXML
    private TableColumn<makina, String> columnPhoto;

    @FXML
    private TableColumn<makina,String> columnPrice;

    @FXML
    private TableColumn<makina, String> columnStatus;

    @FXML
    private Button customersBtn;

    @FXML
    private Button dataAnalysisBtn;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;
    @FXML
    private Button carRentBtn;
    @FXML
    private TableView<makina> tableCarsRegistration;
    private CarRepository carRepository;
    private DBHandler handler;
    private Connection connection;
    private ResultSet rs = null;
    private Statement statement;
    private Image image;
    private PreparedStatement pst = null;


    public void availableCarAdd() {

        String sql = "INSERT INTO makinaa (makina_id, brand_makina, model_makina, cmimi_makina, statusiMakina, foto_makina, date) "
                + "VALUES(?,?,?,?,?,?,?)";

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

        String sql = "UPDATE makinaa SET brand_makina = '" + availableCars_brand.getText() + "', model_makina = '"
                + availableCars_model.getText() + "', statusiMakina ='"
                + availableCars_status.getSelectionModel().getSelectedItem() + "', cmimi_makina = '"
                + availableCars_price.getText() + "', image = '" + uri
                + "' WHERE makina_id = '" + availableCars_carid.getText() + "'";

//        connect = database.connectDb();
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
                    statement = connection.createStatement();
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

    public void availableCarDelete() {

        String sql = "DELETE FROM makinaa WHERE makina_id = '" + availableCars_carid.getText() + "'";

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

    public void availableCarClear() {
        availableCars_carid.setText("");
        availableCars_brand.setText("");
        availableCars_model.setText("");
        availableCars_status.getSelectionModel().clearSelection();
        availableCars_price.setText("");

        getData.path = "";

        availableCars_imageView.setImage(null);

    }
    private String[] listStatus = {"Available", "Not Available"};

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

            image = new Image(file.toURI().toString(), 140, 184, false, true);
            availableCars_imageView.setImage(image);

        }

    }
    public ObservableList<makina> availableCarListData() {

        ObservableList<makina> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM makinaa";

//        connect = database.connectDb();
        connection = handler.getConnection();
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();

            makina carD;

            while (rs.next()) {
                carD = new makina(rs.getInt("makina_id"),
                        rs.getString("brand_makina"),
                        rs.getString("model_makina"),
                        rs.getDouble("cmimi_makina"),
                        rs.getString("statusiMakina"),
                        rs.getString("image"),
                        rs.getDate("date"));

                listData.add(carD);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<makina> availableCarList;
    public void availableCarShowListData() {
        availableCarList = availableCarListData();

        columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
        columnBrand.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));

        tableCarsRegistration.setItems(availableCarList);
    }

    public void availableCarSelect() {
        makina carD = tableCarsRegistration.getSelectionModel().getSelectedItem();
        int num = tableCarsRegistration.getSelectionModel().getSelectedIndex();

        if ((num - 1) < - 1) {
            return;
        }

        availableCars_carid.setText(String.valueOf(carD.getMakina_id()));
        availableCars_brand.setText(carD.getBrand_makina());
        availableCars_model.setText(carD.getModel_makina());
        availableCars_price.setText(String.valueOf(carD.getCmimi_makina()));

        getData.path = carD.getFoto_makina();

        String uri = "file:" + carD.getFoto_makina();

        image = new Image(uri, 140, 184, false, true);
        availableCars_imageView.setImage(image);

    }
    @FXML
    public void signOut(ActionEvent event) {
        // Get the current stage/window
        Stage currentStage = (Stage) logoutBtn.getScene().getWindow();

        // Close the window
        currentStage.close();
    }
    @FXML
    public  void home(ActionEvent event) throws IOException {
        DashboardBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginForm.class.getResource("/views/AdminHome.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    public void carReg(ActionEvent event) throws  IOException {
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
    public void carList(ActionEvent event) throws  IOException {
        carListBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminCarList.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }
    @FXML
    public void dataAnalysis(ActionEvent event) throws IOException {
        dataAnalysisBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminStatistics.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);

    }
    @FXML
    public void carRent(ActionEvent event) throws IOException {
        carRentBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/AdminRent.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
    }

    @FXML
    public void customers(ActionEvent event) throws IOException {
        customersBtn.getScene().getWindow().hide();
        Stage signup = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdminHomeForm.class.getResource("/views/customer.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        signup.setScene(scene);
        signup.show();
        signup.setResizable(false);
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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new DBHandler();
        connection = handler.getConnection();
//        homeAvailableCars();
//        homeTotalIncome();
//        homeTotalCustomers();
//        homeIncomeChart();
//        homeCustomerChart();

        //to display the data on the tableview
        availableCarShowListData();
        availableCarStatusList();
    }


    @FXML
    void availableCarSelect(MouseEvent event) {

    }
    @FXML
    void availableCarStatusList(ActionEvent event) {

    }











    }
