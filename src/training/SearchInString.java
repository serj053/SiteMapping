package training;

public class SearchInString {
    public static void main(String[] args) {
       // String url = "https://skillbox.ru";
        String url =  "https://www.playback.ru";
        int start = url.indexOf("//");
        System.out.println(start);
        int end = url.indexOf(".ru");

        String sub = url.substring(start+2, end);
        System.out.println(sub);

    }
}
