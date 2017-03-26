package gui.charts;

import javafx.beans.property.SimpleStringProperty;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LineChartSample extends Application
{
    private TableView<XYChart.Data<Number, Number>> tblvw;

    private TableColumn<XYChart.Data<Number, Number>, String> weekC;

    private TableColumn<XYChart.Data<Number, Number>, String> accC;

    private XYChart.Series<Number, Number> series1;

    private TextField weekTxt;

    private TextField accTxt;

    public void start(Stage stage)
    {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Woche");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Kontostand");
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Kontostände 2014");
        // lineChart.setCreateSymbols(false);

        Label week = new Label("Woche");
        Label account = new Label("Kontostand");
        weekTxt = new TextField();
        accTxt = new TextField();

        Button add = new Button("Hinzufügen / Ändern");
        add.setOnAction(e -> add(weekTxt.getText(), accTxt.getText()));

        Button delete = new Button("Löschen");
        delete.setOnAction(e -> {
                delete();
            });

        HBox hbox = new HBox();
        hbox.getChildren().addAll(week, weekTxt, account, accTxt, add);

        series1 = new XYChart.Series<>();
        series1.setName("Konto1");
        series1.getData().add(new XYChart.Data<Number, Number>(1, 15));
        series1.getData().add(new XYChart.Data<Number, Number>(2, 18));
        series1.getData().add(new XYChart.Data<Number, Number>(3, 15));
        series1.getData().add(new XYChart.Data<Number, Number>(7, 19));
        series1.getData().add(new XYChart.Data<Number, Number>(12, 17));
        series1.getData().add(new XYChart.Data<Number, Number>(19, 22));

        lineChart.getData().add(series1);

        tblvw = new TableView<>(series1.getData());
        weekC = new TableColumn<>("Woche");
        weekC.setPrefWidth(200);
        weekC.setCellValueFactory(item -> new SimpleStringProperty("" + item.getValue().XValueProperty().getValue()));
        tblvw.getColumns().add(weekC);

        accC = new TableColumn<>("Kontostand");
        accC.setPrefWidth(200);
        accC.setCellValueFactory(item -> new SimpleStringProperty("" + item.getValue().YValueProperty().getValue()));
        tblvw.getColumns().add(accC);

        GridPane root = new GridPane();
        root.add(tblvw, 0, 0);
        root.add(lineChart, 1, 0);
        root.add(hbox, 0, 1);
        root.add(delete, 0, 2);
        Scene scene = new Scene(root);
        stage.setTitle("Liniendiagramm");
        stage.setScene(scene);
        stage.show();
    }

    private void add(String weekValue, String accValue)
    {
        if (series1.getData().isEmpty() || series1.getData().get(series1.getData().size() - 1).getXValue().intValue() < Integer.parseInt(weekValue))
        {
            series1.getData().add(new XYChart.Data<>(Integer.parseInt(weekValue), Integer.parseInt(accValue)));
        }

        else if (!weekValue.matches("-?\\d+(\\.\\d+)?") || !accValue.matches("-?\\d+(\\.\\d+)?"))
        {
            Dialog.showButtonDialog("Fehler", "Bitte geben Sie nur Zahlen ein", new String[]
            { "okay" });
        }
        else
        {
            for (int i = 0; i < series1.getData().size(); i++)
            {
                if (series1.getData().get(i).getXValue().toString().equals(weekValue))
                {
                    series1.getData().get(i).setYValue(Integer.parseInt(accValue));
                    accC.setVisible(false);
                    accC.setVisible(true);
                    return;
                }
                else if (Integer.parseInt(weekValue) < series1.getData().get(i).getXValue().intValue())
                {
                    series1.getData().add(i, new XYChart.Data<>(Integer.parseInt(weekValue), Integer.parseInt(accValue)));

                    break;
                }
            }
        }
        weekTxt.clear();
        accTxt.clear();
    }

    public void delete()
    {
        if (tblvw.getSelectionModel().getSelectedItem() != null)
        {
            series1.getData().remove(tblvw.getSelectionModel().getSelectedIndex());
        }
        else
        {
            Dialog.showButtonDialog("Fehler", "Bitte wählen Sie Element aus", new String[]
            { "okay" });
        }

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}