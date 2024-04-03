package siteparsing;

import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMap {
    private CopyOnWriteArrayList<SiteMap> list;
    private final String url;
    public SiteMap(String url){
        this.url = url;
    }
    public void addUrl(String url){
        list.add(new SiteMap(url));
    }
    public CopyOnWriteArrayList<SiteMap> getSiteMaps(){
        return list;
    }

    public String getUrl(){
        return url;
    }
}
