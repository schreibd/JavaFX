package gui.graphics;



import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Drawing extends Application
{
    private GraphicsContext gc;
    private double x,y;
    private ObservableList<String> data1 = FXCollections.observableArrayList("Schwarz", "Aquamarin", "Rot", "Blau", "Grün", "Antikweiss", "Dunkelolivgrün");
    private Color[] colors = new Color[]{Color.BLACK, Color.AQUAMARINE, Color.RED, Color.BLUE, Color.GREEN, Color.ANTIQUEWHITE, Color.DARKOLIVEGREEN};
    
    public void start(Stage primaryStage)
    {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        Canvas canvas = new Canvas(900, 600);
        ComboBox<String> cbox1 = new ComboBox<>(data1);
        cbox1.getSelectionModel().select(0);
        cbox1.setOnAction(
            e -> {
                gc.setStroke(colors[cbox1.getSelectionModel().getSelectedIndex()]);
            });
        
        ComboBox<Integer> cbox2 = new ComboBox<>();
        for(int i = 1; i < 10; i++)
        {
            cbox2.getItems().add(i);
        }
        cbox2.getSelectionModel().select(0);
        cbox2.setOnAction(
            e -> {
                gc.setLineWidth(cbox2.getSelectionModel().getSelectedItem());
            });
        
        Button delete = new Button("Loeschen");
        delete.setOnAction(
            e -> {
                canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            });
        
        
        
        gc = canvas.getGraphicsContext2D();
        
        canvas.setOnMousePressed(
            e -> mouseClicked(e.getX(), e.getY()));
        
        canvas.setOnMouseDragged(
            e -> mouseDragged(e.getX(), e.getY()));
            
        
        hbox.getChildren().addAll(cbox1, cbox2, delete);
        vbox.getChildren().addAll(hbox, canvas);
        Scene scene = new Scene(vbox, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Paint für Arme");
        primaryStage.show();
    }
    
    private void mouseClicked(double newX, double newY)
    {
        x = newX;
        y = newY;
        mouseDragged(x, y);
    }
    
    public void mouseDragged(double newX, double newY)
    {
        gc.strokeLine(x, y,newX, newY);
        x = newX;
        y = newY;
    }
    
    public static void main(String[]args)
    {
        launch(args);
    }

}
