package problems.generics.list_to_array;

import java.util.ArrayList;
import java.util.List;

public class ListToArray {
    public static <T> T[] listToArray(List<T> list, T[] arrayFromList) {
        for(int i = 0; i < list.size(); i++){
            arrayFromList[i] = list.get(i);
        }
        return arrayFromList;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        System.out.println(list);

        List<String> stringList = new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Cherry");
        stringList.add("Orange");
        System.out.println(stringList);

    }
}


