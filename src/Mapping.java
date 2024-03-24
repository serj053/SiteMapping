import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Pattern;

public class Mapping extends RecursiveTask<List<String>> {
    String url;
    private final String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    private static int counter = 0;

    Mapping(String url) {
        this.url = url;
    }

    protected List<String> compute() {
        counter++;
        List<String> setLinks = new ArrayList<>();
        List<Mapping> tasks = new ArrayList<>();
        Set<String> tempList;
        setLinks.add(url);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            tempList = getLinksFromPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (tempList.isEmpty()) {
            return setLinks;
        }
        for (String link : tempList) {
            if (!isValid(link)) {//проверка соответствия щаблона и url
                continue;
            }
            Mapping finderLinks = new Mapping(link);//для каждой ссылки поток создается
            finderLinks.fork();
            tasks.add(finderLinks);
        }
        addTaskResult(setLinks, tasks);
        return setLinks;
    }

    public void addTaskResult(List<String> set, List<Mapping> tasks) {
        for (Mapping link : tasks) {
            set.add(link.join().toString());
        }
    }
//получаем список ссылок
    public Set<String> getLinksFromPage() throws IOException {
        Document doc = Jsoup.connect(url).ignoreHttpErrors(true).get();
        Elements elements = doc.select("a[abs:href]");
        Set<String> links = new LinkedHashSet<>();
        elements.forEach(elem -> links.add(elem.attr("abs:href")));
        return links;
    }
//regex-шаблон, test-проверяемый url-адрес, url-главная часть адреса
    public boolean isValid(String test) {
        boolean bl = Pattern.matches(regex, test) && test.contains(url)
                && !test.equals(url) && !url.contains("pdf");
        return bl;
    }
}
