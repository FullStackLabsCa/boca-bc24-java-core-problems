package io.reacticestax.problems;

public class DuplicateInNumArray {
    public static void main(String[] args) {

        //initialize an array
        //using a for loop iterate through the elements of the array
        // check which element is duplicate
        // to check if an element is duplicate run a new for loop(with range i+1)
        //to compare one element with element next to it
        // inside the old for loop on the same array
        // and print the element which is equal to each other


        int[] numArr = {1, 2, 2,3, 4, 4, 5, 5, 6, 6, 6, 6};

        for (int i = 0; i < numArr.length; i++) {
            for (int j = i + 1; j < numArr.length; j++) {
                if (numArr[i] == numArr[j]) {
                    System.out.println(numArr[j]);

                }


            }


        }
    }
}
