import java.text.Normalizer;
import java.util.*;

public class HtmlToEnglish
{
    public String NormalText = "";

    HtmlToEnglish(String htmltext)
    {

       Map<String,String> map = new HashMap<String,String>();



        String A = "ĄÀÁÂÃÄÅĀĂA";
        String a = "ąàáâãäåāăa";
        String C = "ĈĊČĆÇC";
        String c = "ĉċčćçc";
        String D = "ĐĎD";
        String d = "đďd";
        String E = "ĒĔĖĘĚÈÉÊËE";
        String e = "ēĕėęěèéêëe";
        String G = "ǦĜĞĠĢG";
        String g = "ǧĝğġģg";
        String H = "ĤĦH";
        String h = "ĥħh";
        String I = "ĨĪĬĮİÌÍÎÏI";
        String i = "ĩīĭįıìíîïi";
        String J = "ĲĴJ";
        String j = "ĳĵj";
        String N = "ÑNŃŅŇŊ";
        String n = "ñnńņňŉŋ";
        String K = "ǨĶK";
        String k = "ǩķk";
        String L = "ĹĻĽĿŁL";
        String l = "ĺļľŀłl";
        String O = "ŌŎŐÒÓÔÕÖØO";
        String o = "ōŏőòóôõöøo";
        String R = "ŔŖŘR";
        String r = "ŕŗřr";
        String S = "ßŚŜŞŠS";
        String s = "śŝşšs";
        String T = "ŢŤŦT";
        String t = "ţťŧt";
        String U = "ŨŪŬŮŰŲÙÚÛÜU";
        String u = "ũūŭůűųùúûüu";
        String Y = "ŶÝŸY";
        String y = "ŷýÿy";
        String W = "ŴW";
        String w = "ŵw";
        String Z = "ŹŻŽZŹŻŽ";
        String z = "źżžz";
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
                   // System.out.println("Zamena");
                    buf = (String) pair.getKey();
                    sym = buf.charAt(0);
                    flag = true;
                }
            }

            NormalText = NormalText + sym;


        }



    }


}
