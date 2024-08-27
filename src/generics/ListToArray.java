package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListToArray {

    public static void main(String[] args) {
        List<String> listString = new ArrayList<>();
        List<Integer> listInteger = new ArrayList<>();
        listString.add("Ankit");
        listString.add("Joshi");
        listInteger.add(1);
        listInteger.add(101);
        System.out.println(Arrays.toString(listToArray(listString)));
        System.out.println(Arrays.toString(listToArray(listInteger)));
    }

    public static <T> T[] listToArray(List<T> list){
        T[] array = (T[]) new Object[list.size()];
        for (int i=0; i< list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
}
