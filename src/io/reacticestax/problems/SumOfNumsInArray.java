package io.reacticestax.problems;

public class SumOfNumsInArray {
    public static void main(String[] args) {
        //Sum all the numbers in array
        //initialize an array
        int[] sumArray = {10, 5, 20};
        int sum = 0;

        //for loop to iterate through array
        for (int i = 0; i < sumArray.length; i++) {
            sum += sumArray[i];
            System.out.println(sum);
        }
        int total = sum;
        System.out.println("total sum of numbers in array : " + total);

    }
}
