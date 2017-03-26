package gui.mvp.login; 

import javafx.event.ActionEvent;
 
public class Presenter 
{ 
    private IView view; 
    private Model model; 
 
    public Presenter()
    {
        
    }
    
    public Presenter(IView view) 
    { 
        this.view = view; 
    } 
    
    public void setView(IView view)
    {
        this.view = view;
    }
    public void setModel(Model model) 
    { 
        this.model = model; 
    } 
    public void login(ActionEvent actionEvent) 
    {         
        String loginName = view.getLoginInput().trim();
        String password = view.getPwInput().trim();
        
        view.resetInput();
        
        if(loginName.isEmpty()) 
        { 
            view.showInputError(); 
        } 
        else if(model.isOkay(loginName, password)) 
        { 
            model.logLogin(loginName); 
            view.showOkayMessage(); 
        } 
        else 
        { 
            model.logLoginAttempt(loginName); 
            view.showLoginError(); 
        } 
    } 
} 
