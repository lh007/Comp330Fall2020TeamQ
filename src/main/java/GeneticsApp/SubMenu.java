package GeneticsApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class SubMenu {

    Stage stage = new Stage();
    VBox branch = new VBox(10);
    TextField inputField = new TextField("field");
    TextField inputField2 = new TextField("field");
    Button closeButton = new Button("enter");


    public void displayImportFile(Font font){ //instead of void have it return the textfield string
        stage.setTitle("Import file");
        stage.setHeight(600);
        stage.setWidth(700);
        stage.initModality(Modality.APPLICATION_MODAL);

        inputField.setText("Type file name here");
        SetStyle.setTextFieldStyle(font,inputField);

        closeButton.setText("Enter");
        SetStyle.setButtonStyle(font,closeButton);
        closeButton.setOnAction(e -> stage.close());//TODO something with the textfield when closing

        branch.getChildren().addAll(inputField,closeButton);

        Scene scene = new Scene(branch);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void displayExportFile(Font font){ //instead of void have it return the textfield string
        stage.setTitle("export file");
        stage.setHeight(600);
        stage.setWidth(700);
        stage.initModality(Modality.APPLICATION_MODAL);

        inputField.setText("Type name of export file here");
        SetStyle.setTextFieldStyle(font,inputField);

        closeButton.setText("Enter");
        SetStyle.setButtonStyle(font,closeButton);
        closeButton.setOnAction(e -> stage.close());//TODO something with the textfield when closing

        branch.getChildren().addAll(inputField,closeButton);

        Scene scene = new Scene(branch);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void displayGraph(Font font){
        stage.setTitle("This is where the picture of the graph will go");
        stage.setHeight(900);
        stage.setWidth(800);
        stage.initModality(Modality.APPLICATION_MODAL);

        closeButton.setText("press to export graph to pdf");
        SetStyle.setButtonStyle(font,closeButton);
        closeButton.setOnAction(e -> stage.close());//TODO something with exporting graph when closing

        //something with the display graph being added to the vbox

        branch.getChildren().addAll(closeButton);//add graph here
        Scene scene = new Scene(branch);
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void displayExplore(Font font){
        stage.setTitle("Explore Graph!");
        stage.setHeight(900);
        stage.setWidth(800);
        stage.initModality(Modality.APPLICATION_MODAL);

        inputField.setText("Type first name here");
        inputField2.setText("Type last name here");
        SetStyle.setTextFieldStyle(font,inputField);
        SetStyle.setTextFieldStyle(font,inputField2);

        closeButton.setText("Press to search. fields can be empty");
        SetStyle.setButtonStyle(font,closeButton);
        closeButton.setOnAction(e -> stage.close());//TODO something with exploring graph

        branch.getChildren().addAll(inputField,inputField2,closeButton);
        Scene scene = new Scene(branch);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void displayAddPerson(Font font){

    }

    //can probably put the import file method from familyGraph in here


}
