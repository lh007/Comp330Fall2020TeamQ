package GeneticsApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;

public class Gui extends Application implements EventHandler<ActionEvent> {

    Button importButton;
    Button exportButton;
    Button exploreGraphButton;
    Button addPerson;
    Button displayGraph;
    Label label1;


    @Override
    public void init() throws Exception {
        //this runs before the start method runs. can use this for loading stuff, probably firebase if I use that
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("genealogy app!");
        stage.setHeight(600);
        stage.setWidth(700);
        importButton = new Button("Import File");
        label1 = new Label("this is a test label");
        //stage.initModality(Modality.APPLICATION_MODAL); this will make it so no other window can be used until this is closed. can use it for opening small search windows or a login window

        VBox root = new VBox();
        root.getChildren().addAll(label1, importButton);
        Scene scene = new Scene(root);


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
