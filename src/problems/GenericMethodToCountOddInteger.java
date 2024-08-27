package problems;

import java.util.*;

public class GenericMethodToCountOddInteger {
    public static void main(String[] args) {

        //To test list collection
        List<Integer> integerList = new ArrayList<>();
        integerList.add(2);
        integerList.add(1);
        integerList.add(4);
        integerList.add(6);
        integerList.add(7);
        System.out.println(GenericMethodToCountOddInteger.oddIntegerCountMethod(integerList));

        //To test Set collection
        Set<Integer> integerSet = new HashSet<>();
        integerSet.add(2);
        integerSet.add(2);
        integerSet.add(9);
        integerSet.add(17);
        integerSet.add(7777);
        System.out.println(GenericMethodToCountOddInteger.oddIntegerCountMethod(integerSet));


    }

    public static <T extends Integer> Integer oddIntegerCountMethod(Collection<T> collection) {
        Integer counter = 0;
        for (T t : collection) {
            if (t.intValue() % 2 == 1) {
                counter++;
            }
        }
        return counter;
    }
}
