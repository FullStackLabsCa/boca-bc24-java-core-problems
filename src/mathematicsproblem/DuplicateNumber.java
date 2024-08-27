package mathematicsproblem;

import java.util.Scanner;

public class DuplicateNumber {
    public static void main(String[] args) {// Find first duplicate in an array of numbers
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers to find the duplicate");
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intInputArray = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intInputArray[i] = Integer.parseInt(inputs[i].trim());
        }
    }

    public static int[] duplicateArray(int[] inputs) {
        int value = inputs.length;
        int[] repeatedValues = new int[0];
        for (int i = 0; i < value; i++) {
            for (int j = i + 1; j < value; j++) {
                int currentValue = value;
                if (currentValue == repeatedValues.length) {
                    System.out.println("Repeatvalue is  " + currentValue);
                    break;
                }
            }
        }

        return repeatedValues;
    }
}

