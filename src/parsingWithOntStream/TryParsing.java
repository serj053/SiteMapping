package parsingWithOntStream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class TryParsing {
    public static void main(String[] args) throws IOException {
        String url = "https://www.skillbox.ru";

        Connection connect = Jsoup.connect(url).ignoreHttpErrors(true)
                .ignoreContentType(true)
                .followRedirects(true);
        Document doc = connect.get();
        Elements elements = doc.select("body").select("a");
        int n = 0;
        for (Element element : elements) {
            String absUrl = element.absUrl("href");
            getUrl(absUrl, n);
        }
        System.out.println("n - " + n);
    }

    public static int getUrl(String url, int n) throws IOException {

        String url1 = url;
        Elements elements = Jsoup.connect(url)
                .ignoreHttpErrors(true)
                .get().select("a");
        if (elements.isEmpty()) {
            return 0;
        }
        for (Element element : elements) {
            n++;
            String absUrl = element.absUrl("href");
            getUrl(absUrl, n);
            if(n>1000)
                return n;
        }
        return n;
    }
}
