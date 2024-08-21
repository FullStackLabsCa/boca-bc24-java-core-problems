package arrays_problems;

import java.util.Arrays;
import java.util.Scanner;

public class DuplicateElement {
    public static int[] findDuplicates(int[] input) {
        int count = 0;
        int l = 0;
        int[] temp = new int[input.length];
        FIRST: for (int i = 0; i < input.length; i++) {
            SECOND: for (int j = i + 1; j < input.length; j++) {
              THIRD:  for (int m=0; m < j; m++) {
                    if (input[i] == input[j] && input[i] != input[m]) {
                        count++;
                        for (; l < count; l++) {
                            temp[l] = input[i];
                        }
                        break SECOND;
                    }
                    }
                if (input[i] == input[j]){
                    count=1;
                    temp[0]=input[i];
                    break FIRST;
                }
            }
//            if (count == 0) {
//                count = 1;
//                temp[0] = input[0];
            }
            int[] duplicates = new int[count];
            System.arraycopy(temp, 0, duplicates, 0, duplicates.length);
        return duplicates;
    }
        public static void main (String[]args){
            System.out.println("Please provide the size of an array to be checked");
            Scanner scanner = new Scanner(System.in);
            int size = scanner.nextInt();
            int[] inputArray = new int[size];
            System.out.println("Please enter the elements of an array");
            for (int userArrayIndex = 0; userArrayIndex < size; userArrayIndex++) {
                inputArray[userArrayIndex] = scanner.nextInt();
            }
            System.out.println("User enter array");
            for (int userArray : inputArray) {
                System.out.print(userArray + " ");
            }
            System.out.println("\nDuplicate Elements in array : " + Arrays.toString(DuplicateElement.findDuplicates(inputArray)));
        }
    }
