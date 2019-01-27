

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Parser");
        //primaryStage.getIcons().add(new Image("sample/assets/delivryicon.png"));

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
        Controller cl = new Controller();
        TextField textField = new TextField("Tran");
        textField.setMinWidth(120);
        // cl.initialize();
    }

    public static void main(String[] args) {
        launch(args);
    }

}