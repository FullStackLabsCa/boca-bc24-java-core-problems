package generic_problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CountOddIntegerInCollection {
    public static <T extends Integer> void occureneceOfOddNumbers(Collection<T> integerColection) {
        int count = 0;

        for (T integer : integerColection) {
            if (Integer.valueOf(integer) % 2 != 0) {
                count++;
            }
        }

        System.out.println("No of Odd Integers = " + count);
    }

    public static void main(String[] args) {
        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(12);
        integerArrayList.add(3);
        integerArrayList.add(8);
        integerArrayList.add(5);
        integerArrayList.add(7);
        integerArrayList.add(2);
        CountOddIntegerInCollection.occureneceOfOddNumbers(integerArrayList);
    }
}
