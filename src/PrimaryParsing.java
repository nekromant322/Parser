import java.util.ArrayList;

public class PrimaryParsing
{
    public ArrayList<String> PrimeRef;
    public ArrayList<String> SecRef;


    PrimaryParsing(ArrayList<String> RawReferences)
    {
        PrimeRef = new ArrayList<>();
        for (String x : RawReferences)
        {
            
            if (x.substring(0, 4).equals("http") == false )
            {
                x = "http://" + x;
            }

            StringBuffer buffer = new StringBuffer(x);
            if (x.charAt(x.length() - 1) == '/')
            {
                buffer.delete(buffer.length() - 1, buffer.length());
                x = buffer.toString();
            }
            PrimeRef.add(x);
        }
    }

    void ShowLists()
    {
       System.out.println("Первичные ссылки обработанные");
            for (String x : PrimeRef)
            {
                System.out.println(x);
            }
        try
        {

            System.out.println("Вторичные ссылки, полученные после парсинга");
            for (String x : SecRef)
            {
                System.out.println(x);
            }
        }
        catch(NullPointerException e)
        {
            System.err.println("Не найдены вторичные ссылки");
        }


    }
}
