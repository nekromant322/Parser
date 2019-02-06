
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;


public class Controller
{

    public static String adress1;
    public static String adress2;
    public static String Log = "";
    public static double percent ;

        @FXML
        private Button FindButt;
        @FXML
        private TextArea Log_Text;
        @FXML
        private TextArea Input_path;



        @FXML
        final private ProgressBar Pb  = new ProgressBar(0);

        @FXML
        void initialize()
        {
            Input_path.setDisable(true);

            FindButt.setDisable(true);

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
                        Log = Log + Pb.getProgress() + "\n";
                        Pb.setProgress(percent);





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



            System.out.println(adress1);
            System.out.println(adress2);





            Log = Log + "\n" + "Входной файл: "  + adress1 + "\n" + "Выходной файл в: "  + adress2+ "\n";

            Log_Text.setText(Log);
            Log_Text.appendText("");




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

        }
        @FXML
        void ChooseInput(ActionEvent event) throws IOException
        {
            try
            {


                Stage primaryStage = new Stage();
                primaryStage.setTitle("Extension Filter Example");
                FileChooser fileChooser = new FileChooser();

                // Set extension filter
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
                fileChooser.getExtensionFilters().add(extFilter);

                // Show open file dialog
                File file = fileChooser.showOpenDialog(primaryStage);
                adress1 = file.getAbsolutePath();

                Input_path.setText(adress1);

                adress2 = adress1;
                int pos = 0;
                for(int i = adress2.length() - 1 ; i >=0; i--)
                {
                    if( adress2.charAt(i) == '\\')
                    {
                        pos = i;
                        break;
                    }
                }

                adress2 = adress2.substring(0, pos) ;

                FindButt.setDisable(false);
            }
            catch (Exception e)
            {
                Log = Log + "Неверно выбран файл" + "\n";
                e.printStackTrace();
            }

        }
        @FXML
        public void StopAll() throws InterruptedException
        {
            Log= Log + "Результат сохранен в " + adress2 + "\nРабота программы завершена принудительно\n";


            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Принудительное завершение");
            alert.setHeaderText("Поиск не закончен");
            alert.setContentText("Результаты поиска сохранены в "+ adress2);
            alert.showAndWait();

            System.exit(0);
        }





}
