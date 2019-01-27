import java.text.Normalizer;
import java.util.*;

public class HtmlToEnglish
{
    public String NormalText = "";

    HtmlToEnglish(String htmltext)
    { System.out.println("Начало обработки");
       Map<String,String> map = new HashMap<String,String>();



        String A = "AĄÀÁÂÃÄÅĀĂ";
        String a = "aąàáâãäåāă";
        String C = "CĈĊČĆÇ";
        String c = "cĉċčćç";
        String D = "DĐĎ";
        String d = "dđď";
        String E = "EĒĔĖĘĚÈÉÊË";
        String e = "eēĕėęěèéêë";
        String G = "GǦĜĞĠĢ";
        String g = "gǧĝğġģ";
        String H = "HĤĦ";
        String h = "hĥħ";
        String I = "IĨĪĬĮİÌÍÎÏ";
        String i = "iĩīĭįıìíîï";
        String J = "JĲĴ";
        String j = "jĳĵ";
        String N = "NÑŃŇ";
        String n = "nñńņňŉŋ";
        String K = "KǨĶ";
        String k = "kǩķ";
        String L = "LĹĻĽĿŁ";
        String l = "lĺļľŀł";
        String O = "OŌŎŐÒÓÔÕÖØ";
        String o = "oōŏőòóôõöø";
        String R = "RŔŖŘ";
        String r = "rŕŗř";
        String S = "SßŚŜŞŠ";
        String s = "sśŝşš";
        String T = "TŢŤŦ";
        String t = "tţťŧ";
        String U = "UŨŪŬŮŰŲÙÚÛÜ";
        String u = "uũūŭůűųùúûü";
        String Y = "YŶÝŸ";
        String y = "yŷýÿ";
        String W = "WŴ";
        String w = "wŵ";
        String Z = "ZŹŻŽŹŻŽ";
        String z = "zźżž";
        map.put("A",A);
        map.put("a",a);
        map.put("C",C);
        map.put("c",c);
        map.put("D",d);
        map.put("d",d);
        map.put("E",e);
        map.put("e",e);
        map.put("G",g);
        map.put("g",g);
        map.put("H",H);
        map.put("h",h);
        map.put("I",I);
        map.put("i",i);
        map.put("J",J);
        map.put("j",j);
        map.put("N",N);
        map.put("n",n);
        map.put("K",K);
        map.put("k",k);
        map.put("L",l);
        map.put("l",l);
        map.put("O",O);
        map.put("o",o);
        map.put("R",R);
        map.put("r",r);
        map.put("S",S);
        map.put("s",s);
        map.put("T",T);
        map.put("t",t);
        map.put("U",U);
        map.put("u",u);
        map.put("Y",Y);
        map.put("y",y);
        map.put("W",W);
        map.put("w",w);
        map.put("Z",Z);
        map.put("z",z);





        for(int count = 0 ; count < htmltext.length() ; count++)
        {
            char sym = htmltext.charAt(count);

            //System.out.println(sym);
            Iterator it = map.entrySet().iterator();
            boolean flag = false;
            Set< Map.Entry< String,String> > st = map.entrySet();
            for (Map.Entry< String,String> pair:st)
            {

                String buf = (String) pair.getValue();
               // System.out.println(pair.getKey() + " = " + pair.getValue());
                String buf2 = sym + "";
                if (buf.contains(buf2) == true)
                {
                    //System.out.println("Zamena " +buf2);
                    buf = (String) pair.getKey();
                    sym = buf.charAt(0);
                    break;
                }
            }

            NormalText = NormalText + sym;


        }
        System.out.println("Конец обработки");
        NormalText =  NormalText.toLowerCase();
        System.out.println("Переведено в нижний");

    }

}
