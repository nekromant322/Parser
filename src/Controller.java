
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Controller
{

    public static String adress1;
    public static String adress2;
    public static String Log = "Логи действий:";

        @FXML
        private Button FindButt;

        @FXML
        private TextField Input_Field;

        @FXML
        private TextField Output_Field;

        @FXML
        private TextArea Log_Text;

        @FXML
        void FindAction(ActionEvent event) throws IOException
        {

            adress1 = Input_Field.getText().trim();
            adress2 = Output_Field.getText().trim();

            System.out.println(adress1);
            System.out.println(adress2);
            // вывод input output в консоль приложения



            Log_Text.setText("Hello");
            Log = Log + "\n" + adress1 + "\n" + adress2;
            Log_Text.setText(Log);
            String inputpath = "C:\\MyFiles\\Программирование\\Java\\test.xlsx";
            String outputpath = "C:\\MyFiles\\Программирование\\Java";
            InputExcel input = new  InputExcel(inputpath);
            PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef,outputpath);

            pp.SearchSecRef();
            pp.ShowLists();

            Runnable task = () ->
            {

                try
                {
                    pp.FinalParsing(input.KeyWords);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

            };
            task.run();
            Thread thread = new Thread(task);
            thread.start();

            Log = Log + "Парсинг запущен в отдельном потоке" +"\n";
            Log_Text.setText(Log);
        }
        public static void ShowConsole(String new_log)
        {

           Log = Log + new_log + "\n";
          // Log_Text.setText(Log);
        }




}
