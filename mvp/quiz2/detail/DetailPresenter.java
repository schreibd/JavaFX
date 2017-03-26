package gui.mvp.quiz2.detail;

import javafx.collections.ObservableList;
import gui.mvp.quiz2.main.MainPresenter;
import gui.mvp.quiz2.model.Question;
import gui.mvp.quiz2.model.QuestionModel;

public class DetailPresenter
{
    private MainPresenter presenter;
    private QuestionModel model;
    private DetailView view;
    
    public DetailPresenter()
    {
        
    }
    
    public void setPresenter(MainPresenter presenter)
    {
        this.presenter = presenter;
    }
    
    public MainPresenter getPresenter()
    {
        return presenter;
    }
    
    public void setModel(QuestionModel model)
    {
        this.model = model;
    }
    
    public void setView(DetailView view)
    {
        this.view = view;
    }
    
    public DetailView getView()
    {
        return view;
    }
    public void firstStart()
    {
        view.showTable();
    }
    
    public ObservableList<Question> getData()
    {
        return model.getQuestionList();
    }
    
    public void deleteScore()
    {
        for(int i = 0; i < model.getQuestionList().size(); i++)
        {
            model.getQuestionList().get(i).setCorrectToZero();
            model.getQuestionList().get(i).setCounterToZero();
        }
        
        view.showTable();
    }
    

}
