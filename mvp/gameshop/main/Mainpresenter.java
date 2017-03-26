package gui.mvp.gameshop.main;

import javafx.collections.ObservableList;
import gui.mvp.gameshop.model.Model;

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
    
    public ObservableList copyList()
    {
        return model.getList();
    }
    
    public void addGame(String name, String preis, String anzahl, String genre)
    {
        model.createGame(name, Double.parseDouble(preis), Integer.parseInt(anzahl), genre);
        mainview.showTable();
    }
    
    public void deleteGame(int i)
    {
        model.deleteGame(i);
        mainview.showTable();
    }
    
    public void changeGame(int i, String[] changedValues)
    {
        model.changeGame(i, changedValues);
        mainview.showTable();
    }
}
