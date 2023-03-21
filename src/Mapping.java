import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class Mapping extends RecursiveTask<List<String>> {
    private final String url;
    private String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static int counter;

    Mapping(String url) {
        this.url = url;
    }

    protected List<String> compute() {

        System.out.println(counter++);
        List<String> list = new ArrayList<>();
        List<ForkJoinTask<List<String>>> taskList = new ArrayList<>();
        if (Pattern.matches(regex, url)) {
            Document doc;
            try {
                doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Elements elements = doc.select("a[abs:href]");
            if(elements.size() < 100) {
                for (Element elm : elements) {
                    String nextUrl = elm.attr("abs:href");
                    if (Pattern.matches(regex, nextUrl) && nextUrl.contains(url) && !nextUrl.equals(url)) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("in if 1   " + nextUrl);
                        list.add(nextUrl);
                    }
                }
            }else{
                for (Element elm : elements) {
                    String nextUrl = elm.attr("abs:href");
                    if (Pattern.matches(regex, nextUrl) && nextUrl.contains(url) && !nextUrl.equals(url)) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        Mapping map = new Mapping(nextUrl);
                        taskList.add(map.fork());
                    }
                }
            }
        } else {
            System.out.println("URL не соотвтетсвует синтаксису.");
        }
        for(ForkJoinTask<List<String>> tsk: taskList){
            list.add(tsk.join().toString());
        }
        return list;
    }

}
