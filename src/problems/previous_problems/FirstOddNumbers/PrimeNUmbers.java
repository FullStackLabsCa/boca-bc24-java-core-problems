package oldproblems.FirstOddNumbers;

import java.util.Scanner;

public class PrimeNUmbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter first number = ");
        int firstNumer = scanner.nextInt();

        System.out.println("Enter last number = ");
        int lastNumer = scanner.nextInt();

        for (int i = firstNumer; i<= lastNumer; i++){
            for (int j=2; j<i; i++){
                if(i % j == 0){
                    System.out.println(i+" is not prime");
                    break;
                }
            }
            System.out.println(i+" is prime");
        }
    }
}
