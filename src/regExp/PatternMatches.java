package regExp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatches {
    public static void main(String[] args) {
//        Pattern pattern0 = Pattern.compile("[a-z]+");
//        Matcher matcher0 = pattern0.matcher("a b c d 1 2 3 4");
//        System.out.println(matcher0.find());//находим любое совпадение с шаблоном

        Pattern pattern1 = Pattern.compile(".[0-9]");
        Matcher matcher1 = pattern1.matcher("a b c d e f g h");
        System.out.println(matcher1.find());

        Matcher matcher2 = pattern1.matcher("f g h a b c");
        System.out.println(matcher2.find());

        Matcher matcher3 = pattern1.matcher("76543");
        System.out.println(matcher3.find());
    }
}
