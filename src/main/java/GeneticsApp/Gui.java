package GeneticsApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.awt.*;

public class Gui extends Application implements EventHandler<ActionEvent> {

    Button importButton =  new Button("Import File");
    Button exportButton = new Button("Export File");
    Button exploreGraphButton = new Button("Explore Graph");
    Button addPerson = new Button("Add Person");
    Button displayGraph = new Button("Display Graph");
    Label label1 = new Label("Click which option you want");
    Font font = new Font("Cambria", 25);

    @Override
    public void init() throws Exception {
        //this runs before the start method runs. can use this for loading stuff, probably firebase if I use that
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("genealogy app!");
        stage.setHeight(600);
        stage.setWidth(700);

        SetStyle.setButtonStyle(font, importButton);
        SetStyle.setButtonStyle(font, exportButton);
        SetStyle.setButtonStyle(font, exploreGraphButton);
        SetStyle.setButtonStyle(font, addPerson);
        SetStyle.setButtonStyle(font, displayGraph);

        SetStyle.setLabelStyle(font, label1);
        //stage.initModality(Modality.APPLICATION_MODAL); this will make it so no other window can be used until this is closed. can use it for opening small search windows or a login window

        VBox root = new VBox(20);

        root.getChildren().addAll(label1, importButton, exportButton, exploreGraphButton, addPerson, displayGraph);
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==importButton){
            System.out.println("import pressed");
        }
        else if(actionEvent.getSource()==exportButton){
            System.out.println("export pressed");
        }
        else if(actionEvent.getSource()==exploreGraphButton){
            System.out.println("exploreGraphButton pressed");
        }
        else if(actionEvent.getSource()==addPerson){
            System.out.println("addPerson pressed");
        }
        else if(actionEvent.getSource()==displayGraph){
            System.out.println("displayGraph pressed");
        }
    }

    @Override
    public void stop() throws Exception {
        //runs after the application closes. can use this to close connections with firebase and end the whole process instead of what is done in the break of familygraph
    }


}
