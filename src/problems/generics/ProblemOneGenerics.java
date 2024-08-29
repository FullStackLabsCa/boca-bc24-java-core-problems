package problems.generics;

import java.util.ArrayList;
import java.util.List;

public class ProblemOneGenerics<T> {
    //private List<T>;
    public static <T> T[] listToArray(List<T> list){
        return null;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        System.out.println(list);
    }
}


