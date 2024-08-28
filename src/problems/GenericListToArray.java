package problems;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenericListToArray {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("A", "b");
        List<Integer> intList = Arrays.asList(1, 2);

        //to test Integer array
        Integer[] integerArray = listToArray(intList);
        System.out.println(Arrays.toString(integerArray));

        //to test String array
        String[] stringArray = listToArray(stringList);
        System.out.println(Arrays.toString(stringArray));

        //to test empty array
        Integer[] emptyArray = listToArray(Collections.<Integer>emptyList());
        System.out.println(Arrays.toString(emptyArray));
    }

    public static <T> T[] listToArray(List<T> list) {
        T[] array = null;

        if (!list.isEmpty()) {
            T[] r = (T[]) Array.newInstance(list.get(0).getClass(), list.size());

            array = list.toArray(r);
            return array;
        }
        else {
            return array;
        }
    }

}
