package problems.generics;

import java.util.ArrayList;
import java.util.List;

public class CountOccurence {
    public static <T> int countOccurences(T[] array, T element){
        int count = 0;
    for(T counter : array){
        count++;
    }
        return count;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(1);
        list.add(2);
        list.add(2);
        list.add(1);
        list.add(1);
        list.add(3);
        System.out.println(list);


//        System.out.println("Count: " + countOccurences());
    }

}
