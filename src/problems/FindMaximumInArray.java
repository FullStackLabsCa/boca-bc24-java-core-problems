package problems;

import java.util.Scanner;

public class FindMaximumInArray {

    public static void main(String[] args) {

//
//        System.out.println("Please enter numbers in");
//        Scanner scanner = new Scanner(System.in);

        int[] numbers = {1,2,3,4};

        int max = findMax(numbers);
        System.out.println("The maximum value in the array is: " + max);

    }

    public static int findMax(int[] numbers){
        if (numbers == null || numbers.length == 0){
            return Integer.MIN_VALUE;
        }
        int max = numbers[0];

        for (int i=1; i<numbers.length; i++){
            if (numbers[i] > max){
                max = numbers[i];
            }
        }
        return max;
    }
}
