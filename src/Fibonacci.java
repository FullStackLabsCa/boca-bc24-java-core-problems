import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        String value = scanner.nextLine();
        if(value.length() <0){
            System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
        }
        try {
            int input = Integer.parseInt(value);
            if (input < 4 || input > 47) {
                System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
            } else if (input == 4){
                System.out.println("problems.Fibonacci Series up to 4 numbers: [0, 1, 1, 2]");
            }else {
                List<Integer> fibonacciList = generateFibonacci(input);
                System.out.println("Fibonacci sequence of " + input + " numbers:" + fibonacciList);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number between 4 and 47.");
        }
    }

    public static List<Integer> generateFibonacci(int input) {

        List<Integer> FibonaccSeries = new ArrayList<>();

        int firstValue = 0;
        int secondValue = 1;
        FibonaccSeries.add(firstValue);
        if (input > 1) {
            FibonaccSeries.add(secondValue);
        }
        for (int i = 2; i < input; i++) {
            int next = firstValue + secondValue;
            FibonaccSeries.add(next);
            firstValue = secondValue;
            secondValue = next;
        }
        return FibonaccSeries;
    }
}





