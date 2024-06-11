package siteParsing;

import java.util.concurrent.CopyOnWriteArrayList;

public class SiteMap {
    private final CopyOnWriteArrayList<SiteMap> list = new CopyOnWriteArrayList<>();
    private final String url;
    public SiteMap(String url){
        this.url = url;
    }
    public void addSiteMap(String url){
        list.add(new SiteMap(url));
    }
    public CopyOnWriteArrayList<SiteMap> getSiteMaps(){
        return list;
    }

    public String getUrl(){
        return url;
    }
}
