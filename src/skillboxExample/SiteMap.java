package skillboxExample;

import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMap {
    private final String url; //адрес корневой страницы
    //использование CopyOnWriteArrayList позволяет менять этот ArrayList
    //параллельно несколькими потоками без блокировки
    private final CopyOnWriteArrayList<SiteMap> siteMapChildrens;

    public SiteMap(String url) {
        siteMapChildrens = new CopyOnWriteArrayList<>();
        this.url = url;
    }

    //принимать объект типа SiteMap и добавлять его в CopyOnWriteArrayList
    public void addChildren(SiteMap children) {
        siteMapChildrens.add(children);
    }
    //получаем сисок дочерних страниц
    public CopyOnWriteArrayList<SiteMap> getSiteMapChildrens(){
        return siteMapChildrens;
    }

    //получение строкового предстваления url страницы
    public String getUrl(){
        return url;
    }
}
