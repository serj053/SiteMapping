package SkillboxExample2;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

public class ParseHtml {
    public CopyOnWriteArrayList<String> getLinks(String url) {
        //String reg = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        //String reg = "http[s]?://[^#,\\s]*\\.?skillbox\\.ru[^#,\\s]*";15872
        String reg = "http[s]?://skillbox\\.ru[^#,\\s]*";
        //String reg =  "(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])";
        //String reg = "(?:https?):\\/\\/(\\w+:?\\w*)?(\\S+)(:\\d+)?(\\/|\\/([\\w#!:.?+=&%!\\-\\/]))?";
        //String reg = "(?:https?):\\/\\/(\\w+:?\\w*)?(\\S+)(:\\d+)?(\\/|\\/([\\w#!:.?+=&%!\\-\\/]))?";
        //String reg = "http[s]?://?skillbox\\.ru[^#,\\s]*";
        //String reg = "http[s]?://skillbox\\.ru[^#,\\s]*";
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        Connection connect = Jsoup.connect(url)
                .ignoreHttpErrors(true)//игнорировать ошибки HTTP
                .ignoreContentType(true)
                //.timeout(100)//время ожидания для подключения к серверу
                .followRedirects(false);//избегать зацикливания при обработке перенаправлений
        try {
            Document doc = connect.get();
            Elements elements = doc.select("body").select("a");
            //      Logger.getLogger(ParseHtml.class.getName()).info("elements size" + elements.size());
            int n = 0;
            for (Element tag : elements) {
                String checkingUrl = tag.absUrl("href");
              //  n++;
                if (checkingUrl.matches(reg)
//                        && !checkingUrl.contains("instagram")
//                        && !checkingUrl.contains("linkedin")
                    //&& !checkingUrl.contains("facebook")
                   //     && n < 20
                ) {
                    list.add(checkingUrl);
                    //              Logger.getLogger(ParseHtml.class.getName()).info(checkingUrl);
                }
                continue;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
