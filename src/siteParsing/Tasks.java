package siteParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Tasks extends RecursiveAction {
    public static void main(String[] args) {
        String url = "https://skillbox.ru/";
        ForkJoinPool fp = new ForkJoinPool();
        Tasks tasks = new Tasks(new SiteMap(url));
        fp.invoke(tasks);
    }

    static CopyOnWriteArrayList<String> linksPool = new CopyOnWriteArrayList();
    private SiteMap siteMap;

    public Tasks(SiteMap siteMap) {
        this.siteMap = siteMap;
    }

    @Override
    protected void compute() {
        if (!linksPool.contains(siteMap.getUrl()))
            linksPool.add(siteMap.getUrl());
        ConcurrentSkipListSet<String> links = SimpleParsing.getLinks(siteMap.getUrl());
        for (String link : links) {
            //создаем новые объекты SiteMap с узловым url и заполняем этими объктами
            //текущий бъект SiteMap
            if (!linksPool.contains(link)) {
                linksPool.add(link);
                siteMap.addSiteMap(link);
            }
        }
        List<Tasks> tasks = new ArrayList<>();
        //проходим по всем пустым SiteMap(которые содержат только входные ссылки) и
        // передаем их классу MultiThread где каждый SiteMap в отдельной задаче
        // заполняется ссылками
        for (SiteMap siteMap2 : siteMap.getSiteMaps()) {
            Tasks task = new Tasks(siteMap2);
            task.fork();//запускаем задачи асинхронно
            tasks.add(task);//запущенную задачу добавляем в контейнер
        }
        //метод join() заставляет текущую задачу ждать пока каждая дочерняя задачи
        //завершит свое выполнение
        for (Tasks task : tasks) {
            task.join();
        }


    }
}
