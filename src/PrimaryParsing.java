import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
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
    void SearchSecRef()
    {

        Document doc = new Document("");
        SecRef = new ArrayList<>();

        for(String x : PrimeRef)
        {
            try
            {
                doc = Jsoup.connect(x).get();
            }
            catch (IOException e)
            {
                System.err.println("Ошибка при подключении к " + x + " ,запрос отклонен");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }


            String name = doc.title();
            System.out.println("Name of page html: " + name);

            Elements urls = doc.getElementsByTag("a");

            for(Element url : urls)
            {
                //... и вытаскиваем их название...
                //System.out.println("\nhref  <a> "+url.absUrl("href"));
                SecRef.add(url.absUrl("href"));
            }
           Elements urls1 = doc.getElementsByTag("link");
            for(Element url : urls1)
            {
                //... и вытаскиваем их название...
                //System.out.println("\nhref  <link> "+url.absUrl("href"));
                SecRef.add(url.absUrl("href"));
            }

        }
        for(int i = 0 ; i < SecRef.size();i++)  //подтираем ненужные с ресурсами
        {
            try
            {

                if(SecRef.get(i).contains(".css") || SecRef.get(i).contains(".js") || SecRef.get(i).contains(".ico") || SecRef.get(i).contains(".png"))
                {
                    SecRef.remove(SecRef.get(i));
                }
            }
            catch(NullPointerException e)
            {
                System.err.println("Обнаружена битая вторичная ссылка, поиск по ней не производится");
            }
        }
        System.out.println("Кол-во полученных вторичных ссылок :" +SecRef.size() );
    }
}
