import java.util.ArrayList;
import java.util.List;

public class FormatMap {
    public static List<String> toDo(List<String> list) {
        StringBuilder builder = new StringBuilder();
        list.forEach(builder::append);
        String line = builder.toString();
        String[] arr3 = line.split("[\\]\\[\\s\\,]");
        List<String> listOut = new ArrayList<>();
        for (String str2 : arr3) {
            String string = str2.replace("[", "").replace("]", "");
            String[] ss = string.split("");
            String len = "";
            int st = 0;
            for (String s : ss) {
                if (s.equals("/")) {
                    st++;
                }
            }
            for (int i = 2; i < st; i++) {
                len = len + "   ";
            }
            if (st == 0) continue;
            listOut.add(len + string);
        }
        return listOut;
    }
}
