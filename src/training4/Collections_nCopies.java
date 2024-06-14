package training4;

import java.util.Collections;
import java.util.List;

public class Collections_nCopies {
    public static void main(String[] args) {
        String str = String.join("", "One","Two","Three");
        System.out.println(str);
        List<String> copies = Collections.nCopies(4,"Try");
        System.out.println(copies);
        String str2 = String.join(":", Collections.nCopies(5,"One"));
        System.out.println(str2);
    }
}
