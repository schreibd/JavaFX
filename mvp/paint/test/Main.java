package gui.mvp.paint.test;

import gui.mvp.paint.main.Mainpresenter;
import gui.mvp.paint.main.Mainview;
import gui.mvp.paint.model.Model;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public void start(Stage primaryStage)
    {
        Mainpresenter mainpresenter = initApplication();
        Scene scene = new Scene(mainpresenter.getMainview(), 500, 500);
        primaryStage.setTitle("Paint für Arme");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Mainpresenter initApplication()
    {
        Model model = new Model();
        Mainview mainview = new Mainview();
        Mainpresenter mainpresenter = mainview.getPresenter();
        mainpresenter.setModel(model);
        
        return mainpresenter;
    }
    
    public static void main(String[]args)
    {
        launch(args);
    }
}
