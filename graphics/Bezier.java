package gui.graphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Bezier extends Application
{
    private static final int DIAMETER = 20;
    private static final int RADIUS = DIAMETER/2;
    private int[] begin = new int[]{100, 100};
    private int[] end = new int[]{200, 70};
    private int[] control1 = new int[]{200, 200};
    private int[] control2 = new int[]{125, 200};
    private static boolean hit = false;
    private Canvas canvas;

    public void start(Stage primaryStage)
    {
        canvas = new Canvas(600, 600);
        drawShapes();
        BorderPane root = new BorderPane();
        root.getChildren().add(canvas);
        canvas.setOnMouseReleased(
            e -> hit = false);
        canvas.setOnMousePressed(
            e -> {
                if (Math.pow((e.getX()-begin[0]),2) +  Math.pow((e.getY()-begin[1]), 2) < Math.pow(RADIUS, 2))
                {
                    hit = true;
                
                    canvas.setOnMouseDragged(
                        e1 -> {
                            if(hit)
                            {
                                begin[0] = (int) e1.getX();
                                begin[1] = (int) e1.getY();
                 
                                drawShapes();
                            }
                        });
                }
                if (Math.pow((e.getX()-end[0]),2) +  Math.pow((e.getY()-end[1]), 2) < Math.pow(RADIUS, 2))
                {
                    canvas.setOnMouseDragged(
                        e1 -> {
                            end[0] = (int) e1.getX();
                            end[1] = (int) e1.getY();
                            drawShapes();
                        });
                }
            
                if (Math.pow((e.getX()-control1[0]),2) +  Math.pow((e.getY()-control1[1]), 2) < Math.pow(RADIUS, 2))
                {
                    canvas.setOnMouseDragged(
                        e1 -> {
                            control1[0] = (int) e1.getX();
                            control1[1] = (int) e1.getY();
                            drawShapes();
                        });
                }
            
                if (Math.pow((e.getX()-control2[0]),2) +  Math.pow((e.getY()-control2[1]), 2) < Math.pow(RADIUS, 2))
                {
                    canvas.setOnMouseDragged(
                        e1 -> {
                            control2[0] = (int) e1.getX();
                            control2[1] = (int) e1.getY();
                            drawShapes();
                        });
                }
            });
        primaryStage.setTitle("Bezier-Kurve");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
    
    private void drawShapes()
    {
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLUE);
        gc.fillOval(begin[0]-RADIUS, begin[1]-RADIUS, DIAMETER, DIAMETER);
        gc.fillOval(end[0]-RADIUS, end[1]-RADIUS, DIAMETER, DIAMETER);
        gc.fillOval(control1[0]-RADIUS, control1[1]-RADIUS, DIAMETER, DIAMETER);
        gc.fillOval(control2[0]-RADIUS, control2[1]-RADIUS, DIAMETER, DIAMETER);

        gc.setStroke(Color.BLACK);
        gc.beginPath();
        gc.moveTo(begin[0], begin[1]);
        gc.bezierCurveTo(end[0], end[1], control1[0], control1[1], 
                         control2[0], control2[1]);
        //gc.closePath();
        gc.stroke();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
