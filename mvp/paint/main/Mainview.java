package gui.mvp.paint.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Mainview extends Pane
{
    Mainpresenter mainpresenter;
    
    HBox toolBox;
    ComboBox<Integer> strokeWidth;
    ComboBox<String> strokeColor;
    Button delete;
    ObservableList<String> colorNames;
    Color[] colorList;
    double x, y;
    int lineCounter = 0;
    ObservableList<Line> temp;
    
    public Mainview()
    {
        mainpresenter = new Mainpresenter(this);
        initView();
    }
    
    public void initView()
    {
        colorNames = FXCollections.observableArrayList("Schwarz", "Blau", "Rot", "Pink");
        colorList = new Color[colorNames.size()];
        colorList[0] = Color.BLACK;
        colorList[1] = Color.BLUE;
        colorList[2] = Color.RED;
        colorList[3] = Color.PINK;
        
        toolBox = new HBox();
        strokeWidth = new ComboBox<>();
        for(int i = 1; i < 10; i++)
        {
            strokeWidth.getItems().add(i);
        }
        strokeWidth.getSelectionModel().select(0);
        
        
        delete = new Button("Löschen");
        
        strokeColor = new ComboBox<>();
        strokeColor.getItems().addAll(colorNames);
        strokeColor.getSelectionModel().select(0);
 
        toolBox.getChildren().addAll(strokeWidth, strokeColor, delete);
        this.getChildren().add(toolBox);
        this.setOnMousePressed(e -> 
        {
            lineCounter++;
            ObservableList<Line> temp = FXCollections.observableArrayList();
            mousePressed(e.getX(), e.getY());
            
        });
        
        this.setOnMouseDragged(e ->
        {
            mouseDragged(e.getX(), e.getY());
        });
        
    }
    
    public Mainpresenter getPresenter()
    {
        return mainpresenter;
    }
    
    public void mousePressed(double newX, double newY)
    {
        x = newX;
        y = newY;
        mouseDragged(x, y);
    }
    
    public void mouseDragged(double newX, double newY)
    {
        this.getChildren().clear();
        Line line = new Line(x, y, newX, newY);
        temp.add(line);
        line.setStrokeWidth(strokeWidth.getSelectionModel().getSelectedItem());
        line.setStroke(colorList[strokeColor.getSelectionModel().getSelectedIndex()]);
        this.getChildren().add(toolBox);
        ObservableList<ObservableList> lineList = FXCollections.observableArrayList(mainpresenter.copyList());
        for (ObservableList observableList : lineList)
        {
            
        }
      
        x = newX;
        y = newY;
    }
    
    

}
