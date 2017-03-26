package gui.graphics;

import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Sinus extends Application
{
    private GraphicsContext gc;

    private Canvas canvas;

    private Slider sliderA;

    private Slider sliderF;

    private Slider sliderP;

    private Slider sliderZ;

    private Label labelForm;

    private DecimalFormat temp;

    public Sinus()
    {
        sliderA = new Slider(-6, 6, 1);
        sliderA.setShowTickMarks(true);
        sliderA.setShowTickLabels(true);
        sliderA.setMajorTickUnit(2);
        sliderA.valueProperty().addListener(e -> draw());

        sliderF = new Slider(0, 40, 1);
        sliderF.setShowTickMarks(true);
        sliderF.setShowTickLabels(true);
        sliderF.setMajorTickUnit(10);
        sliderF.valueProperty().addListener(e -> draw());

        sliderP = new Slider(-10, 10, 0);
        sliderP.setShowTickMarks(true);
        sliderP.setShowTickLabels(true);
        sliderP.setMajorTickUnit(5);
        sliderP.valueProperty().addListener(e -> draw());

        sliderZ = new Slider(10, 210, 50);
        sliderZ.setShowTickMarks(true);
        sliderZ.setShowTickLabels(true);
        sliderZ.setMajorTickUnit(50);
        sliderZ.valueProperty().addListener(e -> draw());
        canvas = new Canvas(800, 500);
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(0.5);
        gc.strokeLine(800 / 2, 0, 800 / 2, 500);
        gc.strokeLine(0, 500 / 2, 800, 500 / 2);
        temp = new DecimalFormat("#0.00");
        labelForm = new Label();
    }

    public void start(Stage primaryStage)
    {

        VBox vbox = new VBox();
        GridPane grid = new GridPane();
        Label amplitude = new Label("Amplitude:");
        amplitude.setFont(new Font("Arial", 20));
        Label frequence = new Label("Frequenz:");
        frequence.setFont(new Font("Arial", 20));
        Label phase = new Label("Phase:");
        phase.setFont(new Font("Arial", 20));
        Label zoom = new Label("Zoom:");
        zoom.setFont(new Font("Arial", 20));

        grid.add(amplitude, 0, 0);
        grid.add(sliderA, 1, 0);
        grid.add(frequence, 0, 1);
        grid.add(sliderF, 1, 1);
        grid.add(phase, 0, 2);
        grid.add(sliderP, 1, 2);
        grid.add(zoom, 0, 3);
        grid.add(sliderZ, 1, 3);

        vbox.getChildren().addAll(labelForm, canvas, grid);
        Scene scene = new Scene(vbox, 800, 750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sinuswelle");
        draw();
        primaryStage.show();

    }

    private void draw()
    {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(0.5);
        gc.strokeLine(800 / 2, 0, 800 / 2, 500);
        gc.strokeLine(0, 500 / 2, 800, 500 / 2);
        gc.setLineWidth(1);
        double x1 = 0;
        double x2 = 0;
        double y1 = 0;
        double y2 = 0;
        for (int i = 0; i < canvas.getWidth(); i++)
        {
            x1 = i;
            y1 = -getSinus((x1 - canvas.getWidth() / 2) / sliderZ.getValue()) * sliderZ.getValue();
            x2 = i + 1;
            y2 = -getSinus((x2 - canvas.getWidth() / 2) / sliderZ.getValue()) * sliderZ.getValue();
            y1 += canvas.getHeight() / 2;
            y2 += canvas.getHeight() / 2;
            gc.strokeLine(x1, y1, x2, y2);
        }
        setLabel();
    }

    private void setLabel()
    {
        if (sliderP.getValue() > 0)
        {
            labelForm.setText(temp.format(sliderA.getValue()) + "*sin(" + temp.format(sliderF.getValue()) + "*" + "x" + "+" + temp.format(sliderP.getValue()) + ")");
        }
        else if (sliderP.getValue() == 0)
        {
            labelForm.setText(temp.format(sliderA.getValue()) + "*sin(" + temp.format(sliderF.getValue()) + "*" + "x" + ")");
        }
        else
        {
            labelForm.setText(temp.format(sliderA.getValue()) + "*sin(" + temp.format(sliderF.getValue()) + "*" + "x" + temp.format(sliderP.getValue()) + ")");
        }
    }

    private double getSinus(double x)
    {
        return sliderA.getValue() * Math.sin(sliderF.getValue() * x + sliderP.getValue());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
