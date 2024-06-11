package siteParsing;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ConcurrentSkipListSet;

public class SimpleParsing {

    public static void main(String[] args) {
        String url = "https://skillbox.ru/";
        ConcurrentSkipListSet<String> list = getLinks(url);

        list.forEach(System.out::println);

        System.out.println(list.size());
    }


    //получаем все ссылки по выбранному адресу
    public static ConcurrentSkipListSet<String> getLinks(String url) {
        ConcurrentSkipListSet<String> links = new ConcurrentSkipListSet<>();
        Connection connection = Jsoup.connect(url)
                .ignoreHttpErrors(true)
                .followRedirects(false);
        Document document;
        try {
            document = connection.get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = document.select("a");
        for (Element element : elements) {
            String elemUrl = element.absUrl("href");
            if (isLink(elemUrl) && !isFile(elemUrl)) {
                links.add(elemUrl);
            }
        }
        return links;
    }

    public static boolean isLink(String link) {
         String regex = "http[s]?://[^#,\\s]*\\.?skillbox\\.ru[^#,\\s]*";
         //String regex = "http[s]?://skillbox\\.ru[^#,\\s]*";
       // String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        return link.matches(regex);
    }

    public static boolean isFile(String link) {
        return link.contains("jpg")
                || link.contains(".jpeg")
                || link.contains(".png")
                || link.contains(".gif")
                || link.contains(".webp")
                || link.contains(".pdf")
                || link.contains(".eps")
                || link.contains(".xlsx")
                || link.contains(".doc")
                || link.contains(".pptx")
                || link.contains(".docx")
                || link.contains("?_ga");
    }
}

/*

Connection connect = Jsoup.connect("https://skillbox.ru/")
        .ignoreHttpErrors(true)
        .followRedirects(false);
Document document;
        try {
document = connect.get();
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
Elements elements = document.select("a");
Set<String> checkedLinks = new HashSet<>();
        for(Element element: elements){
String link = element.absUrl("href");
            if(isLink(link)&&
        !isFile(link)) {
        System.out.println(element.absUrl("href"));
        checkedLinks.add(element.absUrl("href"));
        }
        }
        System.out.println(checkedLinks.size());


 */