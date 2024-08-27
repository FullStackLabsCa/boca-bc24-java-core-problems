//Problem 1: Find Maximum in Array
//        Write a Java program to find the maximum value in an array of integers.

public class ArrayMax {

    public static void findMaxInArray() {
        int[] numbArray = {2, 4, 5, 6, 10, 50, 20, 1, 25, 9};

        int maxNumber = numbArray[0];

        for (int j : numbArray) {

            if (j > maxNumber) {
                maxNumber = j;
            }
        }
        System.out.println("Max numebr is " + maxNumber);
    }

}


