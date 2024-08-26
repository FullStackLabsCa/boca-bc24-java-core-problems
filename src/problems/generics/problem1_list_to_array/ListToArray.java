package problems.generics.problem1_list_to_array;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {
    public static <T> List[] listToArray(List<T> list) {
        List[] result = new ArrayList[1];
        for (int i = 0; i < result.length; i++) {
            result[i] = list;
        }
        for (List value : result) {
            System.out.print(value);
        }
        System.out.println("");

        return result;
    }


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(113);
        list.add(121);
        list.add(1221);
        list.add(102);
        ListToArray.listToArray(list);


        List<String> stringList = new ArrayList<>();
        stringList.add("Hello");
        stringList.add("Jade");
        stringList.add("John");
        listToArray(stringList);

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
}
