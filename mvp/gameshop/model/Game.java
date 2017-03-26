package gui.mvp.gameshop.model;

import javafx.beans.property.SimpleStringProperty;

public class Game
{
    private String name;
    private String genre;
    private double preis;
    private int anzahl;
    
    public Game(String name, double preis, int anzahl, String genre)
    {
        this.name = name;
        this.preis = preis;
        this.anzahl = anzahl;
        this.genre = genre;
    }
    
    public SimpleStringProperty getName()
    {
        SimpleStringProperty temp = new SimpleStringProperty(name);
        return temp;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public SimpleStringProperty getPreis()
    {
        SimpleStringProperty temp = new SimpleStringProperty(Double.toString(preis));
        return temp;
    }
    
    public void setPreis(double preis)
    {
        this.preis = preis;
    }
    
    public SimpleStringProperty getAnzahl()
    {
        SimpleStringProperty temp = new SimpleStringProperty(Integer.toString(anzahl));
        return temp;
    }
    
    public void setAnzahl(int anzahl)
    {
        this.anzahl = anzahl;
    }
    
    public SimpleStringProperty getGenre()
    {
        SimpleStringProperty temp = new SimpleStringProperty(genre);
        return temp;
    }
    
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

}
