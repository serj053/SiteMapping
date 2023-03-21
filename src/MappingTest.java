import java.util.List;

public class MappingTest {
    public static void main(String[] args) {
        String url = "https://lenta.ru";
        List<String> list;
        Mapping mapping = new Mapping(url);
        list = mapping.compute();
        list.forEach(System.out::println);
    }
}
