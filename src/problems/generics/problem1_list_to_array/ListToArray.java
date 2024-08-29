package problems.generics.problem1_list_to_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {
    public static <T> T[] listToArray(List<T> list, T[] array) {
        if (list == null || list.isEmpty() ) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return list.toArray(array);
    }

//        List[] result = new ArrayList[1];
//
//        //iterate through all elements
//        for (int i = 0; i < result.length; i++) {
//            result[i] = list;
//        }
//
//        //Printing the ArrayList
//        for (List value : result) {
//            System.out.print(value);
//        }
//        System.out.println("");
//        System.out.println("list.get(0) = " + list.get(0));
//
//        return null;
//    }


//    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
//        list.add(12);
//        list.add(113);
//        list.add(121);
//        list.add(1221);
//        list.add(102);
//        System.out.println("ListToArray.listToArray(list) = " + Arrays.toString(ListToArray.listToArray(list, new Integer[intList.size()])));
//
//
//        List<String> stringList = new ArrayList<>();
//        stringList.add("Hello");
//        stringList.add("Jade");
//        stringList.add("John");
//        System.out.println("listToArray(stringList) = " + Arrays.toString(listToArray(stringList, new Integer[intList.size()])));

//        Integer[] integersArray = new Integer[list.size()];
//
//        for (int i = 0; i < list.size(); i++) {
//            integersArray[i] = list.get(i);
//        }
//
//        System.out.print("[ ");
//        for (int i = 0; i < integersArray.length; i++) {
//            System.out.print(integersArray[i]);
//            if(i < integersArray.length-1){
//                System.out.print(", ");
//            }
//        }
//        System.out.println(" ]");


}
