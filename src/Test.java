import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Test
{

    public static void main(String[]args) throws IOException
    {
        /*
        Bitap bt = new Bitap();
        String text = " Вы не можете использовать int как аргумент типа для дженериков, поэтому он должен был быть int -специфическим методом (или тем, который использовал отражение, чтобы делать неприятные обманки).\n" +
                "Я считаю, что есть библиотеки, у которых есть автогенерированные версии этого типа метода для всех примитивных типов (т.е. есть шаблон, который копируется для каждого типа). Это уродливо, но так, как я боюсь: (\n" +
                "\n" +
                "Несмотря на то, что класс Arrays вышел до того, как дженерики прибыли на Java, все равно пришлось бы включать все ужасные перегрузки, если бы это было (предполагается, что вы хотите использовать примитивные массивы).";
        List<Integer> indexes =  bt.find(text, "м",1);


        int[] ret = new int[indexes.size()];
        for(int i = 0;i < ret.length;i++)
            ret[i] = indexes.get(i);


        for(int i = 0 ; i < text.length() ; i++)
        {
            if( indexes.contains(i) )
            {
                System.out.print( text.charAt(i));
            }
        }
*/




      InputExcel input = new  InputExcel("C:\\MyFiles\\Программирование\\Java\\test.xlsx");
      PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef);
      pp.SearchSecRef();
      pp.ShowLists();

      pp.FinalParsing(input.KeyWords);



    }
}


//<a href="htt   теш для поиска подсайтов
//<a href="/
