import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestString
{
    public static void main(String[] args)
    {


        String textHTML = "kdflnjgkfldsng . Achiziţie  publice și funcu private. adgadfgafdg dfgadfg adgf adf. dfg adf g.";
        Pattern p = Pattern.compile("(?i)[\\w\\d\\s\\-\\p{L}\\'\\,]* ?" + "achizit?i?i?"+ ".*?(?=\\.|\\<|\\!|\\?|\\n|\\t|$|\")");
        //KeyWord.*?(?=\.|\<|\!|\?|\n|\t|$)
        //Controller.ShowConsole("Поиск по регулярному выражению");
        Matcher m = p.matcher(textHTML);

        //Controller.ShowConsole("Поиск по регулярному выражению#2");
                    /* TO DO
                    ПОИСК нескольких предложений, а не одного
                    */

        if(m.find() == true)
        {
            textHTML = m.group();
            //Controller.ShowConsole("Поиск по регулярному выражению#3");

            System.out.println("Полученный фрагмент:  " + textHTML);
        }
        p = Pattern.compile("(?i)[\\w\\d\\s\\-\\p{L}\\'\\,]* ?" + "func?"+ ".*?(?=\\.|\\<|\\!|\\?|\\n|\\t|$|\")");

        m = p.matcher(textHTML);
        if(m.find() == true)
        {
            textHTML = m.group();
            //Controller.ShowConsole("Поиск по регулярному выражению#3");

            System.out.println("Полученный фрагмент:  " + textHTML);
        }

    }

}
