package generics;

import java.util.*;

public class CountOddIntegers {
    public static <T extends Number> int getOddIntegerCount(Collection<T> collection) {
        int count = 0;
        if (collection.isEmpty()) {
            return count;
        }
        for (T collectionItem: collection) {
            if (Integer.valueOf((Integer) collectionItem) % 2 != 0)
            {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(10);
        list.add(21);
        list.add(15);
        list.add(17);

        System.out.println("Count of odd in list collection is::" + getOddIntegerCount(list));

        Set<Integer> hashSet = new HashSet<>();
        hashSet.add(31);
        hashSet.add(33);
        hashSet.add(35);
        hashSet.add(37);
        System.out.println("Count of odd in hashset collection is::" + getOddIntegerCount(hashSet));

        Set<Integer> treeSet = new TreeSet<>();
        System.out.println("Count of odd in tree set collection is::" + getOddIntegerCount(treeSet));
    }
}
