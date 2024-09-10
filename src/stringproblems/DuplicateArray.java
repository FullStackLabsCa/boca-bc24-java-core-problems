package stringproblems;
import java.util.Arrays;
import java.util.Scanner;

public class DuplicateArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter a value to find the duplicate");
        String input = scanner.nextLine();
        String[] inputs = input.split(",");
        int[] intInputArray = new int[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intInputArray[i] = Integer.parseInt(inputs[i].trim());
        }
        System.out.println("duplicateValues  = " + duplicatearray(intInputArray));
    }

    public static String duplicatearray(int[] input) {
        String duplicateValues = "";
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                duplicateValues += input[i] +" ";
                }
            }
        }
        return duplicateValues;
    }
}
