
        package Controller;

        import ConnectionMysql.DBHandler;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.TableColumn;
        import javafx.scene.control.TableView;
        import javafx.scene.control.cell.PropertyValueFactory;
        import models.makina;
        import repository.CarRepository;

        import java.net.URL;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.Date;
        import java.util.ResourceBundle;

public class rentController implements Initializable {

    @FXML
    private TableColumn<makina, String> columnBrand;

    @FXML
    private TableColumn<makina, Integer> columnCarId;

    @FXML
    private TableColumn<makina, String> columnModel;

    @FXML
    private TableColumn<makina, String> columnPhoto;

    @FXML
    private TableColumn<makina, Double> columnPrice;

    @FXML
    private TableColumn<makina, String> columnStatus;

    @FXML
    private TableColumn<makina,Date> columnDate;


    @FXML
    private TableView<makina> tableCars;

    private ObservableList<makina> data;

    private CarRepository carRepository;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    private void fillTable() {
        for (makina car:this.data) {
            System.out.println(car.getMakina_id());
            columnCarId.setCellValueFactory(new PropertyValueFactory<>("makina_id"));
            columnBrand.setCellValueFactory(new PropertyValueFactory<>("brand_makina"));
            columnModel.setCellValueFactory(new PropertyValueFactory<>("model_makina"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("cmimi_makina"));
            columnStatus.setCellValueFactory(new PropertyValueFactory<>("statusiMakina"));
            columnPhoto.setCellValueFactory(new PropertyValueFactory<>("foto_makina"));
            columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableCars.setItems(data);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data = loadDataFromDatabase();
        fillTable();
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

                makina car = new makina(carId, brand, model, price, status, photo, (java.sql.Date) new Date());
                data.add(car);

                System.out.println(car.getMakina_id());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return data;
    }
}