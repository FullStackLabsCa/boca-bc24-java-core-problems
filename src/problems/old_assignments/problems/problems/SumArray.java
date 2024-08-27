package problems.old_assignments.problems.problems;

import java.util.Arrays;

public class SumArray {
    //14 Sum all the numbers in array

    public static void main(String[] args) {
        int[] arrays =  {1,2,3,5,3,1,3, -1};
        int streamSum = Arrays.stream(arrays).sum();
        System.out.println("====== Sum of the elements in the Array using stream ========");
        System.out.println("Sum of the all Numbers in the Array:  " + streamSum);
        for (int i = 0; i < arrays.length; i++) {

        }

    }
}
