package problems.genrics;

import java.util.List;


public class ListToArray {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);


        Integer[] newArray = listToArray(list);
        System.out.println("newArray = " + newArray);
    }


    public static <T> T[] listToArray(List<T> list) {
        T[] array =(T[]) new Object [list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);

        }
        return array;
    }
}
