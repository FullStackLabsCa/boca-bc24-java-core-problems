package problems;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumber {


    // business logic
    public static ArrayList<Integer> checkPrimeNumber(int firstNumber, int lastNumber) {
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        boolean isPrime;
        for (int i = firstNumber; i <= lastNumber; i++) {
            isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    System.out.println(i + " Not prime");
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeNumbers.add(i);

            }
        }
        return primeNumbers;
    }


    public static void main(String[] args) {


        //report output


        //accept input
        Scanner scanner = new Scanner(System.in);

        boolean validated = false;
        String firstNumber = null;
        String lastNumber = null;

        for (int i = 1; i < 4; i++) {
            int a = i;
            switch (a) {
                case 1:
                    System.out.println("Enter Starting Number => ");
                    firstNumber = scanner.nextLine().trim();
                    validated = checkIfInputIsValidNumber(firstNumber);
                    if (!validated) {
                        i = a-1;
                        break;
                    }
                case 2:
                    System.out.println("Enter Ending Number => ");
                    lastNumber = scanner.nextLine().trim();
                    validated = checkIfInputIsValidNumber(lastNumber);
                    if (!validated) {
                        i = a-1;
                        break;
                    }
                case 3:
                    validated = checkGlobalValidationForAllNumbers(firstNumber, lastNumber);
                    if (!validated) {i = a-1;
                        break;
                    }
                case 4:
                    System.out.println(checkPrimeNumber(Integer.parseInt(firstNumber), Integer.parseInt(lastNumber)));
            }
        }
    }

//        while (!validated) {
//            System.out.println("Enter Starting Number => ");
//            String firstNumber = scanner.nextLine().trim();
//            validated = checkIfInputIsValidNumber(firstNumber);
//
//
//            if (validated) {
//                System.out.println("Enter Ending Number => ");
//                String lastNumber = scanner.nextLine().trim();
//                validated = checkIfInputIsValidNumber(lastNumber);
//                if (validated) {
//
//                    validated = checkGlobalValidationForAllNumbers(firstNumber, lastNumber);
//                    System.out.println(checkPrimeNumber(Integer.parseInt(firstNumber), Integer.parseInt(lastNumber)));
//                }
//
//            }
//
//        }


    //validate input
    private static boolean checkGlobalValidationForAllNumbers(String firstNumber, String lastNumber) {

        if (Integer.parseInt(firstNumber) > Integer.parseInt(lastNumber)) {
            System.out.println("first number cannot be greater than second number");
            return false;

        }
        return true;
    }

    private static boolean checkIfInputIsValidNumber(String number) {
        if (!number.matches("\\d+") || number == null) {
            System.out.println("enter a valid number");
            return false;

        }
        return true;
    }
//    public int primenumbers(int num1,int num2){
//
//    }
}