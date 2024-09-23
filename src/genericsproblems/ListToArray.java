package genericsproblems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static void main(String[] args) {
        List<Integer> listOfIntegers = new ArrayList<>();
        listOfIntegers.add(3);
        listOfIntegers.add(4);
        listOfIntegers.add(5);
        Object[] lists = ListToArray.listToArray(listOfIntegers, new Integer[0]);
        for (Object array : lists) {
            System.out.print(array + " ");
        }
        List<String> strList = Arrays.asList("code1", "code2", "code3");
        Object[] strLists = ListToArray.listToArray(strList, new String[0]);
        for (Object stray : strLists) {
            System.out.print(stray + " ");
        }
    }

////        List<String> myStrings = new ArrayList<>();
////        myStrings.add("Gagan");
////        myStrings.add("Deep");
////        myStrings.add("Kaur");
////        myStrings.add("Batth");
////        System.out.println("myStrings = " + Arrays.toString(listToArray(myStrings)));
////
////        List<Integer> myInteger = new ArrayList<>();
////        myInteger.add(1);
////        myInteger.add(2);
////        myInteger.add(3);
////        myInteger.add(4);
////        System.out.println("My Interger list : "+Arrays.toString(listToArray(myInteger)));
//        List<Integer> listOfIntegers = new ArrayList<>();
//        listOfIntegers.add(3);
//        listOfIntegers.add(6);
//        listOfIntegers.add(4);
//
//        Object[] lists = ListToArray.listToArray(listOfIntegers);
//        for(Object array : lists) {
//            System.out.println(array);
//        }
//    }

    public static <T> T[] listToArray(List<T> list, Object[] objects) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T[] listArray = (T[]) new Object[list.size()];
        for (int i = 0; i < listArray.length; i++) {
            objects[i] = list.get(i);
        }
        return (T[]) objects;
    }
}

//    public static <T> T[] listToArray(List<Integer> list) {
//        T[] array = (T[]) new Object[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            array[i] = (T) list.get(i);
//        }
//        return array;
//
//    }
//}
