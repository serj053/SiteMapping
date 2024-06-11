package mapSite;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class MapCreating {
    String url;
    Site gl;

    public MapCreating(String url) {
        this.url = url;
        gl = new Site(url);
    }

    public List<String> createMap() {
        StringBuilder builder = new StringBuilder();
        builder.append(url);

        return null;
    }
    public void recScan(String url) throws IOException {
        Set<String> result = gl.getLinks();
        for(int i = 0 ; i < result.size(); i++){

        }
    }
}
