package gui.charts;

import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PieChartSample extends Application
{
    private Slider[] sliderArray;
    private Label[] labels;
    public void start(Stage stage)
    {
        HBox hbox = new HBox();
        
        ObservableList<PieChart.Data> pieChartData = 
            FXCollections.observableArrayList
            (
                new PieChart.Data("Grapefruit", 13), 
                new PieChart.Data("Orangen", 25), 
                new PieChart.Data("Pflaumen", 10), 
                new PieChart.Data("Birnen", 22), 
                new PieChart.Data("Äpfel", 30),
                new PieChart.Data("Penisfrucht", 15),
                new PieChart.Data("Hurensohn", 45)
            );
        sliderArray = new Slider[pieChartData.size()];
        labels = new Label[pieChartData.size()];
        
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Importiertes Obst");
        pieChart.setLegendSide(Side.BOTTOM);
        Label caption = new Label("");
        for(PieChart.Data data: pieChart.getData())
        {
            data.getNode().setOnMouseMoved
                (
                    e ->
                {
                    caption.setTranslateX(e.getSceneX()-300);
                    caption.setTranslateY(e.getSceneY()-150);
                    caption.setText(data.getPieValue()+"");
                }
                );
        }
        
        for(PieChart.Data data : pieChart.getData())
        {
            VBox temp = new VBox();
            temp.setAlignment(Pos.CENTER);
            
            sliderArray[pieChartData.indexOf(data)] = new Slider();
            sliderArray[pieChartData.indexOf(data)].setOrientation(Orientation.VERTICAL);
            sliderArray[pieChartData.indexOf(data)].setPadding(new Insets(0, 50, 0, 50));
            sliderArray[pieChartData.indexOf(data)].valueProperty().bindBidirectional(pieChartData.get(pieChartData.indexOf(data)).pieValueProperty());
            
            temp.getChildren().add(sliderArray[pieChartData.indexOf(data)]);
            labels[pieChartData.indexOf(data)] = new Label(data.getName());
            temp.getChildren().add(labels[pieChartData.indexOf(data)]);
            hbox.getChildren().add(temp);
        } 
        
        TableView<PieChart.Data> tblvw = new TableView<>(pieChartData);
        
        TableColumn<PieChart.Data, String> fruits = new TableColumn<>("Obst");
        fruits.setPrefWidth(200);
        fruits.setCellValueFactory(
            item -> item.getValue().nameProperty()
        );
        tblvw.getColumns().add(fruits);
        
        TableColumn<PieChart.Data, String> amount = new TableColumn<>("Menge");
        amount.setPrefWidth(200);
        amount.setCellValueFactory(
            item -> item.getValue().pieValueProperty().asString()
        );
        tblvw.getColumns().add(amount);
        tblvw.setMaxHeight(150);
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Group groupPane = new Group();
        groupPane.getChildren().addAll(pieChart, caption);
        root.getChildren().addAll(tblvw, groupPane, hbox);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Tortendiagramm");
        stage.setWidth(1100);
        stage.setHeight(700);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}