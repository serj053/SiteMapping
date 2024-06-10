package training;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TryJsoup {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://skillbox.ru/").get();
        Elements elements = doc.select("a");
        List<String> list1 = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for(Element element: elements){
            list1.add(element.attr("abs:href"));
        }
        System.out.println("time1 " + (System.currentTimeMillis() - start1) + " size " + list1.size());
//        for(String str: list1){
//            System.out.println(str);
//        }
        System.out.println("*****************************");
        List<String> list2 = new ArrayList<>();
        long start2 = System.currentTimeMillis();
        for(Element element: elements){
            list2.add(element.attr("abs:href"));
        }
        System.out.println("time2 " + (System.currentTimeMillis() - start2) + " size " + list2.size());

        System.out.println("*****************************");
        Set<String> list3 = new HashSet<>();
        long start3 = System.currentTimeMillis();
        for(Element element: elements){
            list3.add(element.attr("abs:href"));
        }
        System.out.println("time3 " + (System.currentTimeMillis() - start3) + " size " + list3.size());

        System.out.println("*****************************");
        Set<String> list4 = new HashSet<>();
        long start4 = System.currentTimeMillis();
        for(Element element: elements){
            list4.add(element.attr("abs:href"));
        }
        System.out.println("time4 " + (System.currentTimeMillis() - start4) + " size " + list4.size());

        System.out.println("*****************************");
        Set<String> list5 = new HashSet<>();
        long start5 = System.currentTimeMillis();
        for(Element element: elements){
            list5.add(element.attr("abs:href"));
        }
        System.out.println("time3 " + (System.currentTimeMillis() - start5) + " size " + list5.size());


    }

}
