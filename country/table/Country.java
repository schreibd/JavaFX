package gui.country.table;

import java.text.DecimalFormat;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country
{
    private DecimalFormat temp = new DecimalFormat("###,###");
    
    private SimpleStringProperty name;

    private SimpleStringProperty capital;

    private SimpleLongProperty people;

    private SimpleLongProperty area;

    public Country(String name, String capital, long people, long area)
    {
        this.name = new SimpleStringProperty(name);
        this.capital = new SimpleStringProperty(capital);
        this.people = new SimpleLongProperty(people);
        this.area = new SimpleLongProperty(area);
    }

    public SimpleStringProperty getName()
    {
        return name;
    }

    public SimpleStringProperty getCapital()
    {
        return capital;
    }

    public SimpleLongProperty getPeople()
    {
        return people;
    }

    public SimpleLongProperty getArea()
    {
        return area;
    }


}
