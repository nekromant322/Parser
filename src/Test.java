import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

class Test
{

    public static void main(String[]args)
    {

        String example = "https://www.nytimes.com/vi-assets/static-assets/global-a2de946a9a0e78407da4b3405271f391.css";
        if(example.contains(".css"))
        {
            System.out.println("contains");
        }








      InputExcel input = new  InputExcel("C:\\MyFiles\\Программирование\\Java\\test.xlsx");
      PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef);
      pp.SearchSecRef();
      pp.ShowLists();
        System.out.println("TEEEEEEEEEEEEEEEEEXT");
      pp.FinalParsing(input.KeyWords);



    }
}


//<a href="htt   теш для поиска подсайтов
//<a href="/
