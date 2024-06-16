package skillboxExample;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox.ru/";
        String pathToSiteMapFile = "data/siteMap.txt";
//        String url = "https://www.playback.ru";
//        String pathToSiteMapFile = "data/playbackFromSkillbox.txt";
        long start = System.currentTimeMillis();
        //создаем пустой контейнер
        SiteMap siteMap = new SiteMap(url);
        //создаем задачу - создавать контейнеры SiteMap, передавать туда ссылки
        //и для этих ссылок создавать новые задачи и запускать их отдельными потоками
        SiteMapRecursiveAction task = new SiteMapRecursiveAction(siteMap);
        new ForkJoinPool().invoke(task);

        //после выполнения задачи записать карту сайта в файл
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(pathToSiteMapFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String siteMapFile = createSiteMapString(siteMap,0);

        try {
            stream.write(siteMapFile.getBytes(StandardCharsets.UTF_8));
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long finish = System.currentTimeMillis() - start;
        System.out.println("* full time - " + finish/1000+ " sec");

    }

    public static String createSiteMapString(SiteMap siteMap, int indent){
        String tab = String.join("", Collections.nCopies(indent,"\t" ));
        StringBuilder result = new StringBuilder(tab + siteMap.getUrl());
        siteMap.getSiteMapChildrens().forEach(child->result.append("\n")
                .append(createSiteMapString(child, indent +1)));
        return result.toString();
    }
}
