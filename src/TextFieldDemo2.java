

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TextFieldDemo2 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        TextField textField = new TextField("This is a Text");
        textField.setMinWidth(180);

        // Clear
        Button buttonClear = new Button("Clear");
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.clear();
            }
        });

        // Copy
        Button buttonCopy = new Button("Copy");

        // Click this button without losing focus of the other component
        buttonCopy.setFocusTraversable(false);

        buttonCopy.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.copy();
            }
        });

        // Cut
        Button buttonCut = new Button("Cut");

        // Click this button without losing focus of the other component
        buttonCut.setFocusTraversable(false);

        buttonCut.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.cut();
            }
        });

        // Paste
        Button buttonPaste = new Button("Paste");
        buttonPaste.setFocusTraversable(false);
        buttonPaste.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                textField.paste();
            }
        });

        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setVgap(5);
        root.setHgap(5);

        root.getChildren().addAll(textField, buttonClear,
                buttonCopy, buttonCut, buttonPaste);

        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("JavaFX TextField (o7planning.org)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}