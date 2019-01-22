import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class InputExcel
{

    public XSSFWorkbook book;

    public static void writeWorkbook(HSSFWorkbook wb, String fileName)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e)
        {
            //Обработка ошибки
        }
    }




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
    }
}
 //HSSFSheet sheet= wb.createSheet(["имя листа"]) создание нового