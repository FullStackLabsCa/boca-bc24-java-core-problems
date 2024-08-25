package problems.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericListToArray {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Spring");
        list.add("Kafka");
        list.add("Node");
        list.add("MySQL");

        System.out.println(Arrays.toString(listToArray(list)));
    }

    private static <T> T[] listToArray(List<T> list) {
        T[] returnArray = (T[]) new Object[list.size()];
        for(int i =0; i<list.size();i++){
        returnArray[i] = list.get(i);
        }
        return returnArray;
    }

}
