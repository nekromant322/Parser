import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.Iterator;

class Test
{

    public static void main(String[]args)
    {


      InputExcel input = new  InputExcel("C:\\MyFiles\\Программирование\\Java\\test.xlsx");
      ArrayList<String> ref = new ArrayList<>();

      XSSFSheet sheet = input.book.getSheetAt(0);


        Iterator rowIter = sheet.rowIterator();
        while (rowIter.hasNext()) {
            XSSFRow row = (XSSFRow) rowIter.next();
            XSSFCell cell = row.getCell(0);
            ref.add(cell.getRichStringCellValue().getString());

        }


        for (String  x : ref)
            System.out.println(x);



    }
}


//<a href="htt   теш для поиска подсайтов
//<a href="/
