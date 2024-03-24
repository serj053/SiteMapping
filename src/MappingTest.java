import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class MappingTest {
    //https://lenta.ru/
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        int cores = Runtime.getRuntime().availableProcessors();
        ForkJoinPool pool = new ForkJoinPool(cores);
         String url = "https://www.playback.ru";
        //String url = "https://lenta.ru/";
        Mapping linksFinder = new Mapping(url);
        pool.execute(linksFinder);
        List<String> links = linksFinder.join();
        Files.write(Path.of("data/map.html"), FormatMap.toDo(links));
        int n = 0;
        for (String str : FormatMap.toDo(links)) {
            n++;
            System.out.println(str);
        }
        System.out.println("Duration " + (start - System.currentTimeMillis()) / 1000);
        System.out.println("N - " + n);
    }
}
