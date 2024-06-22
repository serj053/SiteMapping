package SkillboxExample2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class Mapping extends RecursiveAction {
    String url;
    ConcurrentSkipListSet<String> urlPool;//собираем все ссылки с текущей страницы
    public static String constantPart;

    public Mapping(ConcurrentSkipListSet<String> urlPool, String url) {
        this.urlPool = urlPool;
        this.url = url;
    }

    @Override
    protected void compute() {
        ConcurrentSkipListSet<String> tempList;//временный список для переноса ссылок
        CopyOnWriteArrayList<Mapping> taskList = new CopyOnWriteArrayList<>();
        ParseHtml2 ph = new ParseHtml2();
        tempList = ph.getLinks(url, constantPart);//получаем все ссылки со страницы
        if (!urlPool.contains(url))
            urlPool.add(url);
        for (String urlChildren : tempList) {
            if (!urlPool.contains(urlChildren)) {
                urlPool.add(urlChildren);
                Mapping task = new Mapping(urlPool, urlChildren);
                task.fork();
                taskList.add(task);
            }
            continue;
        }
        for (Mapping task : taskList) {
            task.join();//дожидаемся выполнения задачи (кода в объекте)
        }
        //     Logger.getLogger(Mapping.class.getName()).info("task size - "+taskList.size());
    }
}
