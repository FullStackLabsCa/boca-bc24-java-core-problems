package generic.collections.practice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S106")
public class Problem1ConvertListToArray {
    public static <T> T[] listToArray(List<T> list) {
        T[] array = (T[]) java.lang.reflect.Array.newInstance(list.get(0).getClass(), list.size());

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        Integer[] intArrayResult = listToArray(integers);

        System.out.println("intArrayResult = " + Arrays.toString(intArrayResult));

        List<String> string = Arrays.asList("Harry", "Harman", "Naveen");
        System.out.println("Array of string: " + Arrays.toString(listToArray(string)));
    }
}
