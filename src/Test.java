import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
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


       //C:\MyFiles\Программирование\Java\test.xlsx
        /*
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Введите название исходного файла ");
        System.out.println("Данные в нем должны быть представлены на двух листах");
        System.out.println("Первый с ссылками в левом столбце, второй с ключевыми словами/фразами");
        String inputpath  = reader.next();
        System.out.println("Введите путь к папке, куда следует поместить результат");
        String outputpath  = reader.next();
        outputpath = outputpath + "\\ParserResult.xlsx";
        */


        String inputpath = "C:\\MyFiles\\Программирование\\Java\\test.xlsx";
        String outputpath = "C:\\MyFiles\\Программирование\\Java";
        InputExcel input = new  InputExcel(inputpath);
       PrimaryParsing pp = new PrimaryParsing(input.PrimaryRef,outputpath);
        pp.SearchSecRef();
        pp.ShowLists();

        pp.FinalParsing(input.KeyWords);


    }
}


//<a href="htt   теш для поиска подсайтов
//<a href="/
