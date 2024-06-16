package SkillboxExample2;

import java.util.ArrayList;
import java.util.List;

public class SiteMap {
    String url;
    List<SiteMap> siteMapChildrens = new ArrayList<>();

    public SiteMap(String url) {
        this.url = url;
    }

    public void addSiteMap(SiteMap siteMap) {
        siteMapChildrens.add(siteMap);
    }

    public List<SiteMap> getMaps() {
        return siteMapChildrens;
    }

}
