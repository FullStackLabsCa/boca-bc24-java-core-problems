package problems;

import java.util.Scanner;

public class Factorial {

            public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("please enter the value: ");
                int number = Integer.parseInt(scanner.next());
                int factorial=1;
                for (int i=1 ; i<=number; i++){
                    factorial=factorial*i;

                }
                System.out.println("the factorial is: "+factorial);

            }
}