package GeneticsApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;

public class Gui extends Application implements EventHandler<ActionEvent> {

    Button importButton;
    Button exportButton;
    Button exploreGraphButton;
    Button addPerson;
    Button displayGraph;


    @Override
    public void init() throws Exception {
        //this runs before the start method runs. can use this for loading stuff, probably firebase if I use that
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("genealogy app!");
        importButton = new Button("Import File");


        StackPane startLayout = new StackPane();
        //startLayout.getChildren().add(importButton);

        Scene scene = new Scene(startLayout, 600,400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }

    @Override
    public void stop() throws Exception {
        //runs after the application closes. can use this to close connections with firebase and end the whole process instead of what is done in the break of familygraph
    }
}
