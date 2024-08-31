package genricproblem;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public  class OddCounter {
    public static <T extends Collection<Integer>> int countOddNumbers(T collection) {
        int count = 0;

        for (Integer number : collection) {
            if (number == null) {
                continue;
            }
            if(number % 2 != 0) {
                count++;
            }
        }
        return count;
    }


public static void main (String[]args){
    List<Integer> listOfIntegers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println("List of Odd integers:");
    System.out.println(OddCounter.countOddNumbers(listOfIntegers));
}
}
