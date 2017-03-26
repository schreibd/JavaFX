package gui.mvp.paint.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.shape.Line;

public class Model
{
    ObservableList<ObservableList> listList;
    
    public Model()
    {
        listList = FXCollections.observableArrayList();
    }
    
    public void addToList(ObservableList<Line> list)
    {
        listList.add(list);
    }
    
    public ObservableList<ObservableList> getList()
    {
        return listList;
    }
}
