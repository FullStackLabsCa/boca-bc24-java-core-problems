package problems.generics.problem5_counting_odd_integer_in_collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static problems.generics.problem5_counting_odd_integer_in_collection.OddCounter.countOddNumbers;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 3, null, 4, 7, null, 8);
//        Set<Integer> set = new HashSet<>(list);

        System.out.println("countOddNumbers(list) = " + countOddNumbers(list));
//        System.out.println("countOddNumbers(set) = " + countOddNumbers(set));

    }
}
