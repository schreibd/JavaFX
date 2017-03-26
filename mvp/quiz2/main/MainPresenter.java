package gui.mvp.quiz2.main;

import javafx.scene.layout.Pane;
import gui.mvp.quiz2.detail.DetailPresenter;
import gui.mvp.quiz2.quiz.QuizPresenter;

public class MainPresenter
{
    private MainView view;
    private QuizPresenter quizPresenter;
    private DetailPresenter detailPresenter;
    
    public MainPresenter(MainView view)
    {
        this.view = view;
    }
    
    public MainView getView()
    {
        return view;
    }
    
    public void setView(MainView view)
    {
        this.view = view;
    }
    
    public QuizPresenter getQuizPresenter()
    {
        return quizPresenter;
    }
    
    public void setQuizPresenter(QuizPresenter presenter)
    {
        this.quizPresenter = presenter;
    }
    
    public DetailPresenter getDetailPresenter()
    {
        return detailPresenter;
    }
    
    public void setDetailPresenter(DetailPresenter presenter)
    {
        detailPresenter = presenter;
    }
    
    public void showQuizView()
    {
        quizPresenter.getModel().mixAnswers();
        Pane pane = quizPresenter.getView();
        view.setView(pane);
    }
    
    public void newBeginning()
    {
        quizPresenter.getModel().newBeginning();
        quizPresenter.getModel().mixAnswers();
        quizPresenter.getView().showQuestion();
        quizPresenter.getView().disableButton(quizPresenter.getModel().getPointer() == quizPresenter.getModel().getQuestionList().size()-1);
        view.setView(quizPresenter.getView());
    }
    
    public void showDetailView()
    {
        view.setView(detailPresenter.getView());
    }
}
