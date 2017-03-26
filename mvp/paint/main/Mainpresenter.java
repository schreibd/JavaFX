package gui.mvp.paint.main;

import javafx.collections.ObservableList;
import javafx.scene.shape.Line;
import gui.mvp.paint.model.Model;

public class Mainpresenter
{
    Mainview mainview;
    Model model;
    
    public Mainpresenter(Mainview mainview)
    {
        this.mainview = mainview;   
    }
    
    public void setMainview(Mainview mainview)
    {
        this.mainview = mainview;
    }
    
    public Mainview getMainview()
    {
        return mainview;
    }
    
    public void setModel(Model model)
    {
        this.model = model;
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public void saveLine(Line line)
    {
        model.addToList(line);
    }
    
    public ObservableList<ObservableList> copyList()
    {
        return model.getList();
    }
}
