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

    private XSSFWorkbook book;
    private int counter;
    private Sheet sheet;
    FileOutputStream fs;
    String filename;
    OutputExcel(String fn) throws FileNotFoundException
    {

        filename = fn;
            book = new XSSFWorkbook();
            sheet = book.createSheet("Результат парсинга");
            counter = 0;



    }
   void  SaveData(String url, String kw, String text)
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
    }
    @SuppressWarnings("deprecation")
    @Override
    protected void finalize() throws Throwable
    {
        try
        {
            fs = new FileOutputStream(filename);
            book.write(fs); //запись изменений в файл
            book.close();
        }
        finally
        {
            super.finalize();
        }
    }

}
