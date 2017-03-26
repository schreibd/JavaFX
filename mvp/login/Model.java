package gui.mvp.login; 
 
import java.util.*; 
 
public class Model 
{ 
    private HashMap<String, String> userList; 
    private ArrayList<String> report; 
     
    public Model() 
    { 
        userList = new HashMap<>(); 
        report = new ArrayList<>(); 
        userList.put("hänsel", "password1"); 
        userList.put("gretel", "password2"); 
        userList.put("aschenputtel", "password3"); 
        userList.put("wolf", "password4"); 
        userList.put("7zwerge", "password5"); 
        userList.put("hansimglück", "password6"); 
    } 
    public boolean isOkay(String loginName,  
                          String password) 
    { 
        return password.equals(userList.get(loginName)); 
    } 
    public void logLogin(String loginName) 
    { 
        report.add("Erfolgreiches Login von " + 
                   loginName); 
    } 
    public void logLoginAttempt(String loginName) 
    { 
        report.add("Versuch eines Login von " + 
                   loginName); 
    } 
} 
