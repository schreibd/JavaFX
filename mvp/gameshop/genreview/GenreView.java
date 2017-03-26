package gui.mvp.gameshop.genreview;

import gui.mvp.gameshop.model.Game;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GenreView extends VBox
{
    GenrePresenter genrePresenter;
    
    TreeView<Game> treeview;
    
    public GenreView()
    {
        genrePresenter = new GenrePresenter(this);
        initView();
    }
    
    public void initView()
    {
        HBox hbox = new HBox();
        Button overview = new Button("Übersicht");
        hbox.getChildren().add(overview);
        this.getChildren().add(hbox);
        

        TreeItem<String> root = new TreeItem<>("Genres");
        treeview = new TreeView<>(root);     
        
    }

}
