package problems;

import java.util.Arrays;
import java.util.Collections;


public class Problem {

    //Please code these problems:
    //
    //Problem 1: Find Maximum in Array
    //Write a Java program to find the maximum value in an array of integers.


    public static void main(String[] args) {
       Integer [] numberArray = {1, 2, 3, 4, 6, 7, 229, 8, 15, 16, 17, 18};

       int max = Collections.max(Arrays.asList(numberArray));
       int min = Collections.max(Arrays.asList(numberArray));
        System.out.println("Maximum number: " + max);
        System.out.println("Maximum number: " + min);


    }
}
