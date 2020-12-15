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

public class ImportFile {

    public static void display(Font font){
        Stage stage = new Stage();
        stage.setTitle("genealogy app!");
        stage.setHeight(600);
        stage.setWidth(700);
        stage.initModality(Modality.APPLICATION_MODAL);

        TextField importFileName = new TextField("Type file name here");
        SetStyle.setTextFieldStyle(font,importFileName);
        Button closeButton = new Button("Enter");
        SetStyle.setButtonStyle(font,closeButton);
        closeButton.setOnAction(e -> stage.close());

        VBox branch = new VBox(10);
        branch.getChildren().addAll(importFileName,closeButton);

        Scene scene = new Scene(branch);
        stage.setScene(scene);
        stage.showAndWait();

    }

    //can probably put the import file method from familyGraph in here


}
