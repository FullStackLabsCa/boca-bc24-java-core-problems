package stringproblems;

import java.util.Arrays;
import java.util.Scanner;

public class ShiftArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a value to shift");
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intInputArray = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intInputArray[i] = Integer.parseInt(inputs[i].trim());
        }
        System.out.println("Enter a value to shift an array");

        int input2 = scanner.nextInt();
            int[] shiftarray = shiftarray(intInputArray, input2);

            System.out.println("Shifted Array = " + Arrays.toString(shiftarray));

        }
    public static int[] shiftarray(int[] values, int shiftCount) {
        int[] shiftedArray = new int[values.length];
        for (int i = 0; i < values.length; i++) {
            if (i < values.length - shiftCount) {
                shiftedArray[i + shiftCount] = values[i];
            } else {
                shiftedArray[i + shiftCount - values.length] = values[i];
            }
        }
        return shiftedArray;
    }
}
