package problems.array_problems;

import java.util.Arrays;

public class IsSortedArray {
    public static void isSorted(int[] inputArray){
        int minNumber = 0;
        int[] sortedArray = Arrays.copyOf(inputArray, inputArray.length);

        Arrays.sort(sortedArray);


        Boolean istrue = Arrays.equals(inputArray, sortedArray);
        if(istrue){
            System.out.println("Array is sorted");
        }
        else{
            System.out.println("Array is not sorted");
        }
    }

    public static void main(String[] args) {
        int[] inputArray = new int[]{1,5,3,1,5};
        isSorted(inputArray);
    }
}
