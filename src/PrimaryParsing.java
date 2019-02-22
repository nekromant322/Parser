import javafx.scene.control.Alert;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;

import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrimaryParsing
{
    public ArrayList<String> PrimeRef;
    public ArrayList<String> SecRef;
    public ArrayList<String> SecPreRef;
    public ArrayList<String> Repeats;

    public String log;
    private FileInputStream fs;
    PrimaryParsing(ArrayList<String> RawReferences) throws FileNotFoundException
    {


        PrimeRef = new ArrayList<>();
        Repeats = new ArrayList<>();
        for (String x : RawReferences)
        {

            try
            {
                if (x.substring(0, 4).equals("http") == false)
                {
                    x = "http://" + x;
                }
                //НАДО ИСПРАВИТЬ
                StringBuffer buffer = new StringBuffer(x);
                if (x.charAt(x.length() - 1) == '/')
                {
                    buffer.delete(buffer.length() - 1, buffer.length());
                    x = buffer.toString();
                }
                PrimeRef.add(x);
            }
            catch(Exception e)
            {
                PrimeRef.add(x);
            }
        }
    }

    void ShowLists()
    {
       System.out.println("Первичные ссылки обработанные");
       Controller.ShowConsole("Первичные ссылки обработанные");

            for (String x : PrimeRef)
            {
                System.out.println(x);
                Controller.ShowConsole(x);
            }
        try
        {

            System.out.println("Вторичные ссылки, полученные после парсинга");
            Controller.ShowConsole("Вторичные ссылки, полученные после парсинга");
            for (String x : SecRef)
            {
                System.out.println(x);
                Controller.ShowConsole(x);
            }
        }
        catch(NullPointerException e)
        {
            System.err.println("Не найдены вторичные ссылки");
            Controller.ShowConsole("Не найдены вторичные ссылки");
        }

        System.out.println("Кол-во полученных вторичных ссылок :" +SecRef.size() );
    }
    void SearchSecRef() throws IOException
    {

        Controller.ShowConsole("Формирование списка ссылок для поиска, и обработка ключевых слов...");
        Document doc = new Document("");
        SecRef = new ArrayList<>();

        for (String x : PrimeRef)
        {
            try
            {
                doc = Jsoup.connect(x).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
            }
            catch (IOException e)
            {
                System.err.println("Ошибка при подключении к " + x + " , запрос отклонен");
                Controller.ShowConsole("Ошибка при подключении к " + x + " ,запрос отклонен");
            }
            catch (IllegalArgumentException e)
            {
                System.err.println("Неверная ссылка " + x + " ,запрос отклонен");
                Controller.ShowConsole("Неверная ссылка " + x + " ,запрос отклонен");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


            Elements urls = doc.getElementsByTag("a");

            for (Element url : urls)
            {
                //... и вытаскиваем их название...
                //System.out.println("\nhref  <a> "+url.absUrl("href"));
                SecRef.add(url.absUrl("href"));
            }
            Elements urls1 = doc.getElementsByTag("link");
            for (Element url : urls1)
            {
                //... и вытаскиваем их название...
                //System.out.println("\nhref  <link> "+url.absUrl("href"));
                SecRef.add(url.absUrl("href"));
            }

        }


        ArrayList<String> Bad = new ArrayList<>();
        Scanner sc = new Scanner(new File("BadURL.txt"));
        while (sc.hasNext())
        {
            String URL = sc.nextLine();
            Repeats.add(URL);
        }

        for(int i = 0 ; i < SecRef.size();i++)  //подтираем ненужные с ресурсами
        {

            String x = SecRef.get(i);
            for(String m : Bad)
            {
                if(x.equals(m))
                {
                    SecRef.remove(i);
                    i--;
                    break;
                }
            }
            try
            {

                if(x.contains("youtube") || x.contains("mailto:") || x.contains(".jpg") || x.contains("yandex") || x.contains("telegram") ||x.contains("linkedin.com") || x.contains("twitter") || x.contains("vk.com") || x.contains("facebook") || x.contains("css") || x.contains(".js") || x.contains(".ico") || x.contains(".png") || x.contains(".xml") || x.contains(".svg"))
                {
                    SecRef.remove(i);
                    i--;
                }

            }
            catch(StringIndexOutOfBoundsException e)
            {

                System.err.println("Обнаружена битая вторичная ссылка, поиск по ней не производится");
                Controller.ShowConsole("Обнаружена битая вторичная ссылка, поиск по ней не производится");
                SecRef.remove(i);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }











       /* FileOutputStream of = new FileOutputStream("C:\\MyFiles\\Программирование\\Java\\2nd_urls.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(of));
        for(int i = 0 ; i < SecRef.size();i++)  //подтираем ненужные с ресурсами
        {
            bw.write(SecRef.get(i) + "\n");
        }
        bw.close();
        */
        for(String x : PrimeRef) //добавление первичных ссылок в начале финального списка
        {
            SecRef.add(0,x);
        }
        Set<String> set = new HashSet<>(SecRef);
        SecRef.clear();
        SecRef.addAll(set);



    }
    void FinalParsing(ArrayList<String> Kw) throws IOException
    {

        int i = 0;
        //OutputExcel output = new OutputExcel(outputpath);
        Document doc = new Document("");
        long startTime;
        long curTime = System.currentTimeMillis();
        long timing = 0;
        long sum = 0;

        for(String secondUrl : SecRef)
        {
            startTime = curTime;




            //System.out.println("поиск по ссылке " + secondUrl);
            Controller.ShowConsole("Поиск по ссылке " + secondUrl);
           // log = log + "поиск по ссылке " + secondUrl +  "\n";
            try
            {
                doc = Jsoup.connect(secondUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0) Gecko/20100101 Firefox/5.0").get();
            }

            catch(IllegalArgumentException e)
            {
                System.err.println("Битая ссылка:" + secondUrl);
                Controller.ShowConsole("Битая ссылка:" + secondUrl);


                FileWriter writer = new FileWriter("BadURL.txt", true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(secondUrl + "\n");
                bufferWriter.close();

            }
            catch (IOException e)
            {
                System.err.println("Ошибка при подключении к " + secondUrl + " ,запрос отклонен");
                Controller.ShowConsole("Ошибка при подключении к " + secondUrl + " ,запрос отклонен");


                FileWriter writer = new FileWriter("BadURL.txt", true);
                BufferedWriter bufferWriter = new BufferedWriter(writer);
                bufferWriter.write(secondUrl + "\n");
                bufferWriter.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
           /*
            String textHTMLraw = doc.html();
            HtmlToEnglish ht = new HtmlToEnglish(textHTMLraw);
            String textHTML = ht.NormalText;
            */
            String textHTML = doc.html();


            for(String kw : Kw)
            {
                String[] words;
                String delimeter = " "; // Разделитель
                words = kw.split(delimeter); // Разделения строки str с помощью метода split()
                for (int k = 0; k < words.length - 1; k++)
                {
                    for (int j = 0; j < words.length - 1; j++)
                    {

                        if (words[j].length() < words[j + 1].length())
                        {
                            String tmp = words[j];
                            words[j] = words[j + 1];
                            words[j + 1] = tmp;
                        }
                    }
                }
                //System.out.println("Ключевая фраза по словам");
                /*for(int k = 0 ; k < words.length ; k++)
                {
                    System.out.println(words[k]);
                }*/
                //Controller.ShowConsole("Изначальная фраза:" + kw);
                try
                {
                    int found = 0;
                    for (int k = 0; k < words.length; k++)
                    {
                        if (words[k].length() > 8)
                        {

                            char second = words[k].charAt(words[k].length() - 2);
                            char first = words[k].charAt(words[k].length() - 1);
                            words[k] = words[k].substring(0, words[k].length() - 2) + "?" + second + "?" + first + "?";
                        } else if (words[k].length() > 4)
                        {

                            char first = words[k].charAt(words[k].length() - 1);
                            words[k] = words[k].substring(0, words[k].length() - 1) + "?" + first + "?";

                        } else if (words[k].length() == 4)
                        {
                            words[k] = words[k] + "?";
                        }
                        //System.out.println("Преобразование в часть регулярки:" + words[k]);
                        //System.out.println("поиск по слову " + kw);
                        Pattern p = Pattern.compile("(?i)[\\w\\d\\s\\-\\p{L}\\'\\,]* ?" + words[k] + ".*?(?=\\.|\\<|\\!|\\?|\\n|\\t|$|\")");
                        //KeyWord.*?(?=\.|\<|\!|\?|\n|\t|$)
                        //Controller.ShowConsole("Поиск по регулярному выражению");
                        Matcher m = p.matcher(textHTML);

                        //Controller.ShowConsole("Поиск по регулярному выражению#2");
                    /* TO DO
                    ПОИСК нескольких предложений, а не одного
                    */

                        if (m.find() == true)
                        {
                            found++;
                            textHTML = m.group();
                            //Controller.ShowConsole("Поиск по регулярному выражению#3");
                            //System.out.println("Найдено одно из слов: " + words[k]);
                            //System.out.println("Полученный фрагмент:  " + textHTML);
                        }


                    }

                    if (found > words.length / 2 )
                    {
                        OutputExcel.SaveData(secondUrl, kw, textHTML);
                    }
                }
                catch (Exception e)
                {
                    System.err.println("Ошибка во время поиска по фразе на слове"  + kw);
                }
            }


            i++;
            //System.out.println("Пройдено "+ i +" из "+ SecRef.size());
            Controller.ShowConsole("Пройдено "+ i +" из "+ SecRef.size());
            Controller.percent = 1.0f*i/SecRef.size();

            //System.out.println("Кол-во найденных результатов: " + OutputExcel.counter);
            Controller.ShowConsole("Кол-во найденных результатов: " + OutputExcel.counter);
            curTime = System.currentTimeMillis();

            timing = (int) (curTime - startTime);
            if(timing > 3000)
            {
               sum += sum/i;
            }
            else
            {
                sum += timing;
            }
            Controller.timing = (double) (sum * (SecRef.size() - i)/i)/1000.0/60.0;
            //Controller.ShowConsole("Среднее время для одной ссылки:" + Controller.timing);

        }

        OutputExcel.SaveAndExit();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Принудительное завершение");
        alert.setHeaderText("Поиск не закончен");
        alert.setContentText("Результаты поиска сохранены в "+ Controller.adress2);
        alert.showAndWait();
        System.exit(0);



    }

}
