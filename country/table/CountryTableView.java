package gui.country.table;

import java.text.DecimalFormat;

import javafx.application.Application;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CountryTableView extends Application
{
    private DecimalFormat temp = new DecimalFormat("###,###");
    
public void start(Stage primaryStage)
{
    Label label = new Label("Liste der Länder");
    label.setFont(new Font("Arial", 20));
    
    CheckBox checkBox = new CheckBox("Genaue Angabe");
    
    ObservableList<Country> data = FXCollections.observableArrayList
    (
    new Country("Afghanistan", "Kabul", 3412901, 32489),
    new Country("NeuRussland", "Donetsk", 32148, 48483),
    new Country("JapanischeStreitinsel", "KungPao", 388, 20),
    new Country("Tattooine", "MosEisley", 400048690, 368040),
    new Country("Ooo", "CandyKingdom", 502093012, 293080)
    );
    
    TableView<Country> tblvw = new TableView<>(data);
    
    TableColumn<Country, String> name = new TableColumn<>("Name");
    name.setPrefWidth(200);
    name.setCellValueFactory
    (
    item -> item.getValue().getName()
    );
    tblvw.getColumns().add(name);
    
    TableColumn<Country, String> capital = new TableColumn<>("Hauptstadt");
    capital.setPrefWidth(200);
    capital.setCellValueFactory
    (
    item -> item.getValue().getCapital()
    );
    tblvw.getColumns().add(capital);
    
    TableColumn<Country,String> people = new TableColumn<>("Einwohneranzahl");
    people.setPrefWidth(200);
    people.setCellValueFactory
    (
    item -> runden(item.getValue().getPeople().get())
    );
    tblvw.getColumns().add(people);
    
    TableColumn<Country,String> area = new TableColumn<>("Fläche");
    area.setPrefWidth(200);
    area.setCellValueFactory
    (
    item -> runden(item.getValue().getArea().get())
    );
    tblvw.getColumns().add(area);
    
    TableColumn<Country,String> ppqm = new TableColumn<>("Einwohner pro qkm");
    ppqm.setPrefWidth(200);
    ppqm.setCellValueFactory
    (
    item -> runden(item.getValue().getPeople().get() / item.getValue().getArea().get())
    );
    tblvw.getColumns().add(ppqm);
    
    checkBox.setOnAction(
    e->
    {
      if (checkBox.isSelected())
      {
          tblvw.getColumns().clear();
          /*Option1:
          area.setCellValueFactory
          (
          item -> item.getValue().getArea().asString()
          );
          */
          TableColumn<Country,String> temp = new TableColumn<>("Fläche");
          temp.setPrefWidth(200);
          temp.setCellValueFactory(item -> punkteSetzen(item.getValue().getArea().get()));
           
          TableColumn<Country,String> temp2 = new TableColumn<>("Einwohneranzahl");
          temp2.setPrefWidth(200);
          temp2.setCellValueFactory(item -> punkteSetzen(item.getValue().getPeople().get()));
          
          TableColumn<Country,String> temp3 = new TableColumn<>("Einwohner pro qkm");
          temp3.setPrefWidth(200);
          temp3.setCellValueFactory(item -> punkteSetzen(item.getValue().getPeople().get()/item.getValue().getArea().get()));
          
          tblvw.getColumns().addAll(name, capital, temp2, temp, temp3);
      }
      
      if (!checkBox.isSelected())
      {
          tblvw.getColumns().clear();
          
          TableColumn<Country,String> temp = new TableColumn<>("Fläche");
          temp.setPrefWidth(200);
          temp.setCellValueFactory(item -> runden(item.getValue().getArea().get()));
          
          TableColumn<Country,String> temp2 = new TableColumn<>("Einwohneranzahl");
          temp2.setPrefWidth(200);
          temp2.setCellValueFactory(item -> runden(item.getValue().getPeople().get()));
          
          TableColumn<Country,String> temp3 = new TableColumn<>("Einwohner pro qkm");
          temp3.setPrefWidth(200);
          temp3.setCellValueFactory(item -> runden(item.getValue().getPeople().get()/item.getValue().getArea().get()));
          
          tblvw.getColumns().addAll(name, capital, temp2, temp, temp3);
      }
      });
    
    VBox vbox = new VBox();
    vbox.getChildren().addAll(label, checkBox, tblvw);
    
    Scene scene = new Scene(vbox, 1000, 450);
    primaryStage.setScene(scene);
    primaryStage.show();
}

public SimpleStringProperty punkteSetzen(long x)
{
    String i;
    i = temp.format(x);
    return new SimpleStringProperty(i);
    
}

public SimpleStringProperty runden(long x)
{

    String i;
    if (x < 999999 && x > 9999)
    {
        if (x % 1000 < 500)
        {
            i = temp.format(x - (x % 1000));
        }
        else
        {
            i = temp.format(x + (1000 - (x % 1000)));
        }
    }

    else if (x >= 999999)
    {
        if (x % 1000000 < 500000)
        {
            i = x / 1000000 + " Mill.";
        }
        else
        {
            i = (x + (1000000 - (x % 1000000))) / 1000000 + " Mill.";
        }
    }
    else
    {
        i = temp.format(x);
    }
    return new SimpleStringProperty(i);

}

public static void main(String[]args)
{
    launch(args);
}
}
