package genericsProblems;

import java.util.Arrays;
import java.util.List;


public class ListToArray {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);

        Object[] array = listToArray(intList, new Integer[intList.size()]);

        for (Object elem : array) {
            System.out.println(elem);
        }

    }

    public static <T> T[] listToArray(List<T> list, T[] arr) {
        //check for null or emptyList
        if (list == null || list.isEmpty()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);

        }
        return arr;
    }
}




