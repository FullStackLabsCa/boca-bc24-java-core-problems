package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("Enter String: ");
        String input = reader.nextLine();
        reader.close();

        if (!isValidInput(input)) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        } else if (Integer.parseInt(input) < 4 || Integer.parseInt(input) > 47) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        } else if (Integer.parseInt(input) == 4) {
            System.out.println("problems.Fibonacci Series up to 4 numbers: " + generateFibonacci(Integer.parseInt(input)));
        } else {
            generateFibonacci(Integer.parseInt(input));

        }

    }

    private static boolean isValidInput(String input) {
        try {
            return input.matches("-?\\d+");
        } catch (Exception e) {
            return false;
        }
    }


    public static List<Integer> generateFibonacci(int n) {
        int n1 = 0;
        int n2 = 1;
        int temp = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(n1);
        arrayList.add(n2);
        for (int i = 0; i < n - 2; i++) {
            temp = n1 + n2;
            n1 = n2;
            n2 = temp;
            arrayList.add(temp);
        }
        System.out.println(arrayList);
        return arrayList;
    }
}

