package training3;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TryMapping {
    public static void main(String[] args) throws IOException {
        //String url = "https://new.astra.agency/";
        String url = "https://skillbox.ru/";
        Connection connection = Jsoup.connect(url)
                .ignoreHttpErrors(true)
                //.timeout(200)
                .followRedirects(false);
        Document doc = connection.get();
        Elements elements = doc.select("a");
        System.out.println(elements.size());
        int n = 0;
        int n2 = 0;
        for (Element el : elements) {
            //  System.out.println(el.absUrl("href"));
            if (el.absUrl("href")
                      .matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")
                    //.matches("(?:https?):\\/\\/(\\w+:?\\w*)?(\\S+)(:\\d+)?(\\/|\\/([\\w#!:.?+=&%!\\-\\/]))?")

            ) {
                System.out.println("** " + el.absUrl("href"));
                n2++;
            }

            n++;
        }
        System.out.println("n - " + n + "  n2 - " + n2);
    }
}
//String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

//String regex = "http[s]?://[^#,\\s]*\\.?new.astra.agency\\.ru[^#,\\s]*";
//String regex = "http[s]?://[^#,\\s]*\\.?skillbox\\.ru[^#,\\s]*";

// https://go.skillbox.ru/