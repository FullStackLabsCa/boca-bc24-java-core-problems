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

        Set<Integer> set = new HashSet<>();
        set.add(31);
        set.add(33);
        set.add(35);
        set.add(37);
        System.out.println("Count of odd in set collection is::" + getOddIntegerCount(set));
    }
}
