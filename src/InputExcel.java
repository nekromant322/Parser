import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class InputExcel
{


    public ArrayList<String> PrimaryRef;
    public ArrayList<String> KeyWords;
    private XSSFWorkbook book;

    InputExcel(String filename)
     {
        try {
            //POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filename));
            FileInputStream fs = new FileInputStream(filename);
            book = new XSSFWorkbook(fs);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

         PrimaryRef = new ArrayList<>();
         KeyWords = new ArrayList<>();
         XSSFSheet sheet = book.getSheetAt(0);
         XSSFSheet sheetwords = book.getSheetAt(1);

         Iterator rowIter = sheet.rowIterator();

         while (rowIter.hasNext()) //поиск ссылок до конца таблицы
         {
             XSSFRow row = (XSSFRow) rowIter.next();
             XSSFCell cell = row.getCell(0);
             PrimaryRef.add(cell.getRichStringCellValue().getString());

         }
         rowIter = sheetwords.rowIterator();
         while (rowIter.hasNext())  //поиск ключевых слов и фраз до конца таблицы
         {
             XSSFRow row = (XSSFRow) rowIter.next();
             XSSFCell cell = row.getCell(0);
             KeyWords.add(cell.getRichStringCellValue().getString());
         }
         PrimaryRef.removeAll(Arrays.asList("", null, " "));
         KeyWords.removeAll(Arrays.asList("", null, " "));


         for(String x : KeyWords)
         {
             HtmlToEnglish hm = new HtmlToEnglish(x);
             x = hm.NormalText;
             System.out.println(x);
         }

    }
}
 //HSSFSheet sheet= wb.createSheet(["имя листа"]) создание нового