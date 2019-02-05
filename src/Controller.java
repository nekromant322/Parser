
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Controller
{

    public static String adress1;
    public static String adress2;
    public static String Log = "";

        @FXML
        private Button FindButt;

        @FXML
        private TextField Input_Field;

        @FXML
        private TextField Output_Field;

        @FXML
        private TextArea Log_Text;

        @FXML
        private ScrollPane Scroll;

        @FXML
        void initialize()
        {
          /* Log_Text.textProperty().addListener((ChangeListener<Object>) (observable, oldValue, newValue) ->
           {
               Log_Text.setScrollTop(Double.MIN_VALUE); //this will scroll to the bottom
               //use Double.MIN_VALUE to scroll to the top
           });*/




            Task task_log = new Task<Void>()
            {
                @Override
                public Void call() throws InterruptedException
                {
                    while(true)
                    {
                        Log_Text.setText(Log);
                        //Log_Text.appendText("");
                        Log_Text.positionCaret( Log_Text.getText().length());
                        Log_Text.setEditable(true);
                        Log_Text.setScrollTop(Double.MAX_VALUE);
                        Thread.sleep(500);
                    }
                }
            };
            new Thread(task_log).start();


        }

        @FXML
        void FindAction(ActionEvent event) throws IOException
        {
            FindButt.setDisable(true);

            adress1 = Input_Field.getText().trim();
            adress2 = Output_Field.getText().trim();

            System.out.println(adress1);
            System.out.println(adress2);
            // вывод input output в консоль приложения




            Log = Log + "Входной файл: "  + adress1 + "\n" + "Выходной файл в: "  + adress2+ "\n";

            Log_Text.setText(Log);
            Log_Text.appendText("");
            adress1 = "C:\\MyFiles\\Программирование\\Java\\test.xlsx";
            adress2 = "C:\\MyFiles\\Программирование\\Java";
            InputExcel input = new  InputExcel(adress1);
            PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef,adress2);



            Task task = new Task<Void>()
            {
                @Override
                public Void call() throws IOException
                {
                    pp.SearchSecRef();
                    pp.ShowLists();
                    pp.FinalParsing(input.KeyWords);
                    return null;
                }
            };

            new Thread(task).start();


        }
        public static void ShowConsole(String new_log)
        {

           Log = Log + new_log + "\n";
          // Log_Text.setText(Log);
        }




}
