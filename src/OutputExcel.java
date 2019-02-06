import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutputExcel
{

    public static XSSFWorkbook book = new XSSFWorkbook();
    public static int counter = 0;
    private static Sheet sheet = book.createSheet("Результат парсинга");;

    public static String filename;
    OutputExcel(String fn) throws FileNotFoundException
    {

        filename = fn;

            sheet = book.createSheet("Результат парсинга");
            counter = 0;



    }
   public static void SaveData(String url, String kw, String text) throws IOException
   {
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
       /* if(counter ==10 )
        {
            this.SaveAndExit();
        }*/
    }
    @SuppressWarnings("deprecation")

   public static void SaveAndExit() throws IOException
    {


            FileOutputStream fs = new FileOutputStream(filename);
            book.write(fs); //запись изменений в файл
            book.close();
            //System.exit(0);

    }
   /* public static void Save() throws IOException
    {
        fs = new FileOutputStream(filename);
        book.write(fs); //запись изменений в файл
    }*/


}
