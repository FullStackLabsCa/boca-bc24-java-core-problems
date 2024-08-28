package genricproblem;

import java.util.*;

public class OddCounter {
    public static void main(String[] args) {
        OddCounter countOdd = new OddCounter();
        List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        Set<Integer> numbersSet = new HashSet<>(List.of(10, 15, 20, 25, 30, 35));
        List<Integer> numbersList = new ArrayList<>(numbersSet);
        int count = countOddNumbers(numbersList);
        System.out.println("Odd numbers count: " + count);
        System.out.println("odd numbers :" + countOddNumbers(integerList));
    }

    public static <T extends Number> int countOddNumbers(Collection<T> array) {
        int sum = 0;
        for (T value : array) {
            if (value != null && value.intValue() % 2 != 0) {
                sum++;
            }
        }
        return sum;
    }
}
