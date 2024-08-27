package problems.generic;
/*
Problem 1: Generic Method to Convert List to Array
        Objective:
        Write a generic method listToArray that converts a List<T> to an array of type T[].

public static <T> T[] listToArray(List<T> list)
 */

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ListToArray {

    public static <T> T[] listToArray(List<T> list) {

        //Tried this two way of initializing the array of generic type, didn't work so use the next method from gpt, that prevent the classCastException too.
//        T[] array = new T[list.size()];
//        T[] array = (T[]) new Object[list.size()];
        T[] array = (T[]) Array.newInstance(list.get(0).getClass(), list.size());

//       T[] array = (T[]) new Object[list.size()] can be done in this way too.

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(2, 5, 6, 8);
        List<String> stringList = Arrays.asList("generic", "array", "collection", "string");
        Integer[] integers = listToArray(integerList);
        String[] strings = listToArray(stringList);
        System.out.println("Array is = " + Arrays.toString(integers));
        System.out.println("Array is = " + Arrays.toString(strings));

    }
}
