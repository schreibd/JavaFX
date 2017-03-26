package gui.mvp.login;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginTest extends Application
{
    private static int startArg;

    public void start(Stage primaryStage)
    {
        IView v = null;
        Presenter p = null;
        if (startArg == 0)
        {
            v = new View();
            p = v.getPresenter();
        }
        else
        {
            GridPane pane = null;
            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader();
                pane = fxmlLoader.load(getClass().getResource("loginView.fxml").openStream());
                p = (Presenter) fxmlLoader.getController();
                v = new ViewFXML(pane, p);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        
        p.setView(v);
        Model m = new Model();
        p.setModel(m);

        Scene scene = new Scene(v.getUI(), 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        startArg = Integer.parseInt(args[0]);
        launch(args);
    }
}