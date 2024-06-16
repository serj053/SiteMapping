package SkillboxExample2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        CopyOnWriteArrayList<String> urlPool = new CopyOnWriteArrayList<>();
        String url = "https://skillbox.ru";
        long start = System.currentTimeMillis();
        Mapping task = new Mapping(urlPool, url);
        fjp.invoke(task);
        long finish = System.currentTimeMillis() - start;

        for(String urls: urlPool){
            System.out.println(urls);
        }
        System.out.println("* full time - " + finish/1000 + " секунд");

    }
}
