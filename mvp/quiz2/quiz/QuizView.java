package gui.mvp.quiz2.quiz;





import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizView extends VBox
{
    private QuizPresenter presenter;
    private Label questionLabel;
    private GridPane answerPane;
    private GridPane orderPane;
    private Button nextB;
    private ClipboardContent content;
    
    public QuizView()
    {
        initView();
    }

    public void initView()
    {
        nextB = new Button("=>");
        this.setPadding(new Insets(10, 5, 0, 10));
        nextB.setOnAction(e ->
            {
                presenter.checkAnswerOrder();
                presenter.nextQuestion();
            });    
    }
    
    public void setPresenter(QuizPresenter presenter)
    {
        this.presenter = presenter;
    }
    
    public QuizPresenter getPresenter()
    {
        return presenter;
    }
    
    @SuppressWarnings("static-access")
    public void showQuestion()
    {
        this.getChildren().clear();
        orderPane = new GridPane();
        answerPane = new GridPane();
        
        orderPane.setPadding(new Insets(10, 5, 0, 10));
        answerPane.setPadding(new Insets(10, 5, 0, 10));
      
        questionLabel = new Label(presenter.getQuestion().get());
        questionLabel.setFont(new Font("ARIAL", 24));
        
        this.getChildren().add(questionLabel);
          
        for(int i = 0; i < presenter.getAnswers().length; i++)
        {
            Label source = new Label(presenter.getAnswers()[i]);
            source.setAlignment(Pos.CENTER);
            source.setMinWidth(90);
            answerPane.add(source, i, 0);
            source.setOnDragDetected(e -> onDragDetected(e));
            source.setOnDragDone(e -> onDragDone(e));
            source.setOnDragDropped(e -> onDragDropped(e, source));
            source.setOnDragOver(e -> onDragOver(e));
            
        }
            
        for(int i = 0; i < presenter.getAnswers().length; i++)
        {
            Label tempNumber = new Label(i+1 + ".");
            orderPane.add(tempNumber, 0, i);
            
            Label target = new Label("");
            target.setOnDragOver(e -> onDragOver(e));
            target.setOnDragDropped(e -> onDragDropped(e, target));
            target.setOnDragDetected(e -> onDragDetected(e));
            target.setOnDragDone(e -> onDragDone(e));
            target.setOnDragOver(e -> onDragOver(e));
            
            target.setMaxWidth(Double.MAX_VALUE);
            orderPane.add(target, 1, i);
            
            GridPane.setHgrow(target, Priority.ALWAYS);
            GridPane.setVgrow(target, Priority.ALWAYS);
        }
        
        this.getChildren().addAll(answerPane, orderPane, nextB);
        
        
        orderPane.setGridLinesVisible(true);
        answerPane.setGridLinesVisible(true);
        
        VBox.setMargin(nextB, new Insets(10, 5, 0, 10));
    }
    
    public void disableButton(boolean check)
    {
        nextB.setDisable(check);
    }
    
    public void endQuiz()
    {
        this.getChildren().clear();
        this.getChildren().add(new Label("Ende des Quiz erreicht"));
    }
    
    public void onDragDetected(MouseEvent e)
    {
        System.out.println("onDragDetected");
        Label source = (Label)e.getSource();
        
        Dragboard db = source.startDragAndDrop(TransferMode.ANY);
        
        content = new ClipboardContent();
        content.putString(source.getText());
        db.setContent(content);
    }
    
    public void onDragOver(DragEvent e)
    {
        System.out.println("onDragOver");
        Label target = (Label)e.getSource();
        if(e.getGestureSource() != target && e.getDragboard().hasString())
        {
            e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }
    
    private void onDragDropped(DragEvent e, Label source)
    {
        System.out.println("onDragDropped");
        Label target = (Label)e.getSource();
        Dragboard db = e.getDragboard();
        
        //System.out.println(db.getString());
        boolean success = false;
        if(db.hasString())
        {
            if(target.getText().equals(""))
            {
                target.setText(db.getString());
                success = true;
            }
            else
            {
                String temp = target.getText();
                System.out.println(temp); 
                System.out.println(db.getString());
                target.setText(db.getString());
               //source.setText(temp);
            }
        }
        e.setDropCompleted(success);
    }
    
    private void onDragDone(DragEvent e)
    {
        System.out.println("onDragDone");
        Label source = (Label)e.getSource();
        if (e.getTransferMode() == TransferMode.MOVE)
        {
            source.setText("");
        }
    }
    
    public String getAnswer(int i)
    {
        Label temp;
        if(i == 0)
        {
            temp = (Label)orderPane.getChildren().get(i+1);
        }
        else if (i == 1)
        {
            temp = (Label)orderPane.getChildren().get(i+2);  
        }
        else
        {
            temp = (Label)orderPane.getChildren().get(i+i+1);
        }
        return temp.getText();
    }
}
