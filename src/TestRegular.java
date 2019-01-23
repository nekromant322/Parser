import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegular
{


    public static void main(String[] args)
    {
        String onep = " <div class=\"articleIntro mainP\"><p>\n" +
                "În anumite intervale orare, mai ales în timpul dimineţii, atunci când sunt înregistrate vârfuri de consum, România importa 20% din necesarul său de energie, în contextul în care problemele pe partea de producţie persistă.</p>\n" +
                "<p>\n" +
                "Ucraina era ieri cel mai important furnizor extern, după cum arătau datele trans\u00ADmise în timp real pe site-ul Trans\u00ADelec\u00ADtrica, urmată de Ungaria, Serbia şi Bul\u00ADgaria. În mod tradiţional, acestea erau destinaţii de export pentru energia ro\u00AD\u00ADmânească. După primele nouă luni ale lui 2018, pe datele Transelec\u00ADtrica, era vizibilă o creştere a impor\u00ADtu\u00ADrilor din Bul\u00ADgaria şi Serbia, conco\u00ADmitent cu scă\u00ADderea exporturilor pe aceste des\u00ADtinaţii.</p>\n" +
                "<p>\n" +
                "Pe de altă parte, cantităţile aduse din Ungaria şi Ucraina erau în scădere, tendinţă însoţită de o creştere a exporturilor pe aceste destinaţii. Ca structură de producţie, cea mai importantă sursă în alimentare cu energie a pieţei locale erau centralele pe gaze, urmate de cele pe cărbuni, hidrocentrale şi energia nucleară.</p>\n" +
                "</div>\n" +
                "\n" +
                "                    <div id=\"adoceanthinkdigitalrokhjqpvngmi\"></div>\n" +
                "<script type=\"text/javascript\">\n" +
                "/* (c)AdOcean 2003-2015, thinkdigital_ro.zf.ro.Energie.640x160_adtext */\n" +
                "ado.slave('adoceanthinkdigitalrokhjqpvngmi', ";
        Pattern p = Pattern.compile("(?i)[\\w\\d\\s\\-\\ă\\Ă\\Î\\î\\ş\\Ş\\ţ\\Ţ\\ș\\Ș\\ț\\Ț\\Â\\â\\'\\,]* "+"pe" + " .*?(?=\\.|\\<|\\!|\\?|\\n|\\t|$|\")");

        String to_check = onep.toString();




        Matcher m = p.matcher(to_check);
        while(m.find())
        {
            System.out.println(m.group());
        }
    }
}
