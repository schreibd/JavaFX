package gui.mvp.login;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ViewFXML implements IView
{
    private Presenter presenter; 
    

    private GridPane pane; 
    private TextField loginName; 
    private PasswordField password; 
    private Label status; 

    public ViewFXML(GridPane pane, Presenter p) 
    { 
        presenter = p; 
        this.pane = pane;
        password = (PasswordField) pane.getChildren().get(3);
        loginName = (TextField) pane.getChildren().get(1);
        status = (Label) pane.getChildren().get(5);
    } 
    
    public ViewFXML()
    {
        
    }
     
    public Pane getUI() 
    { 
        return pane; 
    } 

    public Presenter getPresenter() 
    { 
        return presenter; 
    } 
     
    public void showOkayMessage() 
    { 
        status.setText("Login erfolgreich."); 
    } 

    public void showInputError() 
    { 
        status.setText("Keine Login-Kennung " + 
                       "angegeben."); 
    }

    public void showLoginError() 
    { 
        status.setText("Login-Kennung bzw. Passwort " + 
                       "falsch."); 
    } 

    public String getLoginInput()
    {
        return loginName.getText();
    }

    public String getPwInput()
    {
        return password.getText();
    }

    public void resetInput()
    {
        loginName.clear();
        password.clear();
    }
}
