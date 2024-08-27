package genricproblem.CountOdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class CountOddMain {
    public static void main(String[] args) {
      CountOddGeneric countOdd = new CountOddGeneric();
      List<Integer> integerList = new ArrayList<>(Arrays.asList(1, 2, 3,4,5,6));
        System.out.println(countOdd.countOdd(integerList));
    }
}
