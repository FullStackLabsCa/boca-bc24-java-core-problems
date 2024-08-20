package MathematicsProblem;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumber {

    //  method for validation for inputs
    public static boolean isValidate(String input) {
        if (!input.matches("\\d+") || input == null) {
            System.out.println("Enter valid number ");
            return false;
        }
        return true;
    }

    //  method for checking first value is greater than second value
    public static boolean globalValidation(String firstValue, String secondValue) {
        if (Integer.parseInt(firstValue) > (Integer.parseInt(secondValue))) {
            System.out.println("Enter a value greater then second value");
            return false;
        }
        return true;
    }

    public static ArrayList<Integer> checkprimenumbers(int firstValue, int lastValue) {
        ArrayList<Integer> primeNumber = new ArrayList<>();
        boolean isprime;
        for (int i = firstValue; i <= lastValue; i++) {
            isprime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    System.out.println(i + " not prime");
                    isprime = false;
                    break;
                }
            }
            if (isprime) {
                primeNumber.add(i);
            }
        }
        return primeNumber;
    }
// main business logic
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean validate = false;
        while (!validate) {
            System.out.println("Enter first value");
            String firstValue = scanner.nextLine().trim();
            validate= isValidate(firstValue);
            if(validate){
                System.out.println("enter second value");
                String lastValue = scanner.nextLine();
                if(validate){
                    validate=  globalValidation(firstValue,lastValue);
                    System.out.println(checkprimenumbers(Integer.parseInt(firstValue),Integer.parseInt(lastValue)));
                }
            }
        }
    }
}





