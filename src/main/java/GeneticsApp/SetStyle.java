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

public class SetStyle {

    public static void setButtonStyle(Font font, Button button){
        ButtonController controller = new ButtonController();
        button.setTextFill(Color.web("#e80d11"));
        button.setFont(font);
        button.setOnAction(controller::handle);
    }
    public static void setLabelStyle(Font font, Label label){
        label.setFont(font);
        label.setTextFill(Color.web("#3131b7"));
    }
}
