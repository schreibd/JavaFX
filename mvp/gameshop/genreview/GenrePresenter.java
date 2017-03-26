package gui.mvp.gameshop.genreview;

import gui.mvp.gameshop.main.Mainpresenter;
import gui.mvp.gameshop.model.Model;

public class GenrePresenter
{
    Mainpresenter mainpresenter;
    Model model;
    GenreView genreview;
    
    public GenrePresenter(GenreView genreview)
    {
        this.genreview = genreview;
    }
    
    public GenreView getGenreview()
    {
        return genreview;
    }
    
    public void setGenreview(GenreView genreview)
    {
        this.genreview = genreview;
    }
    
    public Mainpresenter getMainpresenter()
    {
        return mainpresenter;
    }
    
    public void setMainpresenter(Mainpresenter mainpresenter)
    {
        this.mainpresenter = mainpresenter;
    }
    
    public Model getModel()
    {
        return model;
    }
    
    public void setModel(Model model)
    {
        this.model = model;
    }

}
