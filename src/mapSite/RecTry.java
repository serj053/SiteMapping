package mapSite;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RecTry {
    public static void main(String[] args) throws IOException {
        String url = "https://www.playback.ru";
        RecTry.scan(url);

    }
    static Set<String> box = new HashSet<>();

    static void scan(String url) throws IOException {
        Set<String> list = getLinks(url);
        box.addAll(list);
        for(String str: list){
            System.out.println(str);
            scan(str);
        }
    }

    static Set<String> getLinks(String url) throws IOException {
        Set<String> links = new HashSet<>();
        Connection connection = Jsoup.connect(url)
                .ignoreHttpErrors(true);
        Document doc = connection.get();
        Elements elements = doc.select("a");
        elements.forEach(e -> {
            links.add(e.absUrl("href"));
        });
        //удаляем ссылку по которой вошли в метод, что по ней больше не было вызовов
    //    links.removeAll(url);
        return links;
    }
    
}
