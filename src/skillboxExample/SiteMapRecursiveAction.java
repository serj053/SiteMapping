package skillboxExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class SiteMapRecursiveAction extends RecursiveAction {
    private SiteMap siteMap;//для текущей карты страницы
    //список посещенных сылок в текущей задаче
    private static CopyOnWriteArrayList<String> linksPool = new CopyOnWriteArrayList();
    static String constantPart;

    public SiteMapRecursiveAction(SiteMap siteMap) {
        this.siteMap = siteMap;
    }

    //рекурсивный обход сайта и его дочерних страниц
    @Override
    protected void compute() {
        if (!linksPool.contains(siteMap.getUrl()))
            linksPool.add(siteMap.getUrl());//добавляем первым адрес страницы (базовый url)
        //кладем все ссылки по адресу страницы в контейнер
        ConcurrentSkipListSet<String> links = ParseHtml.getLinks(siteMap.getUrl(), constantPart);
        //если в контейнере для посещенных ссылок нет текущей ссылки то добавляем ее туда
        //и кладем все адреса соответствующие ссылкам в контейнер класса SiteMap
        for (String link : links) {
            if (!linksPool.contains(link)) {
                linksPool.add(link);//добавляем в список посещенных ссылок
                //по текущей ссылке создаем новый объект - пустую карту SiteMap
                //и добавляем туда базовую (начальную) ссылку, а сам объект добавляем
                // в контейнер
                siteMap.addChildren(new SiteMap(link));
            }
        }
        //создаем контейнер для задач
        List<SiteMapRecursiveAction> taskList = new ArrayList<>();
        //для каждой ссылки внутри карты создаем свою задачу
        for (SiteMap child : siteMap.getSiteMapChildrens()) {
            SiteMapRecursiveAction task = new SiteMapRecursiveAction(child);
            //запускаем(добавляем) новую задачу асинхронно относительно текущего потока
            task.fork();
            //добавляем текущую задачу в список подготовленных (запущенных?) задач
            taskList.add(task);
        }
        //метод join() перебериает задачи и заставляет текущую задачу ждать пока
        // кождая дочерняя звдвча  не закончит выполнение
        for (SiteMapRecursiveAction task : taskList) {
            task.join();
        }

    }
}













