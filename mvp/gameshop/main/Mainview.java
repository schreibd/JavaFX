package gui.mvp.gameshop.main;



import javax.script.SimpleBindings;

import gui.mvp.gameshop.model.Game;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Mainview extends VBox
{
    Mainpresenter mainpresenter;

    TableView<Game> tableview;

    HBox toolLeiste;

    Button overview;

    Button add;

    Button delete;

    Button change;
    
    Button showTree;
    
    ObservableList<Game> temp;

    public Mainview()
    {
        mainpresenter = new Mainpresenter(this);
        initView();
    }
    
    public Mainpresenter getMainpresenter()
    {
        return mainpresenter;
    }

    public void initView()
    {
        toolLeiste = new HBox();

        overview = new Button("Übersicht");
        add = new Button("Hinzufügen");
        add.setOnAction(e -> showAddDialog());
        delete = new Button("Löschen");
        showTree = new Button("Genres anzeigen");
        delete.setOnAction(e -> 
        {
            if(!tableview.getSelectionModel().isEmpty())           
            {
                showConfirmDialog();
            }
        });
        change = new Button("Ändern");
        change.setOnAction(e ->
        {
            if(!tableview.getSelectionModel().isEmpty())
            {
                showChangeDialog();
            }
        });

        toolLeiste.getChildren().addAll(overview, add, delete, change);
        this.getChildren().add(toolLeiste);

    }

    public void showTable()
    {
        this.getChildren().clear();
        this.getChildren().add(toolLeiste);
        temp = FXCollections.observableArrayList(mainpresenter.copyList());
        tableview = new TableView<Game>(temp);
        
        TableColumn<Game, String> name = new TableColumn<>("Spiel");
        name.setCellValueFactory(item -> item.getValue().getName());
        
        TableColumn<Game, String> preis = new TableColumn<>("Preis");
        preis.setCellValueFactory(item -> item.getValue().getPreis());
        
        TableColumn<Game, String> anzahl = new TableColumn<>("Anzahl");
        anzahl.setCellValueFactory(item -> item.getValue().getAnzahl());
        
        tableview.getColumns().addAll(name, preis, anzahl);
        this.getChildren().add(tableview);
    }
    
    public void showAddDialog()
    {
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        TextField name = new TextField();
        name.setPromptText("Spielname");
        TextField preis = new TextField();
        preis.setPromptText("Preis");
        TextField anzahl = new TextField();
        anzahl.setPromptText("Anzahl");
        TextField genre = new TextField();
        genre.setPromptText("Genre");
        
        Button confirm = new Button("Bestätigen");
        Button cancel = new Button("Abbrechen");
        
        
        hbox1.getChildren().addAll(name, preis, anzahl, genre);
        hbox2.getChildren().addAll(confirm, cancel);
        
        vbox.getChildren().addAll(hbox1, hbox2);
        Scene scene = new Scene(vbox, 300, 100);
        Stage addStage = new Stage();
        addStage.setScene(scene);
        addStage.setTitle("Neues Spiel hinzufügen");
        addStage.initModality(Modality.APPLICATION_MODAL);
        
        
        cancel.setOnAction(e -> addStage.close());
        confirm.setOnAction(e -> 
        {
                mainpresenter.addGame(name.getText(), preis.getText(), anzahl.getText(), genre.getText());
                addStage.close();

        });
        
        addStage.showAndWait();   
    }
    
    public void showConfirmDialog()
    {
        VBox vbox = new VBox();
        Label label = new Label("Sind Sie sicher, dass Sie das Spiel löschen wollen?");
        HBox hbox = new HBox();
        Button confirm = new Button("Ja");
        Button cancel = new Button("Nein");
        hbox.getChildren().addAll(confirm, cancel);
        vbox.getChildren().addAll(label, hbox);
        Stage stage = new Stage();
        Scene scene = new Scene(vbox, 150, 100);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        
        
        confirm.setOnAction(e ->
        {
           mainpresenter.deleteGame(tableview.getSelectionModel().getSelectedIndex());
           stage.close();
        });
        
        stage.showAndWait();
        
    }
    
    public void showChangeDialog()
    {
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        TextField name = new TextField();
        name.setText(tableview.getSelectionModel().getSelectedItem().getName().get());
        SimpleBooleanProperty enabler = new SimpleBooleanProperty();
        name.textProperty().addListener((e, ov, nv )-> {
                enabler.set(nv.isEmpty());
        });
        

        TextField preis = new TextField();
        preis.setText(tableview.getSelectionModel().getSelectedItem().getPreis().getValue());
        SimpleBooleanProperty enabler2 = new SimpleBooleanProperty();
        preis.textProperty().addListener((e, ov, nv)->
        {
            enabler2.set(nv.isEmpty());
        });
        
        TextField anzahl = new TextField();
        anzahl.setText(tableview.getSelectionModel().getSelectedItem().getAnzahl().getValue());
        SimpleBooleanProperty enabler3 = new SimpleBooleanProperty();
        anzahl.textProperty().addListener((e, ov, nv) -> 
        {
            enabler3.set(nv.isEmpty());
        });
        
        TextField genre = new TextField();
        genre.setText(tableview.getSelectionModel().getSelectedItem().getGenre().getValue());
        SimpleBooleanProperty enabler4 = new SimpleBooleanProperty();
        genre.textProperty().addListener((e, ov, nv) ->
        {
           enabler4.set(nv.isEmpty()); 
        });
        
        hbox1.getChildren().addAll(name, preis, anzahl, genre);
        
        Button confirm = new Button("Bestätigen");
        Button cancel = new Button("Abbrechen");
        confirm.disableProperty().bind(enabler.or(enabler2.or(enabler3.or(enabler4))));
        hbox2.getChildren().addAll(confirm, cancel);
        
        vbox.getChildren().addAll(hbox1, hbox2);
        
        Scene scene = new Scene(vbox, 500, 70);
        Stage stage = new Stage();
        stage.setScene(scene);
        
        cancel.setOnAction(e ->
        {
            stage.close();
        });
        
        confirm.setOnAction(e ->
        {    
                String[] temp = new String[3];
                temp[0] = name.getText();
                temp[1] = preis.getText();
                temp[2] = anzahl.getText();
                temp[3] = genre.getText();
                mainpresenter.changeGame(tableview.getSelectionModel().getSelectedIndex(), temp);
                stage.close();
        });
        
        stage.showAndWait();
        
        
        
        
    }
}
