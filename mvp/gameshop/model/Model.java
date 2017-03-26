package gui.mvp.gameshop.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
    ObservableList<Game> gameList;
    
    public Model()
    {
        gameList = FXCollections.observableArrayList();
        createGameCatalogue();
    }
    
    public void createGameCatalogue()
    {
        Game halo = new Game("Halo", 49.99, 3, "Shooter");
        Game wow = new Game("World of Warcraft", 15.35, 4, "MMORPG");
        Game swtor = new Game("Star Wars - The Old Republic", 5.00, 33, "MMORPG");
        gameList.addAll(halo, wow, swtor);    
    }
    
    public void createGame(String name, double preis, int anzahl, String genre)
    {
        Game temp = new Game(name, preis, anzahl, genre);
        gameList.add(temp);
    }
    
    public void deleteGame(int i)
    {
        gameList.remove(i);
    }
    
    public ObservableList<Game> getList()
    {
        return gameList;
    }
    
    public void changeGame(int i, String[] newValues)
    {
        Game temp = new Game(newValues[0], Double.parseDouble(newValues[1]), Integer.parseInt(newValues[2]), newValues[3]);
        gameList.set(i, temp);
    }

}
