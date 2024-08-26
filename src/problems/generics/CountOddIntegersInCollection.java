package problems.generics;

import java.util.*;
import java.util.stream.Stream;

public class CountOddIntegersInCollection {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        Set<Integer> set = Set.of(1,2,3,4,5,6,7,8,9);

        System.out.println("Count of odds in list: "+countOdd(list));
        System.out.println("Count of odds in set: "+countOdd(set));
    }


    public static <T extends Collection<Integer>> Integer countOdd(T collection){

        return Math.toIntExact(collection.stream().filter(a -> a % 2 != 0).count());
    }
}
