/*13 Find first duplicate in an array of numbers
14 Sum all the numbers in array
15 Sum only the Odd or Even Numbers in array*/

package problems.oldProblems;

import java.util.HashSet;

public class P9_Array {

    public static void main(String[] args) {


        int[] numbers = {3, 5, 4, 3, 2, 4, 1, 10, 20, 30, 40, 50, 10, 21, 30, 43, 50, 65, 70};

        HashSet<Integer> num_see = new HashSet<>();
        int firstDuplicate = -1;

        for (int number : numbers) {
            if (num_see.contains(number)) {
                firstDuplicate = number;
                break;
            }
            num_see.add(number);
        }

        if (firstDuplicate != -1) {
            System.out.println("The first duplicate number is: " + firstDuplicate);
        } else {
            System.out.println("No duplicates found.");
        }

//        --------------------------------------

        System.out.println("\nSum all the numbers in array:");
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        System.out.println("The sum of all numbers in the array is: " + sum);

//        ---------------------------------------------

        System.out.println("\nSum only the Odd or Even Numbers in array");
        int sumOdd = 0;
        int sumEven = 0;

        for (int number : numbers) {
            if (number % 2 == 0) {
                sumEven += number;
            } else {
                sumOdd += number;
            }
        }

        System.out.println("The sum of odd numbers in the array is: " + sumOdd);
        System.out.println("The sum of even numbers in the array is: " + sumEven);
    }


}
