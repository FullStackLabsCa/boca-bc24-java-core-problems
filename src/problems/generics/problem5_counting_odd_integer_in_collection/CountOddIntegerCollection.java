package problems.generics.problem5_counting_odd_integer_in_collection;

import java.util.Collection;

public class CountOddIntegerCollection {

    public static <T extends Integer> int countOddNumbers(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (T element : collection) {
            if (element % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}
