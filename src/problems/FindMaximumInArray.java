package problems;

import java.util.Scanner;

public class FindMaximumInArray {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("How many numbers you want to input: ");
        int n = s.nextInt();

        int[] num = new int[n];

        for(int i=0; i<n; i++){
            num[i] = s.nextInt();
        }

        System.out.println(findMax(num));
    }

    public static int findMax(int[] numbers){
        if(numbers == null || numbers.length == 0){
            return Integer.MIN_VALUE;
        }
        int max = numbers[0];
        for (int number : numbers) {
            if (number > max) {
                max = number;
            }
        }

        return max;
    }
}
