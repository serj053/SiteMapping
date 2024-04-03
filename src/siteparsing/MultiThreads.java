package siteparsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class MultiThreads extends RecursiveAction {
    private CopyOnWriteArrayList<String> linksPool;
    private SiteMap siteMap;
    public MultiThreads(SiteMap siteMap){
        this.siteMap = siteMap;
    }
    @Override
    protected void compute() {
        linksPool = new CopyOnWriteArrayList();
        ConcurrentSkipListSet<String> links = SimpleParsing.getLinks(siteMap.getUrl());
        for(String link: links){
            //создаем новые объекты SiteMap с узловым url и заполняем этими объктами
            //текущий бъект SiteMap
            siteMap.addUrl(link);
            if(!linksPool.contains(link)){
                linksPool.add(link);
            }
        }
        List<MultiThreads> tasks = new ArrayList<>();
        //проходим по всем пустым SiteMap и передаем их классу MultiThread где
        //каждый SiteMap в отдельной задаче заполняется ссылками
        for(SiteMap siteMap2 : siteMap.getSiteMaps()){

        }

    }
}
