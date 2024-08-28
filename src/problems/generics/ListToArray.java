package generics;//

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {

//    public static <T> T[] listToArray(List<T> list) {
//        T [] numericArray = (T[]) new Object[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            numericArray[i] = list.get(i);
//        }
//        return numericArray;
////        return list.toArray(tTypeArray);
//    }

    public static <T> T[] listToArray(List<T> list, T[] tTypeArray) {
        if (list == null || list.isEmpty()) {
            return null;
        } else {
            for (int i = 0; i < list.size(); i++) {
                tTypeArray[i] = list.get(i);
            }
        }
        return tTypeArray;
    }

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(Arrays.toString(listToArray(intList, new Integer[intList.size()])));

        List<String> stringList = Arrays.asList("apple", "banana", "cherry");
        System.out.println(Arrays.toString(listToArray(stringList, new String[stringList.size()])));

        List<String> emptyList = Arrays.asList();
        System.out.println(Arrays.toString(listToArray(emptyList, new String[emptyList.size()])));

        List<String> nullList = null;
        System.out.println(Arrays.toString(listToArray(nullList, new String[0])));

        List<Object> mixedList = Arrays.asList(1, "banana", 3.14);
        System.out.println(Arrays.toString(listToArray(mixedList, new Object[mixedList.size()])));
   }
}
