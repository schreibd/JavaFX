package gui.mvp.quiz2.main;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainView extends VBox
{
    private MainPresenter presenter;
    private HBox buttons;
    private Button startB;
    private Button continueB;
    private Button detailB;
    
    public MainView()
    {
        presenter = new MainPresenter(this);
        initView();
    }
    
    public void initView()
    {
        buttons = new HBox();
        startB = new Button("Quiz starten");
        continueB = new Button("Quiz fortsetzen");
        detailB = new Button("Überblick!");
        
        startB.setOnAction(e -> 
            {
                presenter.newBeginning();
            });
        
        detailB.setOnAction(e ->
            {
                presenter.showDetailView();
            });
        
        continueB.setOnAction(e -> 
            {
                presenter.showQuizView();
            });
   
        
        buttons.getChildren().addAll(startB, continueB, detailB);
        
        this.getChildren().add(buttons);
        
    }
    
    public MainPresenter getPresenter()
    {
        return presenter;
    }
    
    public void setPresenter(MainPresenter presenter)
    {
        this.presenter = presenter;
    }
    
    public void setView(Pane pane)
    {
        this.getChildren().clear();
        this.getChildren().addAll(buttons, pane);
    }
}
