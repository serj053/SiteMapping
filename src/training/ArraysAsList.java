package training;

import java.util.Arrays;
import java.util.List;

public class ArraysAsList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("I", "love", "study", "different"
                , "programming", "language", "every", "day");
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        for (String str : array) {
            System.out.println(str);
        }
        List<String> list1 = Arrays.asList("I", "love", "study", "different"
                , "programming", "language", "every", "day");
        String[] array1 = list1.toArray(new String[0]);
        for (String str : array1) {
            System.out.println("1 - " + str);
        }

        String[] array2 = new String[list.size()];
        list.toArray(array2);

    }
}
