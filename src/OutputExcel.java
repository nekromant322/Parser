import javafx.scene.control.Alert;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class OutputExcel
{

    public static XSSFWorkbook book = new XSSFWorkbook();
    public static int counter = 0;
    private static Sheet sheet = book.createSheet("Результат парсинга");;
    private static ArrayList<String> results = new ArrayList<String>();
    public static String filename;
    OutputExcel(String fn) throws FileNotFoundException
    {

        filename = fn;

            sheet = book.createSheet("Результат парсинга");
            counter = 0;



    }
   public static void SaveData(String url, String kw, String text) throws IOException
   {
        boolean repeats = false;
        for(int i = 0 ; i < results.size();i++)
        {
            if(results.get(i).equals(text))
                repeats = true;
        }
        if(!repeats)
        {
            results.add(text);
            Row row = sheet.createRow(counter);


            Cell for_url = row.createCell(0);
            for_url.setCellValue(url);

            Cell for_kw = row.createCell(1);
            for_kw.setCellValue(kw);

            Cell for_text = row.createCell(2);
            for_text.setCellValue(text);

            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            counter++;
        }

    }
    @SuppressWarnings("deprecation")

   public static void SaveAndExit() throws IOException
    {
            if(counter != 0)
            {
                FileOutputStream fs = new FileOutputStream(filename);
                book.write(fs); //запись изменений в файл
                book.close();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Поиск закончен");
            alert.setHeaderText("Кол-во результатов :" + counter);
            alert.setContentText("Результаты поиска сохранены в "+ Controller.adress2);

            //НЕ ЗАБЫТЬ ДОДЕЛАТЬ
            //System.exit(0);

    }
   /* public static void Save() throws IOException
    {
        fs = new FileOutputStream(filename);
        book.write(fs); //запись изменений в файл
    }*/


}
