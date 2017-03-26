package gui.mvp.quiz2.quiz;

import javafx.beans.property.SimpleStringProperty;
import gui.mvp.quiz2.main.MainPresenter;
import gui.mvp.quiz2.model.QuestionModel;

public class QuizPresenter
{
    private MainPresenter presenter;
    private QuizView view;
    private QuestionModel model;

    public QuizPresenter()
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
    
    public void setView(QuizView view)
    {
        this.view = view;
    }
    
    public QuizView getView()
    {
        return view;
    }
    
    public void setModel(QuestionModel model)
    {
        this.model = model;
    }
    
    public QuestionModel getModel()
    {
        return model;
    }
    
    public SimpleStringProperty getQuestion()
    {
        return model.getQuestionList().get(model.getPointer()).getQuestion();
    }
    
    public String[] getAnswers()
    {
        return model.getQuestionList().get(model.getPointer()).getAnswers();
    }
    
    public void nextQuestion()
    {
        model.incrementPointer();
        if(model.getPointer() == model.getQuestionList().size())
        {
            view.endQuiz();
        }
        else
        {
            model.mixAnswers();
            view.showQuestion();
        }
    }
    
    public void checkAnswerOrder()
    {
        String[] userAnswers = new String[model.getQuestionList().get(model.getPointer()).getAnswers().length];
        boolean right = true;
        
        for(int i = 0; i < userAnswers.length; i++)
        {
            userAnswers[i] = view.getAnswer(i);
            right = userAnswers[i].equals(model.getQuestionList().get(model.getPointer()).getOrder()[i]);
            
        }
        if(right)
        {
            model.getQuestionList().get(model.getPointer()).incrementCorrect();
            model.getQuestionList().get(model.getPointer()).incrementCounter();
        }
        
        else
        {
            model.getQuestionList().get(model.getPointer()).incrementCounter(); 
        }
        
    }
    
    
    
    
}
