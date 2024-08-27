package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericListToArray {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Abhay");
        names.add("Nimavat");


        System.out.println(Arrays.toString(listToArray(names)));

    }
    public static <T> T[] listToArray(List<T> list){
        T[] names = (T[]) new String[list.size()];

        return names;
    }
}
