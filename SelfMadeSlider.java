package gui.selfMadeSlider;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class SelfMadeSlider extends Application
{
    double x, y;

    Pane root;

    Line line;

    Circle circle;
    
    Circle sexYbooty;

    public void start(Stage primaryStage)
    {
        root = new Pane();
        drawSlider(root);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("SelfMadeSlider YAAAAAAY");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawSlider(Pane root)
    {
        line = new Line(200, 250, 300, 250);
        line.setStrokeWidth(3.0);
        line.setStroke(Color.AQUA);

        circle = new Circle(200, 250, 10, Color.AQUA);
        root.getChildren().addAll(line, circle);
        
        sexYbooty = new Circle(120, 120, 1);
        root.getChildren().add(sexYbooty);
        sexYbooty.setStroke(Color.BLACK);
        sexYbooty.setFill(null);
        drawTicks();


        circle.setOnMousePressed(e -> {
            mousePressed(e.getX(), e.getY());
        });

        circle.setOnMouseDragged(e -> {
            mouseDragged(e.getX(), e.getY());
        });

    }

    public void mousePressed(double newX, double newY)
    {
        x = newX;
        y = newY;
        mouseDragged(x, y);  
    }

    public void mouseDragged(double newX, double newY)
    {

        if (newX > 200 && newX < 300)
        {
            root.getChildren().clear();
      
            circle = new Circle(newX, 250, 10, Color.AQUA);
            root.getChildren().add(circle);
            
            circle.setOnMousePressed(e -> {
                mousePressed(e.getX(), e.getY());
            });
            circle.setOnMouseDragged(e -> {
                mouseDragged(e.getX(), e.getY());
            }); 
            x = newX;
            y = newY;
            
            
            sexYbooty.setRadius((200-circle.getCenterX())*-1);
            root.getChildren().addAll(line, sexYbooty);
            drawTicks();
            System.out.println(sexYbooty.getCenterX());
            System.out.println(sexYbooty.getRadius());
        }
    }
    
    public void drawTicks()
    {

        Line tick1 = new Line(200, 260, 200, 270);
        root.getChildren().add(tick1);
        
        Line tick2 = new Line(225, 260, 225, 270);
        root.getChildren().add(tick2);
        
        Line tick3 = new Line(250, 260, 250, 270);
        root.getChildren().add(tick3);
        
        Line tick4 = new Line(275, 260, 275, 270);
        root.getChildren().add(tick4);
        
        Line tick5 = new Line(300, 260, 300, 270);
        root.getChildren().add(tick5);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
