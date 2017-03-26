package gui.mvp.quiz2.detail;

import gui.mvp.quiz2.model.Question;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DetailView extends VBox
{
    private DetailPresenter presenter;
    private TableView<Question> table;
    private Label label;
    private Button delete;
    
    public DetailView()
    {
        initView();
    }
    
    public void initView()
    {
        label = new Label("Übersicht");
        label.setFont(new Font("Arial", 20));
        this.setPadding(new Insets(10, 5, 0, 10));
        
        delete = new Button("Ergebnisse löschen");
        delete.setOnAction(e -> 
            {
                presenter.deleteScore(); 
            });    
    }
    
    public void setPresenter(DetailPresenter presenter)
    {
        this.presenter = presenter;
    }
    
    @SuppressWarnings("unchecked")
    public void showTable()
    {
        table = new TableView<Question>(presenter.getData());
        
        TableColumn<Question, String> column1 = new TableColumn<>("Frage");
        column1.setCellValueFactory(item -> item.getValue().getQuestion());
        
        TableColumn<Question, String> column2 = new TableColumn<>("Beantwortet");
        column2.setCellValueFactory(item -> item.getValue().getCounter());
        
        TableColumn<Question, String> column3 = new TableColumn<>("Richtig beantwortet");
        column3.setCellValueFactory(item -> item.getValue().getCorrect());
        
        table.getColumns().addAll(column1, column2, column3);
        
        this.getChildren().clear();
        this.getChildren().addAll(label, table, delete);
    }
    
    
}
