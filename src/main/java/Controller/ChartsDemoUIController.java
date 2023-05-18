package Controller;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ChartsDemoUIController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleShowBarChart(ActionEvent event) {

        borderPane.setCenter(buildPieChart());

    }
    private PieChart buildPieChart() {
        //Create Data
        ObservableList<PieChart.Data> pieCharDAta = FXCollections.observableArrayList(
                new PieChart.Data("Product A", 3000),
                new PieChart.Data("Product B", 1500),
                new PieChart.Data("Product C", 100)
        );
        //Create PieChart object
        PieChart pieChart = new PieChart(pieCharDAta);
        pieChart.setTitle("Products sold");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        //Create the context menu and menu item(s)
        ContextMenu contextMenu = new ContextMenu();
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        //Display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                }
            }
        });

        miSwitchToBarChart.setOnAction((ActionEvent actionEvent) -> {
            borderPane.setCenter(buildBarChart());
        });
        return pieChart;
    }
    private BarChart buildBarChart(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Cars");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity Sold of Cars");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Cars Sold");

        // Provide data
        data.getData().add(new XYChart.Data<>("Viti 2021", 3000));
        data.getData().add(new XYChart.Data<>("Viti 2022", 2500));
        data.getData().add(new XYChart.Data<>("Viti 2023", 2000));

        barChart.getData().add(data);
        barChart.setLegendVisible(false);

        return barChart;
    }
    @FXML
    private void handleShowPieChart(ActionEvent event) {
        //Create Data
        ObservableList<PieChart.Data> pieCharDAta= FXCollections.observableArrayList(
                new PieChart.Data("Viti 2021",3000),
                new PieChart.Data("Viti 2022",2500),
                new PieChart.Data("Viti 2023",2000)
        );
        //Create PieChart object
        PieChart pieChart=new PieChart(pieCharDAta);
        pieChart.setTitle("Cars sold");
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        //Create the context menu and menu item(s)
        ContextMenu contextMenu=new ContextMenu();
        MenuItem miSwitchToBarChart=new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        //Display context menu
        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton()== MouseButton.SECONDARY){
                    contextMenu.show(pieChart,event.getScreenX(),event.getScreenY());
                }
            }
        });
        miSwitchToBarChart.setOnAction((ActionEvent actionEvent)-> {
            borderPane.setCenter(buildBarChart());
        } );

        borderPane.setCenter(pieChart);
    }
    @FXML
    private void handleUpdateData(ActionEvent event) {
        Node node = borderPane.getCenter();

        if (node instanceof PieChart) {
            PieChart pc = (PieChart) node;

            pc.getData().get(2).setPieValue(750);

        }
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Charts Cars Demo Application");
        alert.setContentText("This charts shows car sales in the last three years.");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borderPane.setCenter(buildBarChart());
    }
}
