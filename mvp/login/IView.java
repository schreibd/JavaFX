package gui.mvp.login;

import javafx.scene.layout.Pane;

public interface IView
{
    public Pane getUI();

    public Presenter getPresenter();

    public void showOkayMessage();

    public void showInputError();

    public void showLoginError();
    
    public String getLoginInput();
    
    public String getPwInput();
    
    public void resetInput();

}
