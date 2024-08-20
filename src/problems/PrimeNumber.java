package problems;//package problems;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class PrimeNumber {
//
//    public static void main(String[] args) {
//
//        //take input
//        Scanner scanner = new Scanner(System.in);
//        String stringInput = scanner.nextLine();
//
//        //check empty inputs
//        if (stringInput.trim().isEmpty()) {
//            System.out.println("Input is empty");
//        }
//        int number = Integer.parseInt(stringInput);
//
//        ArrayList<Integer> primeNumbers = new ArrayList<>();
//
//        //prime number starts from 2
//        if(number >= 2) {
//            primeNumbers.add(2);
//        }
//
//        for (int i = 3; i <= number ; i++) {
//            for(int prime: primeNumbers) {
//                if (prime * prime > 1) {
//                    System.out.println("Not a prime number");
//                    break;
//                }
//
//                if (i % number == 0) {
//
//                }
//            }
//
//
//        }
//
//
//
//        primeNumbers.add(2);
//        primeNumbers.add(3);
//        for (int i = 2; i <= number; i++) {
//            if (i % 2 != 0 && i % 3 != 0) {
//                primeNumbers.add(i);
//            }
//        }
//        primeNumbers.forEach(System.out::println);
//
//    }
//}
