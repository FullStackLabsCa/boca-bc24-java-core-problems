package problems.generics.problem5_counting_odd_integer_in_collection;

import java.util.Collection;

public class OddCounter {

    public static <T extends Integer> int countOddNumbers(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (T element : collection) {
           first: if (element == null && collection.contains(null)){
                break first;
            }
            else if ((element != null ? element.intValue() : 0) % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}
