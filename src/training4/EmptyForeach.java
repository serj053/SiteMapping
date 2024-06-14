package training4;

import java.util.ArrayList;
import java.util.List;

public class EmptyForeach {
    public static void main(String[] args) {
        List<String> str = new ArrayList<>();
        str.forEach(e-> System.out.println(e.getClass().getName()));
    }
}
