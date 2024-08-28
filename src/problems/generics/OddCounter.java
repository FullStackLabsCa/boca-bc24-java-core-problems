package generics;

import java.util.*;

public class OddCounter {
    public static <T extends Number> int countOddNumbers(Collection<T> collection) {
        int count = 0;
        if (collection.isEmpty()) {
            return count;
        }
        for (T collectionItem: collection) {
            if (collectionItem == null) {
                continue;
            } else if (Integer.valueOf((Integer) collectionItem) % 2 != 0)
            {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(10, 15, 20, 25, 30, 35);
        System.out.println(countOddNumbers(intList));

        Set<Integer> numbers = new HashSet<>(Arrays.asList(10, 15, 20, 25, 30, 35));
        System.out.println(countOddNumbers(numbers));

        List<Integer> emptyList = Collections.emptyList();
        System.out.println(countOddNumbers(emptyList));

        List<Integer> numbersWithNoOdd = Arrays.asList(2, 4, 6, 8, 10);
        System.out.println(countOddNumbers(numbersWithNoOdd));

        List<Integer> numbersWithNull = Arrays.asList(1, 3, null, 4, 7, null, 8);
        System.out.println(countOddNumbers(numbersWithNull));
    }
}
