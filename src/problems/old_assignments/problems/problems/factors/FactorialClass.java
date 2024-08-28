package problems.old_assignments.problems.problems.factors;

import java.util.Scanner;

public class FactorialClass {
    //Calculate the factorial of number n (1 >> n <= 12)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number to find teh factorial,,, ");
        int num = scanner.nextInt();

        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact = fact*i;
        }
        System.out.println("Factorial of " +num + " is " +fact);
        System.out.println("**************************************");

        FactorialClass factorialClass = new FactorialClass();
        factorialClass.factorialMethod(5);


    }
    public void factorialMethod(int num){
        long fact = 1;
        for (int i = num; i> 0 ; i--) {
            fact = fact * i;
        }
        System.out.println("Factorial of " +num + " is " +fact);    }
}
