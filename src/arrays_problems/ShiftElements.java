package arrays_problems;

import java.util.Arrays;
import java.util.Scanner;

public class ShiftElements {


    public static int[] shiftArray(int[] input, int position) {
        int[] temp = new int[input.length];
        System.arraycopy(input,0,temp,0,input.length);
        for (int i = 0; i < input.length; i++) {
            int newPosition = i + position;
            if (newPosition < input.length) {
//                input[i]=temp[newPosition];
                input[newPosition] = temp[i];
            }else{
//                input[i]=temp[newPosition- input.length];
                input[newPosition- input.length]=temp[i];
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("Please provide the size of an array to be checked");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] inputArray = new int[size];
        System.out.println("Please enter the elements of an array");
        for (int userArrayIndex = 0; userArrayIndex < size; userArrayIndex++) {
            inputArray[userArrayIndex] = scanner.nextInt();
        }
        System.out.println("Please enter the positions to shift");
        int position = scanner.nextInt();
        System.out.println("User enter array");
        for (int userArray : inputArray) {
            System.out.print(userArray + " ");
        }
        System.out.println("\nArray After Shifting Elements : " + Arrays.toString(ShiftElements.shiftArray(inputArray,position)));
    }
}
