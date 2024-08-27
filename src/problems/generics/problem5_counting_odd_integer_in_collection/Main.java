package problems.generics.problem5_counting_odd_integer_in_collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static problems.generics.problem5_counting_odd_integer_in_collection.CountOddIntegerCollection.countOddNumbers;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 83,5,2,23,1,1);
        Set<Integer> set = new HashSet<>(list);

        System.out.println("countOddNumbers(list) = " + countOddNumbers(list));
        System.out.println("countOddNumbers(set) = " + countOddNumbers(set));

    }
}
