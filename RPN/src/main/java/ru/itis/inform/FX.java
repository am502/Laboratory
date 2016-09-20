package ru.itis.inform;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

public class FX extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    RPN rpn = new RPN();
    public void start(Stage myStage) {
        myStage.setTitle("Calculator");

        GridPane rootNode = new GridPane();
        rootNode.setPadding(new Insets(15));
        rootNode.setHgap(5);
        rootNode.setVgap(5);

        Scene myScene = new Scene(rootNode, 300, 200);

        rootNode.add(new Label(""), 0, 0);
        TextField value = new TextField();
        rootNode.add(value, 1, 0);
        Button aButton = new Button("Calculate");
        rootNode.add(aButton, 1, 2);
        GridPane.setHalignment(aButton, HPos.LEFT);
        TextField result = new TextField();
        result.setEditable(false);
        rootNode.add(result, 1, 3);

        aButton.setOnAction(e -> {
            String str = String.valueOf(value.getText());
            result.setText(rpn.calculate(str));
        });

        myStage.setScene(myScene);
        myStage.show();
    }
}
