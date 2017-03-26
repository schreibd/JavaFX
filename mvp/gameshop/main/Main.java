package gui.mvp.gameshop.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import gui.mvp.gameshop.model.Model;

public class Main extends Application
{
    public void start(Stage primaryStage)
    {
        Mainpresenter mainpresenter = initApplication(); 
        Scene scene = new Scene(mainpresenter.getMainview(), 600, 400);
        mainpresenter.getMainview().showTable();
        primaryStage.setTitle("Gameshop");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public Mainpresenter initApplication()
    {
        Model model = new Model();
        Mainview mainview = new Mainview();
        Mainpresenter mainpresenter = mainview.getMainpresenter();
        mainpresenter.setModel(model);
        return mainpresenter;
        
        
        
    }
    
    public static void main(String[]args)
    {
        launch(args);
    }

}
