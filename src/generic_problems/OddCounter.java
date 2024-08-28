package generic_problems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OddCounter {
    public static <T extends Integer> int countOddNumbers(Collection<T> integerColection) {
        int count = 0;

        for (T integer : integerColection) {
            if (integer==null) {
                continue;
            } else {
                if (Integer.valueOf(integer) % 2 != 0) {
                    count++;
                }
            }
        }
//        System.out.println("No of Odd Integers = " + count);
        return count;
    }

    public static void main(String[] args) {
        List<Integer> integerArrayList = new ArrayList<>();
        integerArrayList.add(12);
        integerArrayList.add(3);
        integerArrayList.add(8);
        integerArrayList.add(5);
        integerArrayList.add(7);
        integerArrayList.add(2);
        System.out.println("No of Odd Integers = "+OddCounter.countOddNumbers(integerArrayList));
    }
}
