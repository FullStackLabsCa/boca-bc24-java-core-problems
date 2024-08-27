package countoccurrences;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] inumber = {5,6,5,7,5,8};
        int count = countOccurrences(inumber,5);
        System.out.println("count = " + count);

        String[] sNumbers = {"one","two","three","one"};
        int count2 = countOccurrences(sNumbers,"one");
        System.out.println("count = " + count2);




    }
    public static <T> int countOccurrences(T[] array, T element) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (element.equals(array[i])) {
                count++;
            }
        }
        return count;
    }
}
