package skillboxExample;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.lang.Thread.sleep;

//парсинг кода страницы для получения ссылок на страницы
// внутри того же домена
public class ParseHtml {
    public static void main(String[] args) {
        ConcurrentSkipListSet list = ParseHtml.getLinks("https://skillbox.ru/");
        list.forEach(System.out::println);
        System.out.println(list.size());
    }
    //Позволяет безопасно выполнять операции вставки, удаления
    // и доступа к множеству одновременно несколькими потоками.
    private static ConcurrentSkipListSet<String> links;

    //реализуем метод который проверяет является ли строка URL
    //адресом внутри домена сайта
    private static boolean isLink(String link) {
        String regex = "http[s]?://[^#,\\s]*\\.?skillbox\\.ru[^#,\\s]*";
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

    //ConcurrentSkipListSet -это класс из библиотеки Java, представляющий собой
    // потокобезопасную реализацию структуры данных "список уникальных элементов
    // отсортированных по возрастанию"
    //собираем все ссылки со страницы
    public static ConcurrentSkipListSet<String> getLinks(String url) {
        links = new ConcurrentSkipListSet<>();
        try {
            sleep(150);//выдерживать паузы (с помощью метода sleep() у потока), чтобы сайт
            // не заблокировал доступ приложения
            Connection connection = Jsoup.connect(url)
                    .ignoreHttpErrors(true)//игнорировать ошибки HTTP, которые могут возникнуть
                    // при попытке получить данные по URL-адресу, то есть извлекать информацию из
                    // страницы даже в случае возникновения ошибки HTTP
                     //.timeout(100)//Метод timeout() библиотеки Jsoup время ожидания для подключения
                    // к серверу
                    .followRedirects(false);//избегать зацикливания при обработке перенаправлений
            Document document = connection.get();
            Elements elements = document.select("body").select("a");
            for (Element element : elements) {
                String link = element.absUrl("href");
                if (isLink(link) && !isFile(link)) {
                    links.add(link);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e + " " + url);
        } catch (SocketTimeoutException e) {
            System.out.println(e + " " + e);
        } catch (IOException e) {
            System.out.println(e + " " + url);
        }

        return links;
    }
}
