package mapSite;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Site {
    public static void main(String[] args) throws IOException {
        String url = "https://www.playback.ru";
        //String url = "https://lenta.ru/";
        Site gl = new Site(url);
        AtomicInteger n = new AtomicInteger();
        gl.getLinks().forEach(e -> {
            n.getAndIncrement();
            System.out.println(e);
        });
        System.out.println(n);
    }

    static String inputUrl;

    public Site(String url) {
        inputUrl = url;
    }

    public  Set<String> getLinks() throws IOException {
        Set<String> links = new HashSet<>();
        Connection connection = Jsoup.connect(inputUrl).ignoreHttpErrors(true);
        Document doc = connection.get();
        Elements elements = doc.select("a");
        elements.forEach(e -> {
            links.add(e.absUrl("href"));
        });
        return links;
    }
}
