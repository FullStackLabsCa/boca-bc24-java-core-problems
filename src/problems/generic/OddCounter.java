package problems.generic;

import java.util.*;

@SuppressWarnings("java:S106")
public class OddCounter {
    public static Integer countOddNumbers(Collection<Integer> collections) {
        if (collections.isEmpty()) return 0;
        int count = 0;

        for (Integer collection : collections) {
            if ( collection != null && collection % 2 != 0)
                count++;
        }

        return count;
    }

    public static void main(String[] args) {
        int oddNumbersCountForArrayList = 0;
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        oddNumbersCountForArrayList = countOddNumbers(arrayList);
        System.out.println("oddNumbersCountForArrayList = " + oddNumbersCountForArrayList);

        int oddNumbersCountForHashSet = 0;
        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(7);

        oddNumbersCountForHashSet = countOddNumbers(hashSet);
        System.out.println("oddNumbersCountForHashSet = " + oddNumbersCountForHashSet);
    }
}
