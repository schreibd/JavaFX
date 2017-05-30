package gui.mvp.quiz2.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuestionModel
{
    private ObservableList<Question> questionList;

    private int pointerOnQuestion = 0;

    public QuestionModel()
    {
        createQuestionCatalogue();
    }

    public void createQuestion(SimpleStringProperty question, String[] answers, String[] order)
    {
        questionList.add(new Question(question, answers, order));
    }

    public void createQuestionCatalogue()
    {
        questionList = FXCollections.observableArrayList();
        SimpleStringProperty question = new SimpleStringProperty("Ordne die Zahlen von Groß nach Klein");
        String[] answers =
        { "98", "112", "4", "0", "300", "9999" };
        String[] order =
        { "9999", "300", "112", "98", "4", "0" };
        createQuestion(question, answers, order);

        question = new SimpleStringProperty("Ordne die Zahlen nach Größe");
        answers = new String[]
        { "5", "8", "1", "29", "2" };
        order = new String[]
        { "1", "4", "5", "8", "29" };
        createQuestion(question, answers, order);
    }

    public ObservableList<Question> getQuestionList()
    {
        return questionList;
    }

    public int getPointer()
    {
        return pointerOnQuestion;
    }

    public void incrementPointer()
    {
        pointerOnQuestion++;
    }

    public void newBeginning()
    {
        pointerOnQuestion = 0;
    }

    public void mixAnswers()
    {
        int size = questionList.get(pointerOnQuestion).getAnswers().length;
        String[] answersOrderes = questionList.get(pointerOnQuestion).getAnswers();

        for (int i = 0; i < size; i++)
        {
            int random = (int) (Math.random() * 100);
            if (random > size - 1)
            {
                random = random % size;
            }
            String temp = answersOrderes[i];
            questionList.get(pointerOnQuestion).getAnswers()[i] = questionList.get(pointerOnQuestion).getAnswers()[random];
            questionList.get(pointerOnQuestion).getAnswers()[random] = temp;
        }
    }
}
