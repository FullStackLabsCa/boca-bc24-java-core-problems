//package genericsproblems;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class ListToArray {
//    public static void main(String[] args) {
//
//        List<String> myStrings = new ArrayList<>();
//        myStrings.add("Gagan");
//        myStrings.add("Deep");
//        myStrings.add("Kaur");
//        myStrings.add("Batth");
//        System.out.println("myStrings = " + Arrays.toString(listToArray(myStrings)));
//
//        List<Integer> myInteger = new ArrayList<>();
//        myInteger.add(1);
//        myInteger.add(2);
//        myInteger.add(3);
//        myInteger.add(4);
//        System.out.println("My Interger list : "+Arrays.toString(listToArray(myInteger)));
//
//    }
//
//    public static <T> T[] listToArray(List<Integer> list) {
//        T[] array = (T[]) new Object[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            array[i] = list.get(i);
//        }
//        return array;
//    }
//
//}
