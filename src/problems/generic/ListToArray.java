package problems.generic;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S106")
public class ListToArray {
    public static <T> T[] listToArray(List<T> list, T[] array) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Integer[] intArrayResult = listToArray(integers,new Integer[integers.size()]);

        System.out.println("intArrayResult = " + Arrays.toString(intArrayResult));

        List<String> string = Arrays.asList("Harry", "Harman", "Naveen");
        System.out.println("Array of string: " + Arrays.toString(listToArray(string , new String[string.size()])));
    }
}
