package gui.country.combo;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CountryInfo extends Application
{
    private ComboBox<Country> comboBox;

    private String[] labelTxt =
    { "Land:", "Hauptstadt:", "Einwohnerzahl:", "Fläche (in qkm):", "Bevölkerungsdichte (in Personen pro qkm):" };

    private DecimalFormat temp = new DecimalFormat("###,###");

    public void start(Stage primaryStage)
    {
        Country de = new Country("Deutschland", "Berlin", 82345000, 50000);
        Country be = new Country("Belgien", "Brüssel", 10839905, 30528);
        Country fr = new Country("Frankreich", "Paris", 82345000, 50000);
        Country li = new Country("Lichtenstein", "Wasweißich", 55000, 1289);
        ObservableList<Country> data1 = FXCollections.observableArrayList(de, fr, be, li);
        comboBox = new ComboBox<>(data1);
        VBox root = new VBox();
        GridPane grid = new GridPane();

        CheckBox checkBox = new CheckBox("exakte Angabe");

        for (int i = 0; i < labelTxt.length; i++)
        {
            grid.add(new Label(labelTxt[i]), 0, i);
        }

        comboBox.getSelectionModel().select(0);

        Label labelname = new Label(comboBox.getValue().getName());
        Label labelcapital = new Label(comboBox.getValue().getCapital());
        Label labelpeople = new Label(runden(comboBox.getValue().getPeople()));
        Label labelarea = new Label(runden(comboBox.getValue().getArea()));
        Label labelppqm = new Label(temp.format(comboBox.getValue().getPeople() / comboBox.getValue().getArea()));

        grid.add(labelname, 1, 0);
        grid.add(labelcapital, 1, 1);
        grid.add(labelpeople, 1, 2);
        grid.add(labelarea, 1, 3);
        grid.add(labelppqm, 1, 4);

        comboBox.setOnAction(ev -> {
                if (comboBox.getValue() != null)
                {
                    labelname.setText(comboBox.getValue().getName());
                    labelcapital.setText(comboBox.getValue().getCapital());
                    labelpeople.setText(runden(comboBox.getValue().getPeople()));
                    labelarea.setText(runden(comboBox.getValue().getArea()));
                    labelppqm.setText(temp.format(comboBox.getValue().getPeople() / comboBox.getValue().getArea()));
                }
            });

        checkBox.setOnAction(ev -> {
                if (checkBox.isSelected())
                {
                    labelpeople.setText(temp.format(comboBox.getValue().getPeople()));
                    labelarea.setText(temp.format(comboBox.getValue().getArea()));
                }
                else
                {
                    labelpeople.setText(runden(comboBox.getValue().getPeople()));
                    labelarea.setText(runden(comboBox.getValue().getArea()));
                }
            });

        TextField countryname = new TextField();
        countryname.setPromptText("Land");
        TextField capitalfield = new TextField();
        capitalfield.setPromptText("Hauptstadt");
        TextField population = new TextField();
        population.setPromptText("Bevölkerung");
        TextField areafield = new TextField();
        areafield.setPromptText("Fläche");

        Button add = new Button("Hinzufügen");
        Button delete = new Button("Löschen");
        HBox hbox = new HBox();
        hbox.getChildren().add(countryname);
        hbox.getChildren().add(capitalfield);
        hbox.getChildren().add(population);
        hbox.getChildren().add(areafield);
        hbox.getChildren().add(add);

        delete.setOnAction(ev -> {
                if (!data1.isEmpty())
                {
                    if (data1.size() == 1)
                    {
                        labelname.setText("");
                        labelcapital.setText("");
                        labelpeople.setText("");
                        labelarea.setText("");
                        labelppqm.setText("");
                        data1.remove(comboBox.getValue());
                        comboBox.setValue(null);
                        comboBox.setPromptText("Keine Länder vorhanden");
                    }

                    else if (comboBox.getValue() == data1.get(0))
                    {
                        comboBox.getSelectionModel().selectNext();
                        data1.remove(0);
                    }
                    else
                    {
                        /*
                         * kann zu outofbounds führen,
                         * wenn letztes element der combobox gelöscht wird,
                         * bei data1.get(z)
                         */
                        int z = 0;
                        z = data1.indexOf(comboBox.getValue());
                        data1.remove(comboBox.getValue());
                        comboBox.getSelectionModel().select(data1.get(z));
                        
                    }
                }
            });
        add.setOnAction(ev -> {
                if (countryname.getText().matches("[a-zA-Z]+") && capitalfield.getText().matches("[a-zA-Z]+") && population.getText().matches("[0-9]+") && areafield.getText().matches("[0-9]+"))
                {
                    Country neu = new Country(countryname.getText(), capitalfield.getText(), Long.parseLong(population.getText()), Long.parseLong(areafield.getText()));
                    comboBox.getSelectionModel().select(neu);
                    data1.add(neu);
                }
                else
                {
                    throw new IllegalArgumentException("Falsche Eingabe");
                }
                countryname.clear();
                capitalfield.clear();
                population.clear();
                areafield.clear();

            });
        root.getChildren().addAll(comboBox, checkBox, grid, hbox, delete);
        Scene scene = new Scene(root, 700, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public String runden(long x)
    {

        String i = "";
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
        return i;

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
