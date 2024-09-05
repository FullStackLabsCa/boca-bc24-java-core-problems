package problems.arrayProblem;

import java.util.Arrays;

public class SmallestLargestElementInAraay {

    public static void main(String[] args) {
//        int[] arr1 = {1, 2,3,5};
        int[] arr1 = {10, -3, 7, 2};
        int[] mergedArray  = smallLargeArray(arr1);
        System.out.println("sorted Array");
        for (int element : mergedArray){
            System.out.print(element+ " ");
        }
        System.out.println( "\n"+ Arrays.toString(mergedArray));
    }

    private static int[] smallLargeArray(int[] arr1) {
        int[] smallLargeArray = {Integer.MAX_VALUE,Integer.MIN_VALUE};

        for(int element : arr1){
            // Minimum Value for the element
            if(element<smallLargeArray[0]){
                smallLargeArray[0] = element;
            }
            // Maximum Value for the element
            if(element>smallLargeArray[1]){
                smallLargeArray[1] = element;
            }
        }

        return smallLargeArray;

    }
}
