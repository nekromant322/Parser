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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Test
{

    public static void main(String[]args)
    {








      InputExcel input = new  InputExcel("C:\\MyFiles\\Программирование\\Java\\test.xlsx");
      PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef);
      pp.SearchSecRef();
      pp.ShowLists();

      pp.FinalParsing(input.KeyWords);



    }
}


//<a href="htt   теш для поиска подсайтов
//<a href="/
