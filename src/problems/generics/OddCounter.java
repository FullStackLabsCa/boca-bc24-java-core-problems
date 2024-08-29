package problems.generics;

import java.util.*;

public class OddCounter {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Set<Integer> set = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Count of odds in list: " + countOddNumbers(list));
        System.out.println("Count of odds in set: " + countOddNumbers(set));
    }


    public static <T extends Collection<Integer>> Integer countOddNumbers(T collection) {

        return Math.toIntExact(collection.stream().filter(a -> a != null && a % 2 != 0).count());
    }
}
