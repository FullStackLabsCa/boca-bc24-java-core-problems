package arrayproblem;

import java.util.Arrays;
import java.util.Scanner;

public class ShiftElement {
    static int[] shiftArray(int [] intArray, int shiftPosition) {;
        int lastIndex = intArray.length, temp;
        for (int i = 0; i < shiftPosition; i++) {
            temp = intArray[lastIndex-1];
            for (int j = lastIndex - 1; j > 0; j--) {
                intArray[j] = intArray[j - 1];
            }
            intArray[0] = temp;

            // For left shifting
//            temp = intArray[0];
//            for (int j = 0; j < lastIndex - 1; j++) {
//                intArray[j] = intArray[j + 1];
//            }
//            intArray[lastIndex - 1] = temp;
        }
        return intArray;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an array length::");
        int arySize = scanner.nextInt();

        System.out.println("Enter array element::");
        int [] intArray = new int[arySize];
        for (int i = 0; i < arySize; i++) {
            intArray[i] = scanner.nextInt();
        }

        System.out.println("Enter an number of time you want to shift elements::");
        int shiftPosition = scanner.nextInt();

        System.out.println("Shifted array= " + Arrays.toString(shiftArray(intArray, shiftPosition)));
    }
}
