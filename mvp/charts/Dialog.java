package gui.charts;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialog
{
    private static Stage primaryStage;

    private static int button;

    private static boolean pressed = false;
    
    private static boolean filled = true;

    private static String[] text;
    
    private static TextField[] fields;
    
    public static int showButtonDialog(String titel, String befehl, String[] field)
    {
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        Label label = new Label(befehl);

        for (int i = 0; i < field.length; i++)
        {
            int temp = i;
            Button b = new Button(field[i]);
            b.setOnAction(e -> {
                    primaryStage.close();
                    button = temp;
                    pressed = true;
                });
            
            hbox.getChildren().add(b);
        }
        
        
        vbox.getChildren().addAll(label,hbox);
        
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle(titel);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        
        
        
        
        if (pressed)
        {
            pressed = false;
            return button;
        }
        
        return -1;
    }
    
    public static String[] showInputDialog(String titel, String befehl, String[] field)
    {
        VBox vbox = new VBox();
        HBox hbox = new HBox();
        GridPane grid = new GridPane();
        Label label = new Label(befehl);
        fields = new TextField[field.length];
        text = new String[field.length];
        
        for(int i = 0; i < field.length; i++)
        {
            TextField temp = new TextField();
            fields[i] = temp;
            grid.add(new Label(field[i]), 0, i);
            grid.add(temp, 1, i);
        }
        
        Button ok = new Button("Ok");
        Button abort = new Button("Abbrechen");
        
        ok.setOnAction(
            e -> {
                filled = true;
            
                for(int i = 0; i < text.length; i++)
                {
                    text[i] = fields[i].getText();
                    for(int j = 0; j < fields.length; j++)
                    {
                        if (fields[i].getText().equals(""))
                        {
                            filled = false;
                            break;
                        }
                    }
                }
                primaryStage.close();
            });
        
        abort.setOnAction(
            e -> {
                primaryStage.close();
            }
        );
        
        
        hbox.getChildren().addAll(ok, abort);
        vbox.getChildren().addAll(label, grid, hbox);
        
        Scene scene = new Scene(vbox, 200, 300);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle(titel);
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        
        return !filled ? null : text;
    }

}

