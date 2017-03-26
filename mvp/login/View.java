package gui.mvp.login; 
 
import javafx.event.*; 
import javafx.scene.layout.*; 
import javafx.scene.control.*; 
 
public class View implements IView
{ 
    private Presenter presenter; 
     
    private GridPane pane; 
    private TextField loginName; 
    private PasswordField password; 
    private Label status; 
     
    public View() 
    { 
        presenter = new Presenter(this); 
        initView(); 
    } 
    
         
    private void initView() 
    { 
        pane = new GridPane(); 
        pane.add(new Label("Login-Kennung:"), 0, 0); 
        loginName = new TextField(); 
        pane.add(loginName, 1, 0); 
        pane.add(new Label("Passwort:"), 0, 1); 
        password = new PasswordField(); 
        pane.add(password, 1, 1); 
        Button b = new Button("Login"); 
        pane.add(b, 0, 2, 2, 1); 
        status = new Label(); 
        pane.add(status, 0, 3, 2, 1); 
        EventHandler<ActionEvent> h = e -> { 
            presenter.login(e); 
            loginName.setText(""); 
            password.setText(""); 
        }; 
        loginName.setOnAction(h); 
        password.setOnAction(h); 
        b.setOnAction(h); 
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