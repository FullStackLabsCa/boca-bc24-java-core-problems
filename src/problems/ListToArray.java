package problems;

import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("A", "b");
        List<Integer> intList = Arrays.asList(1, 2);

        //List<String> emptyList = Arrays.asList();
        //System.out.println("emptyList size = " + emptyList.size());
        //to test Integer array
        Integer[] integerArray = listToArray(intList, new Integer[intList.size()]);
        System.out.println(Arrays.toString(integerArray));

        //to test String array
        String[] stringArray = listToArray(stringList, new String[stringList.size()]);
        System.out.println(Arrays.toString(stringArray));


    }

    public static <T> T[] listToArray(List<T> list, T[] array) {
        int counter = 0;
        if (list == null ) {
            return null;
        } else if (list.isEmpty()) {
            return null;
        } else {

            for (T item : list) {
                array[counter] = item;
                counter++;
            }
            return array;
        }
    }

}
