package gui.mvp.quiz2.model;

import javafx.beans.property.SimpleStringProperty;

public class Question
{
    private SimpleStringProperty question;
    private String[] answers;
    private String[] order;
    private int counter;
    private int correct;
    
    public Question(SimpleStringProperty question, String[] answers, String[] order)
    {
        this.question = question;
        this.answers = answers;
        this.order = order;
        counter = 0;
        correct = 0;
    }
    
    public SimpleStringProperty getCounter()
    {
        return new SimpleStringProperty(Integer.toString(counter));
    }
    
    public void incrementCounter()
    {
        counter++;
    }
    
    public SimpleStringProperty getCorrect()
    {
        return new SimpleStringProperty(Integer.toString(correct));
    }
    
    public void incrementCorrect()
    {
        correct++;
    }
    
    
    public SimpleStringProperty getQuestion()
    {
        return question;
    }
    
    public String[] getAnswers()
    {
        return answers;
    }
    
    public String[] getOrder()
    {
        return order;
    }
    
    public void setCounterToZero()
    {
        counter = 0;
    }
    
    public void setCorrectToZero()
    {
        correct = 0;
    }
}
