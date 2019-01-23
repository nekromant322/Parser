import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrimaryParsing
{
    public ArrayList<String> PrimeRef;
    public ArrayList<String> SecRef;
    public ArrayList<String> Repeats;

    PrimaryParsing(ArrayList<String> RawReferences)
    {
        PrimeRef = new ArrayList<>();
        Repeats = new ArrayList<>();
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

        System.out.println("Кол-во полученных вторичных ссылок :" +SecRef.size() );
    }
    void SearchSecRef()
    {

        Document doc = new Document("");
        SecRef = new ArrayList<>();

        for(String x : PrimeRef)
        {
            try
            {
                doc = Jsoup.connect(x).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
            }
            catch (IOException e)
            {
                System.err.println("Ошибка при подключении к " + x + " ,запрос отклонен");
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }




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

            String x = SecRef.get(i);

            try
            {

                if(x.contains("css") || x.contains(".js") || x.contains(".ico") || x.contains(".png") || x.contains(".xml"))
                {
                    SecRef.remove(i);
                    i--;
                }

            }
            catch(StringIndexOutOfBoundsException e)
            {

                System.err.println("Обнаружена битая вторичная ссылка, поиск по ней не производится");
                SecRef.remove(i);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        for(String x : PrimeRef) //добавление первичных ссылок в начале финального списка
        {
            SecRef.add(0,x);
        }

    }
    void FinalParsing(ArrayList<String> Kw)
    {

        OutputExcel output = new OutputExcel();
        Document doc = new Document("");

        for(String secondUrl : SecRef)
        {
            System.out.println("поиск по ссылке " + secondUrl);
            for(String kw : Kw)
            {
                Pattern p = Pattern.compile("(?i)[\\w\\d\\s\\-\\ă\\Ă\\Î\\î\\ş\\Ş\\ţ\\Ţ\\ș\\Ș\\ț\\Ț\\Â\\â\\'\\,]* ?"+kw+ " ?.*?(?=\\.|\\<|\\!|\\?|\\n|\\t|$|\")");

                try
                {
                    doc = Jsoup.connect(secondUrl) .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
                }

                catch(IllegalArgumentException e)
                {
                    System.err.println("Битая ссылка:" + secondUrl);

                }
                catch (IOException e)
                {
                    System.err.println("Ошибка при подключении к " + secondUrl + " ,запрос отклонен");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }



                String textHTML = doc.html();


                    Matcher m = p.matcher(textHTML);
                    while( m.find())
                    {
                        if(!Repeats.contains(m.group()))
                        {
                            Repeats.add(m.group());
                            System.out.println(m.group()); //тут будет сохранение в эксель
                            output.SaveData();
                        }

                    }







            }

        }





    }
}
