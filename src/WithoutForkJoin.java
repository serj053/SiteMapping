import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WithoutForkJoin {
    public static void main(String[] args) throws IOException {

        String url = "https://skillbox.ru";
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@/%=~_|]";
        List<String> list = new ArrayList<>();
        int counter = 0;
        mapURL(list, url, regex, counter );
        for(String str: list){
            System.out.println(str);
        }

    }

    public static void mapURL(List<String> list, String URL, String regex, int counter) throws IOException {
        counter++;
        if (Pattern.matches(regex, URL)) {
            Document doc = Jsoup.connect(URL).ignoreHttpErrors(true).get();
            Elements elements = doc.select("a[abs:href]");
            for (Element element : elements) {
                String nextURL = element.attr("abs:href");
                if (Pattern.matches(regex, nextURL) && nextURL.contains(URL)
                        && !nextURL.equals(URL)) {
                    String interval = "";
                    for (int i = 0; i < counter; i++) {
                        interval += "  ";
                    }
                    System.out.println(interval + " " + nextURL);
                    // list.add(interval + nextURL);
                    mapURL(list, nextURL, regex, counter);
                }
            }
        }
    }

}
