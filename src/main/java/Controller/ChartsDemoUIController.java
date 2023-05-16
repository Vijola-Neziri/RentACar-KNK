package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartsDemoUIController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private void handleClose(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleShowBarChart(ActionEvent event) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Product");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Quantity Sold");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

        XYChart.Series<String, Number> data = new XYChart.Series<>();
        data.setName("Product Sold");

        // Provide data
        data.getData().add(new XYChart.Data<>("Product A", 3000));
        data.getData().add(new XYChart.Data<>("Product B", 1500));
        data.getData().add(new XYChart.Data<>("Product C", 100));

        barChart.getData().add(data);

        // Add bar chart to BorderPane
        borderPane.setCenter(barChart);
    }

    @FXML
    private void handleShowPieChart(ActionEvent event) {
        // Handle the action to show the pie chart
    }

    @FXML
    private void handleUpdateData(ActionEvent event) {
        // Handle the action to update data
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the controller
    }
}
